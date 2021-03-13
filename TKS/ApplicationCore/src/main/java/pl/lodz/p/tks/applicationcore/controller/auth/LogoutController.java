package pl.lodz.p.tks.applicationcore.controller.auth;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Named
@RequestScoped
public class LogoutController
{
    @Inject
    private HttpServletRequest request;

    public String submit() throws ServletException
    {
        HttpSession session = request.getSession();
        if (session != null)
        {
            request.logout();
            request.getSession().invalidate();
        }
        return "root";
    }
}
