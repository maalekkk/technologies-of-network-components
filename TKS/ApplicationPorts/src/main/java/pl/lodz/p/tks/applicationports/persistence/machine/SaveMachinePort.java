package pl.lodz.p.tks.applicationports.persistence.machine;
import pl.lodz.p.tks.view.domainmodel.model.machine.Machine;

import javax.validation.Valid;

public interface SaveMachinePort {
    Machine saveMachine(@Valid Machine machine);

}
