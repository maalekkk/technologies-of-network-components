package pl.lodz.p.tks.applicationports.output.User;

import pl.lodz.p.tks.view.domainmodel.model.user.User;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.function.Predicate;

public interface GetUserPort {

    public Optional<User> findUserById(UUID userId);

    public Optional<User> findUserByUsername(String username);

    public List<User> findByPredicate(Predicate<User> predicate);

    public List<User> getAll();
}
