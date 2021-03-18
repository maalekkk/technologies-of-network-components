package pl.lodz.p.tks.restadapters.adapters.converters;


import pl.lodz.p.tks.restadapters.data.machine.MachineGamingRest;
import pl.lodz.p.tks.restadapters.data.machine.MachineRest;
import pl.lodz.p.tks.restadapters.data.machine.MachineWorkstationRest;
import pl.lodz.p.tks.view.domainmodel.model.machine.Machine;
import pl.lodz.p.tks.view.domainmodel.model.machine.MachineGaming;
import pl.lodz.p.tks.view.domainmodel.model.machine.MachineWorkstation;

public class MachineConverter {

    public static Machine toDomainModel(MachineRest machineRest) {
        if (machineRest.getClass().isInstance(MachineGamingRest.class)) {
            MachineGamingRest machineGamingRest = (MachineGamingRest) machineRest;
            return new MachineGaming(machineGamingRest.getName(), machineGamingRest.getCores(), machineGamingRest.getRamSize(), machineGamingRest.getHddSize(), machineGamingRest.getGpuPower(), machineGamingRest.getGpuVram());
        } else {
            MachineWorkstationRest machineWorkstationRest = (MachineWorkstationRest) machineRest;
            return new MachineWorkstation(machineWorkstationRest.getName(), machineWorkstationRest.getCores(), machineWorkstationRest.getRamSize(), machineWorkstationRest.getHddSize(), machineWorkstationRest.getSsdSize(), machineWorkstationRest.getNetCards(), machineWorkstationRest.getRaidSupport());
        }
    }

    public static MachineRest fromDomainModel(Machine machine) {
        if (machine.getClass().isInstance(MachineGaming.class)) {
            MachineGaming machineGaming = (MachineGaming) machine;
            return new MachineGamingRest(machineGaming.getName(), machineGaming.getCores(), machineGaming.getRamSize(), machineGaming.getHddSize(), machineGaming.getGpuPower(), machineGaming.getGpuVram());
        } else {
            MachineWorkstation machineWorkstation = (MachineWorkstation) machine;
            return new MachineWorkstationRest(machineWorkstation.getName(), machineWorkstation.getCores(), machineWorkstation.getRamSize(), machineWorkstation.getHddSize(), machineWorkstation.getSsdSize(), machineWorkstation.getNetCards(), machineWorkstation.getRaidSupport());
        }
    }
}
