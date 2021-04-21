package pl.lodz.p.tks.rent.applicationports.persistance.machine;

import pl.lodz.p.tks.rent.core.domainmodel.machine.Machine;

import java.util.UUID;

public interface DeleteMachinePort {
    boolean deleteMachine(Machine machine);

    boolean deleteById(UUID machineId);
}