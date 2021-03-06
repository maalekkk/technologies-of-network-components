package pl.lodz.p.tks.applicationports.view;

import pl.lodz.p.tks.view.domainmodel.model.user.Role;
import pl.lodz.p.tks.view.domainmodel.model.user.User;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface UserUseCase {
    User saveUser(@Valid User user);

    Optional<User> findUserById(UUID userId);

    Optional<User> findUserByUsername(String username);

    List<User> filterUserByUsername(String username);

    User getCurrentUser();

    Role getCurrentRole();

    boolean existsUser(User user);

    List<User> getAll();

    void changeUserActivity(User user);
}