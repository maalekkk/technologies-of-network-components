package pl.lodz.p.tks.applicationports.output.Machine;

import pl.lodz.p.tks.applicationcore.domainmodel.model.machine.Machine;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface GetMachinePort {
    Optional<Machine> findMachineById(UUID machineId);

    public Optional<Machine> findMachineByName(String name);

    public List<Machine> getAll();
}
