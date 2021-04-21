package pl.lodz.p.tks.rent.repositoriesadapters.adapters;

import pl.lodz.p.tks.rent.applicationports.persistance.machine.DeleteMachinePort;
import pl.lodz.p.tks.rent.applicationports.persistance.machine.ExistMachinePort;
import pl.lodz.p.tks.rent.applicationports.persistance.machine.GetMachinePort;
import pl.lodz.p.tks.rent.applicationports.persistance.machine.SaveMachinePort;
import pl.lodz.p.tks.rent.core.domainmodel.machine.Machine;
import pl.lodz.p.tks.rent.repositoriesadapters.adapters.converters.MachineConverter;
import pl.lodz.p.tks.rent.repositoriesadapters.repository.MachineRepository;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.validation.Valid;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@ApplicationScoped
public class MachineAdapter implements DeleteMachinePort, GetMachinePort, SaveMachinePort, ExistMachinePort {

    @Inject
    private MachineRepository machineRepository;

    @Override
    public boolean deleteMachine(Machine machine) {
        return machineRepository.delete(MachineConverter.fromDomainModel(machine));
    }

    @Override
    public boolean deleteById(UUID machineId) {
        return machineRepository.deleteById(machineId);
    }

    @Override
    public Optional<Machine> findMachineById(UUID machineId) {
        return machineRepository.findById(machineId)
                .map(MachineConverter::toDomainModel);
    }

    @Override
    public Optional<Machine> findMachineByName(String name) {
        return machineRepository.findByUniquePredicate(machineEnt -> machineEnt.getName().equals(name))
                .map(MachineConverter::toDomainModel);
    }

    @Override
    public List<Machine> getAll() {
        return machineRepository.findAll()
                .stream()
                .map(MachineConverter::toDomainModel)
                .collect(Collectors.toList());
    }

    @Override
    public Machine saveMachine(@Valid Machine machine) {
        return MachineConverter.toDomainModel(machineRepository.save(MachineConverter.fromDomainModel(machine)));
    }

    @Override
    public boolean existsMachineById(UUID uuid) {
        return machineRepository.existsById(uuid);
    }
}