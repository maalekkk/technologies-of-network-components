package pl.lodz.p.tks.rent.core.services;

import pl.lodz.p.tks.rent.applicationports.view.RentUseCase;
import pl.lodz.p.tks.rent.core.domainmodel.machine.Machine;
import pl.lodz.p.tks.rent.core.domainmodel.rent.Period;
import pl.lodz.p.tks.rent.core.domainmodel.rent.Rent;
import pl.lodz.p.tks.rent.core.domainmodel.rent.Status;
import pl.lodz.p.tks.rent.applicationports.persistance.rent.*;
import pl.lodz.p.tks.rent.core.domainmodel.user.User;
import pl.lodz.p.tks.rent.core.services.utils.DateTimeProvider;
import pl.lodz.p.tks.rent.core.services.utils.MessageProvider;

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
        if (getRentStatus(rent) == Status.RESERVED) {
            deleteRentPort.deleteRent(rent);
        }
    }

    public void finishRent(Rent rent) {
        if (getRentStatus(rent) == Status.IN_PROGRESS) {
            rent.getPeriod().setEndDate(dateTimeProvider.now());
            saveRentPort.saveRent(rent);
        }
    }

    public boolean isMachineAllocated(Machine machine) {
        return !getRentPort.findByPredicate(rent -> rent.getMachine().equals(machine) &&
                (getRentStatus(rent) == Status.RESERVED || getRentStatus(rent) == Status.IN_PROGRESS)).isEmpty();
    }

    public boolean isMachineAvailable(Rent rent) {
        return getRentPort.findByPredicate(r -> r.getMachine().equals(rent.getMachine()) &&
                (getRentStatus(r) == Status.RESERVED || getRentStatus(r) == Status.IN_PROGRESS))
                .stream().noneMatch(r -> arePeriodOverlaping(r.getPeriod(), rent.getPeriod()));
    }

    public Status getRentStatus(Rent rent) {
        LocalDateTime now = dateTimeProvider.now();
        if (isRentFinished(rent, now)) {
            return Status.FINISHED;
        }
        if (isRentInProgress(rent, now)) {
            return Status.IN_PROGRESS;
        }
        return Status.RESERVED;
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