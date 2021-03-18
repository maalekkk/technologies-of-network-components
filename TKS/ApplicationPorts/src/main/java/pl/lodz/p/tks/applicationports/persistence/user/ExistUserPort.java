package pl.lodz.p.tks.applicationports.persistence.user;

import java.util.UUID;

public interface ExistUserPort {
    boolean existsUserById(UUID uuid);
}
