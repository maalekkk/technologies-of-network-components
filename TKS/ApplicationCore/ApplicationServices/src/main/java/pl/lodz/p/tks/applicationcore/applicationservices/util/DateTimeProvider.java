package pl.lodz.p.tks.applicationcore.applicationservices.util;

import javax.enterprise.context.RequestScoped;
import java.time.LocalDateTime;

@RequestScoped
public class DateTimeProvider
{
    public LocalDateTime now()
    {
        return LocalDateTime.now();
    }
}
