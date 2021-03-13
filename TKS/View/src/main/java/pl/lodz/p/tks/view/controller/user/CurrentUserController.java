package pl.lodz.p.tks.view.controller.user;

import pl.lodz.p.tks.applicationports.input.UserUseCase;
import pl.lodz.p.tks.view.domainmodel.model.user.Role;
import pl.lodz.p.tks.view.domainmodel.model.user.User;

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
    private UserUseCase userUseCase;

    @PostConstruct
    private void init()
    {
        currentUser = userUseCase.getCurrentUser();
    }

    public User getCurrentUser()
    {
        return currentUser;
    }

    public boolean isClientRole()
    {
        return userUseCase.getCurrentRole() == Role.Client;
    }

    public boolean isAdminRole()
    {
        return userUseCase.getCurrentRole() == Role.Admin;
    }

    public boolean isOwnerRole()
    {
        return userUseCase.getCurrentRole() == Role.Owner;
    }
}
