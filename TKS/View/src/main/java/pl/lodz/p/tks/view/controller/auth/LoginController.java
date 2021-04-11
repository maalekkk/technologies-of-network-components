package pl.lodz.p.tks.view.controller.auth;

import pl.lodz.p.tks.view.util.MessageProvider;

import javax.enterprise.context.RequestScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.security.enterprise.AuthenticationStatus;
import javax.security.enterprise.SecurityContext;
import javax.security.enterprise.authentication.mechanism.http.AuthenticationParameters;
import javax.security.enterprise.credential.UsernamePasswordCredential;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.constraints.NotBlank;
import java.util.logging.Level;
import java.util.logging.Logger;

@Named
@RequestScoped
public class LoginController
{
    private static final Logger logger = Logger.getLogger(LoginController.class.getSimpleName());

    @NotBlank
    private String username;

    @NotBlank
    private String password;

    @Inject
    private FacesContext facesContext;

    @Inject
    private SecurityContext securityContext;

    @Inject
    private MessageProvider messageProvider;

    public String submit()
    {
        switch (login(username, password))
        {
            case SEND_CONTINUE:
            {
                facesContext.responseComplete();
                break;
            }
            case SEND_FAILURE:
            {
                logger.log(Level.WARNING, username + " " + messageProvider.getMessage("messages", "login_failed"));
                break;
            }
            case SUCCESS:
            {
                logger.log(Level.INFO, username + " " + messageProvider.getMessage("messages", "login_succeed"));
                return "dashboard";
            }
            default:
            {
                break;
            }
        }
        return null;
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

    private AuthenticationStatus login(String username, String password)
    {
        ExternalContext context = facesContext.getExternalContext();
        return securityContext.authenticate(
                (HttpServletRequest) context.getRequest(),
                (HttpServletResponse) context.getResponse(),
                AuthenticationParameters.withParams()
                        .credential(new UsernamePasswordCredential(username, password))
        );
    }
}