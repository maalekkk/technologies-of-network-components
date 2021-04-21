package pl.lodz.p.tks.rent.core.services.utils;

import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import java.util.ResourceBundle;

@RequestScoped
public class MessageProvider {
    private final FacesContext context = FacesContext.getCurrentInstance();

    public String getMessage(String bundle, String key) {
        return ResourceBundle.getBundle(bundle, context.getViewRoot().getLocale()).getString(key);
    }

    public void addMessage(String location, String msg, FacesMessage.Severity type) {
        FacesMessage message = new FacesMessage(type, msg, null);
        context.addMessage(location, message);
    }
}