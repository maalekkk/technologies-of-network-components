package pl.lodz.p.tks.rent.applicationports.view;

import pl.lodz.p.tks.rent.core.domainmodel.machine.Machine;
import pl.lodz.p.tks.rent.core.domainmodel.rent.Rent;
import pl.lodz.p.tks.rent.core.domainmodel.rent.Status;
import pl.lodz.p.tks.rent.core.domainmodel.user.User;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.function.Predicate;

public interface RentUseCase {
    Rent saveRent(@Valid Rent rent);

    Optional<Rent> findRentById(UUID fromString);

    List<Rent> findRentsByUser(User user);

    List<Rent> filterRents(List<Predicate<Rent>> filters);

    boolean existsRent(Rent rent);

    List<Rent> getAll();

    void deleteRent(Rent rent);

    void finishRent(Rent rent);

    boolean isMachineAllocated(Machine machine);

    boolean isMachineAvailable(Rent rent);

    Status getRentStatus(Rent rent);
}
