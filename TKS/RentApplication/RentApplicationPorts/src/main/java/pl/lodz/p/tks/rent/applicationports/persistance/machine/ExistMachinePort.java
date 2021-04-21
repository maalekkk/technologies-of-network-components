package pl.lodz.p.tks.rent.applicationports.persistance.machine;

import java.util.UUID;

public interface ExistMachinePort {
    boolean existsMachineById(UUID uuid);
}