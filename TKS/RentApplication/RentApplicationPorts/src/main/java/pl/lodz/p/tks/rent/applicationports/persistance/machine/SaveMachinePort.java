package pl.lodz.p.tks.rent.applicationports.persistance.machine;

import pl.lodz.p.tks.rent.core.domainmodel.machine.Machine;

import javax.validation.Valid;

public interface SaveMachinePort {
    Machine saveMachine(@Valid Machine machine);
}
