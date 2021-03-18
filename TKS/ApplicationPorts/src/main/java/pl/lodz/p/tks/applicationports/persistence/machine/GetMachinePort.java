package pl.lodz.p.tks.applicationports.persistence.machine;

import pl.lodz.p.tks.view.domainmodel.model.machine.Machine;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface GetMachinePort {
    Optional<Machine> findMachineById(UUID machineId);

    public Optional<Machine> findMachineByName(String name);

    public List<Machine> getAll();
}
