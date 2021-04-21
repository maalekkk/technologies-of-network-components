package pl.lodz.p.tks.rent.applicationports.view;


import pl.lodz.p.tks.rent.core.domainmodel.machine.Machine;

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

    boolean deleteMachine(Machine machine);

    boolean deleteMachineById(UUID machineId);
}