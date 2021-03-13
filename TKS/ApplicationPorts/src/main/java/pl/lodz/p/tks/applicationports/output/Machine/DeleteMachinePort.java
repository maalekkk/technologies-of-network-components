package pl.lodz.p.tks.applicationports.output.Machine;

import pl.lodz.p.tks.applicationcore.domainmodel.model.machine.Machine;

public interface DeleteMachinePort
{
    void deleteMachine(Machine machine);
}
