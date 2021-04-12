package pl.lodz.p.tks.restadapters.adapters.converters;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import pl.lodz.p.tks.restadapters.data.machine.MachineGamingRest;
import pl.lodz.p.tks.restadapters.data.machine.MachineRest;
import pl.lodz.p.tks.restadapters.data.machine.MachineWorkstationRest;
import pl.lodz.p.tks.view.domainmodel.model.machine.Machine;
import pl.lodz.p.tks.view.domainmodel.model.machine.MachineGaming;
import pl.lodz.p.tks.view.domainmodel.model.machine.MachineWorkstation;

import java.util.UUID;

import static pl.lodz.p.tks.restadapters.adapters.converters.MachineConverter.fromDomainModel;
import static pl.lodz.p.tks.restadapters.adapters.converters.MachineConverter.toDomainModel;
import static pl.lodz.p.tks.restadapters.adapters.converters.TestUtils.compareMachines;

public class MachineConverterTest {

    private Machine machineGaming;
    private MachineRest machineGamingRest;

    private Machine machineWorkstation;
    private MachineRest machineWorkstationRest;

    @Before
    public void init() {
        machineGaming = new MachineGaming("Acer x21y37", 4, 200, 100, 4, 4);
        machineGamingRest = new MachineGamingRest("Acer x21y37", 4, 200, 100, 4, 4);

        UUID uuid = UUID.randomUUID();
        machineGaming.setId(uuid);
        machineGamingRest.setId(uuid);

        machineWorkstation = new MachineWorkstation("HP JP100pro", 10, 500, 10, 37, 2, true);
        machineWorkstationRest = new MachineWorkstationRest("HP JP100pro", 10, 500, 10, 37, 2, true);

        uuid = UUID.randomUUID();
        machineWorkstation.setId(uuid);
        machineWorkstationRest.setId(uuid);
    }

    @Test
    public void domainToRestConversion() {
        MachineGamingRest gamingRest = (MachineGamingRest) fromDomainModel(machineGaming);
        Assert.assertTrue(compareMachines(gamingRest, machineGamingRest));

        MachineWorkstationRest workstationRest = (MachineWorkstationRest) fromDomainModel(machineWorkstation);
        Assert.assertTrue(compareMachines(workstationRest, machineWorkstationRest));
    }

    @Test
    public void entToDomainConversion() {
        MachineGaming gaming = (MachineGaming) toDomainModel(machineGamingRest);
        Assert.assertTrue(compareMachines(gaming, machineGaming));

        MachineWorkstation workstation = (MachineWorkstation) toDomainModel(machineWorkstationRest);
        Assert.assertTrue(compareMachines(workstation, machineWorkstation));
    }
}
