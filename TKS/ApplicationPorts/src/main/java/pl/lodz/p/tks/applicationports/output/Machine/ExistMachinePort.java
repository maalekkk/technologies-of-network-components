package pl.lodz.p.tks.applicationports.output.Machine;

import pl.lodz.p.tks.applicationcore.domainmodel.model.machine.Machine;

import java.util.UUID;

public interface ExistMachinePort {
    boolean existsMachineById(UUID uuid);
}
