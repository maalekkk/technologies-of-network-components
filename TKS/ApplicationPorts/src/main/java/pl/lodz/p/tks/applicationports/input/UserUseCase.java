package pl.lodz.p.tks.applicationports.input;

import pl.lodz.p.tks.applicationcore.domainmodel.model.user.Role;
import pl.lodz.p.tks.applicationcore.domainmodel.model.user.User;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface UserUseCase {
    User saveUser(@Valid User user);

    Optional<User> findUserById(UUID userId);

    Optional<User> findUserByUsername(String username);

    List<User> filterUserByUsername(String username);

    public User getCurrentUser();

    public Role getCurrentRole();

    public boolean existsUser(User user);

    public List<User> getAll();

    public void changeUserActivity(User user);

}