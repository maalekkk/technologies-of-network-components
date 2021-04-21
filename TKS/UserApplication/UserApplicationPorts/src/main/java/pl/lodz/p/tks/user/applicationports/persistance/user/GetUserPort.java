package pl.lodz.p.tks.user.applicationports.persistance.user;

import pl.lodz.p.tks.user.core.domainmodel.user.User;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.function.Predicate;

public interface GetUserPort {

    Optional<User> findUserById(UUID userId);

    Optional<User> findUserByUsername(String username);

    List<User> findByPredicate(Predicate<User> predicate);

    List<User> getAll();
}
