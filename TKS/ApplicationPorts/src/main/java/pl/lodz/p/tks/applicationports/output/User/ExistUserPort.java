package pl.lodz.p.tks.applicationports.output.User;

import java.util.UUID;

public interface ExistUserPort {
    boolean existsUserById(UUID uuid);
}
