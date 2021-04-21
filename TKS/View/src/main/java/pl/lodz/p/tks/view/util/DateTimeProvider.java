package pl.lodz.p.tks.view.util;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Date;

public class DateTimeProvider implements Serializable
{
    public LocalDateTime now()
    {
        return LocalDateTime.now();
    }

    public Date toDate(LocalDateTime localDateTime)
    {
        return Date.from(localDateTime.toInstant(ZoneOffset.UTC));
    }
}
