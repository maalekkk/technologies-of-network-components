package pl.lodz.p.tks.rent.repositoriesadapters.repository;

import pl.lodz.p.tks.rent.repositoriesadapters.data.machine.MachineEnt;
import pl.lodz.p.tks.rent.repositoriesadapters.data.machine.MachineGamingEnt;
import pl.lodz.p.tks.rent.repositoriesadapters.data.machine.MachineWorkstationEnt;
import pl.lodz.p.tks.rent.repositoriesadapters.repository.generator.UuidGenerator;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.validation.Valid;
import java.util.UUID;

@ApplicationScoped
public class MachineRepository extends InMemoryRepository<@Valid MachineEnt, UUID> {
    public MachineRepository() {
        super(new UuidGenerator());
    }

    @PostConstruct
    private void init() {
        MachineGamingEnt mg1 = new MachineGamingEnt("Predator", 8, 16384, 4096, 15, 8192);
        MachineGamingEnt mg2 = new MachineGamingEnt("G4mer", 4, 8192, 2048, 8, 4096);
        MachineWorkstationEnt mw1 = new MachineWorkstationEnt("Developex", 16, 32768, 8192, 1024, 3, true);
        MachineWorkstationEnt mw2 = new MachineWorkstationEnt("W0rker", 8, 16384, 4096, 512, 2, false);
        save(mg1);
        save(mg2);
        save(mw1);
        save(mw2);
    }
}