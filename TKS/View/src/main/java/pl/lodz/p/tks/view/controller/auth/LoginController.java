package pl.lodz.p.tks.view.controller.auth;

import pl.lodz.p.tks.applicationports.view.UserUseCase;
import pl.lodz.p.tks.view.domainmodel.model.user.User;
import pl.lodz.p.tks.view.util.MessageProvider;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

@Named
@RequestScoped
public class LoginController
{
    private static final Logger logger = Logger.getLogger(LoginController.class.getSimpleName());

    String username;
    String password;

    @Inject
    private UserUseCase userUseCase;

    @Inject
    private HttpServletRequest request;

    @Inject
    private MessageProvider messageProvider;

    public String submit()
    {
        Optional<User> optionalUser = userUseCase.findUserByUsername(username);
        if (optionalUser.isPresent())
        {
            if (optionalUser.get().isEnabled())
            {
                try
                {
                    request.login(username, password);
                    logger.log(Level.INFO, username + " " + messageProvider.getMessage("messages", "login_succeed"));
                    return "dashboard";
                }
                catch (ServletException ignored)
                {
                    logger.log(Level.WARNING, username + " " + messageProvider.getMessage("messages", "login_failed"));
                    return "root";
                }
            }
            logger.log(Level.INFO, username + " " + messageProvider.getMessage("messages", "login_restricted"));
        }
        return "root";
    }

    public String getUsername()
    {
        return username;
    }

    public void setUsername(String username)
    {
        this.username = username;
    }

    public String getPassword()
    {
        return password;
    }

    public void setPassword(String password)
    {
        this.password = password;
    }
}
