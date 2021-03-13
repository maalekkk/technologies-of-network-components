package pl.lodz.p.tks.applicationports.output.Rent;

import java.util.UUID;

public interface ExistRentPort {
    boolean existsRentById(UUID uuid);
}
