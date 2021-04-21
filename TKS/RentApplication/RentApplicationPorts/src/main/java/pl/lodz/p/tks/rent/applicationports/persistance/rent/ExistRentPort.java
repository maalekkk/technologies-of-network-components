package pl.lodz.p.tks.rent.applicationports.persistance.rent;

import java.util.UUID;

public interface ExistRentPort {
    boolean existsRentById(UUID uuid);
}