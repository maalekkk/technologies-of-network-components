package pl.lodz.p.tks.applicationports.persistence.machine;

import pl.lodz.p.tks.view.domainmodel.model.machine.Machine;

import java.util.UUID;

public interface DeleteMachinePort {
    boolean deleteMachine(Machine machine);

    boolean deleteById(UUID machineId);
}