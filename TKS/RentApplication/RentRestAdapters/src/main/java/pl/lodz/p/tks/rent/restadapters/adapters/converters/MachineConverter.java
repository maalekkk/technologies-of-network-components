package pl.lodz.p.tks.rent.restadapters.adapters.converters;

import pl.lodz.p.tks.rent.core.domainmodel.machine.Machine;
import pl.lodz.p.tks.rent.core.domainmodel.machine.MachineGaming;
import pl.lodz.p.tks.rent.core.domainmodel.machine.MachineWorkstation;
import pl.lodz.p.tks.rent.restadapters.data.machine.MachineGamingRest;
import pl.lodz.p.tks.rent.restadapters.data.machine.MachineRest;
import pl.lodz.p.tks.rent.restadapters.data.machine.MachineWorkstationRest;

public class MachineConverter {

    public static Machine toDomainModel(MachineRest machineRest) {
        if (machineRest instanceof MachineGamingRest) {
            MachineGamingRest machineGamingRest = (MachineGamingRest) machineRest;
            MachineGaming machineGaming = new MachineGaming(
                    machineGamingRest.getName(), machineGamingRest.getCores(), machineGamingRest.getRamSize(),
                    machineGamingRest.getHddSize(), machineGamingRest.getGpuPower(), machineGamingRest.getGpuVram());
            machineGaming.setId(machineGamingRest.getId());
            return machineGaming;
        } else {
            MachineWorkstationRest machineWorkstationRest = (MachineWorkstationRest) machineRest;
            MachineWorkstation machineWorkstation = new MachineWorkstation(
                    machineWorkstationRest.getName(), machineWorkstationRest.getCores(), machineWorkstationRest.getRamSize(),
                    machineWorkstationRest.getHddSize(), machineWorkstationRest.getSsdSize(), machineWorkstationRest.getNetCards(),
                    machineWorkstationRest.getRaidSupport());
            machineWorkstation.setId(machineWorkstationRest.getId());
            return machineWorkstation;
        }
    }

    public static MachineRest fromDomainModel(Machine machine) {
        if (machine instanceof MachineGaming) {
            MachineGaming machineGaming = (MachineGaming) machine;
            MachineGamingRest machineGamingRest = new MachineGamingRest(
                    machineGaming.getName(), machineGaming.getCores(), machineGaming.getRamSize(), machineGaming.getHddSize(),
                    machineGaming.getGpuPower(), machineGaming.getGpuVram());
            machineGamingRest.setId(machineGaming.getId());
            return machineGamingRest;
        } else {
            MachineWorkstation machineWorkstation = (MachineWorkstation) machine;
            MachineWorkstationRest machineWorkstationRest = new MachineWorkstationRest(
                    machineWorkstation.getName(), machineWorkstation.getCores(), machineWorkstation.getRamSize(),
                    machineWorkstation.getHddSize(), machineWorkstation.getSsdSize(), machineWorkstation.getNetCards(),
                    machineWorkstation.getRaidSupport());
            machineWorkstationRest.setId(machineWorkstation.getId());
            return machineWorkstationRest;
        }
    }
}