package pl.lodz.p.tks.applicationports.input;

import pl.lodz.p.tks.applicationcore.domainmodel.model.machine.Machine;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface MachineUseCase {
    Machine saveMachine(@Valid Machine machine);

    Optional<Machine> findMachineById(UUID machineId);

    Optional<Machine> findMachineByName(String name);

    List<Machine> filterMachineByName(String name);

    boolean existsMachine(Machine machine);

    List<Machine> getAll();

    void deleteMachine(Machine machine);
}
