package pl.lodz.p.tks.repositoriesadapters.adapters;

import pl.lodz.p.tks.view.domainmodel.model.machine.Machine;
import pl.lodz.p.tks.applicationports.persistence.machine.DeleteMachinePort;
import pl.lodz.p.tks.applicationports.persistence.machine.ExistMachinePort;
import pl.lodz.p.tks.applicationports.persistence.machine.GetMachinePort;
import pl.lodz.p.tks.applicationports.persistence.machine.SaveMachinePort;
import pl.lodz.p.tks.repositoriesadapters.adapters.converters.MachineConverter;
import pl.lodz.p.tks.repositoriesadapters.data.machine.MachineEnt;
import pl.lodz.p.tks.repositoriesadapters.repository.MachineRepository;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@ApplicationScoped
public class MachineAdapter implements DeleteMachinePort, GetMachinePort, SaveMachinePort, ExistMachinePort {

    @Inject
    private MachineRepository machineRepository;

    @Override
    public void deleteMachine(Machine machine) {
        machineRepository.delete(MachineConverter.fromDomainModel(machine));
    }

    @Override
    public boolean deleteById(UUID machineId) {
        return machineRepository.deleteById(machineId);
    }

    @Override
    public Optional<Machine> findMachineById(UUID machineId) {
        return machineRepository.findById(machineId).map(MachineConverter::toDomainModel);
    }

    @Override
    public Optional<Machine> findMachineByName(String name) {
        return machineRepository.findByUniquePredicate(machineEnt -> machineEnt.getName().equals(name)).map(MachineConverter::toDomainModel);
    }

    @Override
    public List<Machine> getAll() {
        List<Machine> machines = new ArrayList<>();
        for(MachineEnt machineEnt : machineRepository.findAll()) {
            machines.add(MachineConverter.toDomainModel(machineEnt));
        }
        return machines;
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
