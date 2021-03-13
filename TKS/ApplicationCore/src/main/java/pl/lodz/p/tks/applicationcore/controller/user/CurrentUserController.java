package pl.lodz.p.tks.applicationcore.controller.user;

import pl.lodz.p.tks.applicationcore.applicationservices.service.UserService;
import pl.lodz.p.tks.applicationcore.domainmodel.model.user.Role;
import pl.lodz.p.tks.applicationcore.domainmodel.model.user.User;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;

@Named
@SessionScoped
public class CurrentUserController implements Serializable
{
    private User currentUser;

    @Inject
    private UserService userService;

    @PostConstruct
    private void init()
    {
        currentUser = userService.getCurrentUser();
    }

    public User getCurrentUser()
    {
        return currentUser;
    }

    public boolean isClientRole()
    {
        return userService.getCurrentRole() == Role.Client;
    }

    public boolean isAdminRole()
    {
        return userService.getCurrentRole() == Role.Admin;
    }

    public boolean isOwnerRole()
    {
        return userService.getCurrentRole() == Role.Owner;
    }
}
