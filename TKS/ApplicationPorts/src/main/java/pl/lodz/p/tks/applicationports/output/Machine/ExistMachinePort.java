package pl.lodz.p.tks.applicationports.output.Machine;

import java.util.UUID;

public interface ExistMachinePort {
    boolean existsMachineById(UUID uuid);
}
