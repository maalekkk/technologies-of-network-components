package pl.lodz.p.tks.view.controller.user;


import pl.lodz.p.tks.user.core.domainmodel.user.User;
import pl.lodz.p.tks.user.applicationports.view.UserUseCase;

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
    private UserUseCase userUseCase;

    private List<User> users;

    private String userFilter = "";

    @PostConstruct
    private void init()
    {
        users = userUseCase.getAll();
    }

    public String changeUserActivity(User user)
    {
        userUseCase.changeUserActivity(user);
        return "show-users.xhtml?faces-redirect=true";
    }

    public void filter()
    {
        if (!userFilter.isEmpty())
        {
            users = userUseCase.filterUserByUsername(userFilter);
        }
        else
        {
            users = userUseCase.getAll();
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
