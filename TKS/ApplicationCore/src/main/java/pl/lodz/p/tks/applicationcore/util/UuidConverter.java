package pl.lodz.p.tks.applicationcore.util;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import java.util.UUID;

@FacesConverter(forClass = UUID.class)
public class UuidConverter implements Converter<UUID>
{
    @Override
    public UUID getAsObject(FacesContext facesContext, UIComponent uiComponent, String s)
    {
        return UUID.fromString(s);
    }

    @Override
    public String getAsString(FacesContext facesContext, UIComponent uiComponent, UUID uuid)
    {
        return uuid.toString();
    }
}
