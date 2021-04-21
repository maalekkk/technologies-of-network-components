package pl.lodz.p.tks.rent.core.services;

import pl.lodz.p.tks.rent.applicationports.persistance.machine.DeleteMachinePort;
import pl.lodz.p.tks.rent.applicationports.persistance.machine.ExistMachinePort;
import pl.lodz.p.tks.rent.applicationports.persistance.machine.GetMachinePort;
import pl.lodz.p.tks.rent.applicationports.persistance.machine.SaveMachinePort;
import pl.lodz.p.tks.rent.applicationports.view.MachineUseCase;
import pl.lodz.p.tks.rent.core.domainmodel.machine.Machine;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.validation.Valid;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@RequestScoped
public class MachineService implements MachineUseCase {
    @Inject
    private DeleteMachinePort deleteMachinePort;

    @Inject
    private GetMachinePort getMachinePort;

    @Inject
    private SaveMachinePort saveMachinePort;

    @Inject
    private ExistMachinePort existMachinePort;

    @Override
    public Machine saveMachine(@Valid Machine machine) {
        return saveMachinePort.saveMachine(machine);
    }

    @Override
    public Optional<Machine> findMachineById(UUID machineId) {
        return getMachinePort.findMachineById(machineId);
    }

    @Override
    public Optional<Machine> findMachineByName(String name) {
        return getMachinePort.findMachineByName(name);
    }

    @Override
    public List<Machine> filterMachineByName(String name) {
        return getAll().stream().filter(machine -> machine.getName().equals(name)).collect(Collectors.toList());
    }

    @Override
    public boolean existsMachine(Machine machine) {
        return existMachinePort.existsMachineById(machine.getId());
    }

    @Override
    public List<Machine> getAll() {
        return getMachinePort.getAll();
    }

    @Override
    public boolean deleteMachine(Machine machine) {
        return deleteMachinePort.deleteMachine(machine);
    }

    public boolean deleteMachineById(UUID machineId) {
        return deleteMachinePort.deleteById(machineId);
    }
}