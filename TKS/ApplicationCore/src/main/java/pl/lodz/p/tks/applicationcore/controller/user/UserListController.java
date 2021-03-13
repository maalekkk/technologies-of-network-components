package pl.lodz.p.tks.applicationcore.controller.user;

import pl.lodz.p.tks.applicationcore.applicationservices.service.UserService;
import pl.lodz.p.tks.applicationcore.domainmodel.model.user.User;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;

@Named
@ViewScoped
public class UserListController implements Serializable
{
    @Inject
    private UserService userService;

    private List<User> users;

    private String userFilter = "";

    @PostConstruct
    private void init()
    {
        users = userService.getAll();
    }

    public String changeUserActivity(User user)
    {
        userService.changeUserActivity(user);
        return "show-users.xhtml?faces-redirect=true";
    }

    public void filter()
    {
        if (!userFilter.isEmpty())
        {
            users = userService.filterUserByUsername(userFilter);
        }
        else
        {
            users = userService.getAll();
        }
    }

    public List<User> getUsers()
    {
        return users;
    }

    public String getUserFilter()
    {
        return userFilter;
    }

    public void setUserFilter(String userFilter)
    {
        this.userFilter = userFilter;
    }
}
