package pl.lodz.p.tks.applicationports.persistence.machine;

import java.util.UUID;

public interface ExistMachinePort {
    boolean existsMachineById(UUID uuid);
}