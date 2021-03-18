package pl.lodz.p.tks.applicationports.persistence.rent;

import java.util.UUID;

public interface ExistRentPort {
    boolean existsRentById(UUID uuid);
}
