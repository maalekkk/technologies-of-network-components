package pl.lodz.p.tks.applicationports.persistence.rent;

import pl.lodz.p.tks.view.domainmodel.model.rent.Rent;
import pl.lodz.p.tks.view.domainmodel.model.user.User;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.function.Predicate;

public interface GetRentPort {
    Optional<Rent> findRentById(UUID fromString);

    List<Rent> findRentsByUser(User user);

    List<Rent> findByPredicate(Predicate<Rent> predicate);

    List<Rent> getAll();
}