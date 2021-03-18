package pl.lodz.p.tks.view.applicationservices.service;

import pl.lodz.p.tks.view.domainmodel.model.user.Role;
import pl.lodz.p.tks.view.domainmodel.model.user.User;
import pl.lodz.p.tks.applicationports.view.UserUseCase;
import pl.lodz.p.tks.applicationports.persistence.user.ExistUserPort;
import pl.lodz.p.tks.applicationports.persistence.user.GetUserPort;
import pl.lodz.p.tks.applicationports.persistence.user.SaveUserPort;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RequestScoped
public class UserService implements UserUseCase
{
    @Inject
    private GetUserPort getUserPort;

    @Inject
    private SaveUserPort saveUserPort;

    @Inject
    private ExistUserPort existUserPort;

    @Inject
    private HttpServletRequest request;

    public User saveUser(@Valid User user)
    {
        return saveUserPort.saveUser(user);
    }

    public Optional<User> findUserById(UUID userId)
    {
        return getUserPort.findUserById(userId);
    }

    public Optional<User> findUserByUsername(String username)
    {
        return getUserPort.findUserByUsername(username);
    }

    public List<User> filterUserByUsername(String username)
    {
        return getUserPort.findByPredicate(user -> user.getUsername().contains(username));
    }

    public User getCurrentUser()
    {
        return findUserByUsername(request.getRemoteUser()).orElseThrow(IllegalStateException::new);
    }

    public Role getCurrentRole()
    {
        if (request.isUserInRole(Role.Client.name()))
        {
            return Role.Client;
        }
        if (request.isUserInRole(Role.Admin.name()))
        {
            return Role.Admin;
        }
        if (request.isUserInRole(Role.Owner.name()))
        {
            return Role.Owner;
        }
        return null;
    }

    public boolean existsUser(User user)
    {
        return existUserPort.existsUserById(user.getId());
    }

    public List<User> getAll()
    {
        return getUserPort.getAll();
    }

    public void changeUserActivity(User user)
    {
        user.setEnabled(!user.isEnabled());
        saveUserPort.saveUser(user);
    }
}
