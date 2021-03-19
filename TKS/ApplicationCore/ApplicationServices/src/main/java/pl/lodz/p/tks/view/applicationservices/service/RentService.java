package pl.lodz.p.tks.view.applicationservices.service;

import pl.lodz.p.tks.view.applicationservices.util.DateTimeProvider;
import pl.lodz.p.tks.view.applicationservices.util.MessageProvider;
import pl.lodz.p.tks.view.domainmodel.model.machine.Machine;
import pl.lodz.p.tks.view.domainmodel.model.rent.Period;
import pl.lodz.p.tks.view.domainmodel.model.rent.Rent;
import pl.lodz.p.tks.view.domainmodel.model.rent.Status;
import pl.lodz.p.tks.view.domainmodel.model.user.User;
import pl.lodz.p.tks.applicationports.view.RentUseCase;
import pl.lodz.p.tks.applicationports.persistence.rent.*;

import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.inject.Inject;
import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static pl.lodz.p.tks.view.domainmodel.model.rent.Status.*;

@RequestScoped
public class RentService implements RentUseCase {
    @Inject
    private DateTimeProvider dateTimeProvider;

    @Inject
    private MessageProvider messageProvider;

    @Inject
    private DeleteRentPort deleteRentPort;

    @Inject
    private GetRentPort getRentPort;

    @Inject
    private SaveRentPort saveRentPort;

    @Inject
    private ExistRentPort existRentPort;

    public Rent saveRent(@Valid Rent rent) {
        if (rent.getPeriod().getEndDate().isBefore(rent.getPeriod().getStartDate())) {
            String msg = messageProvider.getMessage("ValidationMessages", "period.message");
            messageProvider.addMessage("rent:endDate", msg, FacesMessage.SEVERITY_ERROR);
            return null;
        }
        if (!isMachineAvailable(rent)) {
            String msg = messageProvider.getMessage("ValidationMessages", "rent.date.busy.message");
            messageProvider.addMessage("rent:endDate", msg, FacesMessage.SEVERITY_ERROR);
            return null;
        }
        return saveRentPort.saveRent(rent);
    }

    public Optional<Rent> findRentById(UUID fromString) {
        return getRentPort.findRentById(fromString);
    }

    public List<Rent> findRentsByUser(User user) {
        return getRentPort.findRentsByUser(user);
    }

    public List<Rent> filterRents(List<Predicate<Rent>> filters) {
        Stream<Rent> stream = getAll().stream();
        for (Predicate<Rent> filter : filters) {
            stream = stream.filter(filter);
        }
        return stream.collect(Collectors.toList());
    }

    public boolean existsRent(Rent rent) {
        return existRentPort.existsRentById(rent.getId());
    }

    public List<Rent> getAll() {
        return getRentPort.getAll();
    }

    public void deleteRent(Rent rent) {
        if (getRentStatus(rent) == RESERVED) {
            deleteRentPort.deleteRent(rent);
        }
    }

    public void finishRent(Rent rent) {
        if (getRentStatus(rent) == IN_PROGRESS) {
            rent.getPeriod().setEndDate(dateTimeProvider.now());
            saveRentPort.saveRent(rent);
        }
    }

    public boolean isMachineAllocated(Machine machine) {
        return !getRentPort.findByPredicate(rent -> rent.getMachine().equals(machine) &&
                (getRentStatus(rent) == RESERVED || getRentStatus(rent) == IN_PROGRESS)).isEmpty();
    }

    public boolean isMachineAvailable(Rent rent) {
        return getRentPort.findByPredicate(r -> r.getMachine().equals(rent.getMachine()) &&
                (getRentStatus(r) == RESERVED || getRentStatus(r) == IN_PROGRESS))
                .stream().noneMatch(r -> arePeriodOverlaping(r.getPeriod(), rent.getPeriod()));
    }

    public Status getRentStatus(Rent rent) {
        LocalDateTime now = dateTimeProvider.now();
        if (isRentFinished(rent, now)) {
            return FINISHED;
        }
        if (isRentInProgress(rent, now)) {
            return IN_PROGRESS;
        }
        return RESERVED;
    }

    private boolean isRentInProgress(Rent rent, LocalDateTime time) {
        return rent.getPeriod().getStartDate().isBefore(time) && time.isBefore(rent.getPeriod().getEndDate());
    }

    private boolean isRentFinished(Rent rent, LocalDateTime time) {
        return rent.getPeriod().getEndDate().isBefore(time);
    }

    private boolean arePeriodOverlaping(@Valid Period p1, @Valid Period p2) {
        return p1.getStartDate().isBefore(p2.getEndDate()) && p2.getStartDate().isBefore(p1.getEndDate());
    }
}