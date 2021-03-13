package pl.lodz.p.tks.applicationports.output.Rent;

import pl.lodz.p.tks.view.domainmodel.model.rent.Rent;
import pl.lodz.p.tks.view.domainmodel.model.user.User;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.function.Predicate;

public interface GetRentPort {
    public Optional<Rent> findRentById(UUID fromString);

    public List<Rent> findRentsByUser(User user);

    public List<Rent> findByPredicate(Predicate<Rent> predicate);

    public List<Rent> getAll();

}
