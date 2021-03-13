package pl.lodz.p.tks.applicationcore.controller.user;

import pl.lodz.p.tks.applicationcore.applicationservices.service.UserService;
import pl.lodz.p.tks.applicationcore.validator.unique.username.UniqueUsername;
import pl.lodz.p.tks.applicationcore.domainmodel.model.user.User;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Named
@ViewScoped
public class UserController implements Serializable
{
    @Inject
    private UserService userService;

    @UniqueUsername
    @Size(min = 3, max = 20)
    private String username;

    private User user = new User();

    public void initUser()
    {
        userService.findUserById(user.getId()).ifPresent(u -> user = u);
    }

    public String submit()
    {
        if (user.getUsername() == null)
        {
            user.setUsername(username);
        }
        userService.saveUser(user);
        return "show-users.xhtml?faces-redirect=true";
    }

    public boolean isUpdate()
    {
        return userService.existsUser(user);
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
}
