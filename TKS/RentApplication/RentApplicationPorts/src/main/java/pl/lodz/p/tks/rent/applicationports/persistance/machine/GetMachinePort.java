package pl.lodz.p.tks.rent.applicationports.persistance.machine;

import pl.lodz.p.tks.rent.core.domainmodel.machine.Machine;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface GetMachinePort {
    Optional<Machine> findMachineById(UUID machineId);

    Optional<Machine> findMachineByName(String name);

    List<Machine> getAll();
}