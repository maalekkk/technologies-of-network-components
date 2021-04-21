package pl.lodz.p.tks.rent.core.services.utils;

import javax.enterprise.context.RequestScoped;
import java.time.LocalDateTime;

@RequestScoped
public class DateTimeProvider {
    public LocalDateTime now() {
        return LocalDateTime.now();
    }
}