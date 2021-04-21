package pl.lodz.p.tks.user.applicationports.persistance.user;

import java.util.UUID;

public interface ExistUserPort {
    boolean existsUserById(UUID uuid);
}
