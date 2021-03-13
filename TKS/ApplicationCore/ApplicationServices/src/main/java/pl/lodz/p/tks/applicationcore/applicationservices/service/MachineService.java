package pl.lodz.p.tks.applicationcore.applicationservices.service;

import pl.lodz.p.tks.applicationcore.domainmodel.model.machine.Machine;
import pl.lodz.p.tks.applicationports.input.MachineUseCase;
import pl.lodz.p.tks.applicationports.output.Machine.DeleteMachinePort;
import pl.lodz.p.tks.applicationports.output.Machine.ExistMachinePort;
import pl.lodz.p.tks.applicationports.output.Machine.GetMachinePort;
import pl.lodz.p.tks.applicationports.output.Machine.SaveMachinePort;

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
    public Machine saveMachine(@Valid Machine machine)
    {
        return saveMachinePort.saveMachine(machine);
    }

    @Override
    public Optional<Machine> findMachineById(UUID machineId)
    {
        return getMachinePort.findMachineById(machineId);
    }

    @Override
    public Optional<Machine> findMachineByName(String name)
    {
        return getMachinePort.findMachineByName(name);
    }

    @Override
    public List<Machine> filterMachineByName(String name)
    {
        return getAll().stream().filter(machine -> machine.getName().equals(name)).collect(Collectors.toList());
    }

    @Override
    public boolean existsMachine(Machine machine)
    {
        return existMachinePort.existsMachineById(machine.getId());
    }

    @Override
    public List<Machine> getAll()
    {
        return getMachinePort.getAll();
    }

    @Override
    public void deleteMachine(Machine machine)
    {
        deleteMachinePort.deleteMachine(machine);
    }
}
