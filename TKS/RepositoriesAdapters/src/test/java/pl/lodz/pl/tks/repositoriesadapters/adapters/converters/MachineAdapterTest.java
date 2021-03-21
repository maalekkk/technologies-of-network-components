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

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.UUID;

public class MachineAdapterTest {

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

    private boolean compareMachines(Object machine1, Object machine2) {
        if (!machine1.getClass().equals(machine2.getClass())) {
            return false;
        }
        if (!(machine1 instanceof Machine || machine1 instanceof MachineEnt)) {
            return false;
        }
        try {
            boolean notEqual = !compareObjectsPropertiesByName(machine1, machine2, UUID.class, "getId");
            notEqual |= !compareObjectsPropertiesByName(machine1, machine2, String.class, "getName");
            notEqual |= !compareObjectsPropertiesByName(machine1, machine2, Integer.class, "getCores");
            notEqual |= !compareObjectsPropertiesByName(machine1, machine2, Integer.class, "getRamSize");
            notEqual |= !compareObjectsPropertiesByName(machine1, machine2, Integer.class, "getHddSize");

            if(machine1 instanceof MachineGaming || machine1 instanceof MachineGamingEnt) {
                notEqual |= !compareObjectsPropertiesByName(machine1, machine2, Integer.class, "getGpuPower");
                notEqual |= !compareObjectsPropertiesByName(machine1, machine2, Integer.class, "getGpuVram");
            } else if(machine1 instanceof MachineWorkstation || machine1 instanceof MachineWorkstationEnt) {
                notEqual |= !compareObjectsPropertiesByName(machine1, machine2, Integer.class, "getSsdSize");
                notEqual |= !compareObjectsPropertiesByName(machine1, machine2, Integer.class, "getNetCards");
                notEqual |= !compareObjectsPropertiesByName(machine1, machine2, Boolean.class, "getRaidSupport");
            }

            if(notEqual) {
                return false;
            }
        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
        return true;
    }

    private <T> boolean compareObjectsPropertiesByName(Object obj1, Object obj2, Class<T> type, String propertyName) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Method getNameM1 = obj1.getClass().getMethod(propertyName);
        Method getNameM2 = obj2.getClass().getMethod(propertyName);

        T nameM1 = type.cast(getNameM1.invoke(obj1));
        T nameM2 = type.cast(getNameM2.invoke(obj2));
        return nameM1.equals(nameM2);
    }
}
