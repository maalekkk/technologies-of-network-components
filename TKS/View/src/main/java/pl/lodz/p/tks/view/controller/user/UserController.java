package pl.lodz.p.tks.view.controller.user;

import pl.lodz.p.tks.user.core.domainmodel.user.Role;
import pl.lodz.p.tks.user.core.domainmodel.user.User;
import pl.lodz.p.tks.user.applicationports.view.UserUseCase;
import pl.lodz.p.tks.view.validator.unique.username.UniqueUsername;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Collections;

@Named
@ViewScoped
public class UserController implements Serializable
{
    @Inject
    private UserUseCase userUseCase;

    @UniqueUsername
    @Size(min = 3, max = 20)
    private String username;

    private User user = new User();

    public void initUser()
    {
        userUseCase.findUserById(user.getId()).ifPresent(u -> user = u);
    }

    public String submit()
    {
        if (user.getUsername() == null)
        {
            user.setUsername(username);
        }
        userUseCase.saveUser(user);
        return "show-users.xhtml?faces-redirect=true";
    }

    public boolean isUpdate()
    {
        return userUseCase.existsUser(user);
    }

    public User getUser()
    {
        return user;
    }

    public void setUser(User user)
    {
        this.user = user;
    }

    public String getUsername()
    {
        return username;
    }

    public void setUsername(String username)
    {
        this.username = username;
    }


    public Role getUserRole()
    {
        return user.getRoles().iterator().next();
    }

    public void setUserRole(Role userRole)
    {
        this.user.setRoles(Collections.singleton(userRole));
    }
}
