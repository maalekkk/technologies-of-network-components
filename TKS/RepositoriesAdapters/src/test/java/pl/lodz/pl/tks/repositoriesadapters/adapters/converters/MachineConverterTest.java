package pl.lodz.pl.tks.repositoriesadapters.adapters.converters;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import pl.lodz.p.tks.repositoriesadapters.adapters.converters.MachineConverter;
import pl.lodz.p.tks.repositoriesadapters.data.machine.MachineEnt;
import pl.lodz.p.tks.repositoriesadapters.data.machine.MachineGamingEnt;
import pl.lodz.p.tks.repositoriesadapters.data.machine.MachineWorkstationEnt;
import pl.lodz.p.tks.view.domainmodel.model.machine.Machine;
import pl.lodz.p.tks.view.domainmodel.model.machine.MachineGaming;
import pl.lodz.p.tks.view.domainmodel.model.machine.MachineWorkstation;

import java.util.UUID;

import static pl.lodz.pl.tks.repositoriesadapters.adapters.converters.TestUtils.compareMachines;

public class MachineConverterTest {

    private Machine machineGaming;
    private MachineEnt machineGamingEnt;

    private Machine machineWorkstation;
    private MachineEnt machineWorkstationEnt;

    @Before
    public void init() {
        machineGaming = new MachineGaming("Acer x21y37", 4, 200, 100, 4, 4);
        machineGamingEnt = new MachineGamingEnt("Acer x21y37", 4, 200, 100, 4, 4);

        UUID uuid = UUID.randomUUID();
        machineGaming.setId(uuid);
        machineGamingEnt.setId(uuid);

        machineWorkstation = new MachineWorkstation("HP JP100pro", 10, 500, 10, 37, 2, true);
        machineWorkstationEnt = new MachineWorkstationEnt("HP JP100pro", 10, 500, 10, 37, 2, true);

        uuid = UUID.randomUUID();
        machineWorkstation.setId(uuid);
        machineWorkstationEnt.setId(uuid);
    }

    @Test
    public void domainToEntConversion() {
        MachineGamingEnt gamingEnt = (MachineGamingEnt) MachineConverter.fromDomainModel(machineGaming);
        Assert.assertTrue(compareMachines(gamingEnt, machineGamingEnt));

        MachineWorkstationEnt workstationEnt = (MachineWorkstationEnt) MachineConverter.fromDomainModel(machineWorkstation);
        Assert.assertTrue(compareMachines(workstationEnt, machineWorkstationEnt));
    }

    @Test
    public void entToDomainConversion() {
        MachineGaming gaming = (MachineGaming) MachineConverter.toDomainModel(machineGamingEnt);
        Assert.assertTrue(compareMachines(gaming, machineGaming));

        MachineWorkstation workstation = (MachineWorkstation) MachineConverter.toDomainModel(machineWorkstationEnt);
        Assert.assertTrue(compareMachines(workstation, machineWorkstation));
    }

}
