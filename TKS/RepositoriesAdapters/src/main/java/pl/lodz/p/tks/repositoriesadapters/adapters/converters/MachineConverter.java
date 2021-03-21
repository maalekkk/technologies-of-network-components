package pl.lodz.p.tks.repositoriesadapters.adapters.converters;

import pl.lodz.p.tks.view.domainmodel.model.machine.Machine;
import pl.lodz.p.tks.view.domainmodel.model.machine.MachineGaming;
import pl.lodz.p.tks.view.domainmodel.model.machine.MachineWorkstation;
import pl.lodz.p.tks.repositoriesadapters.data.machine.MachineEnt;
import pl.lodz.p.tks.repositoriesadapters.data.machine.MachineGamingEnt;
import pl.lodz.p.tks.repositoriesadapters.data.machine.MachineWorkstationEnt;

public class MachineConverter {

    public static Machine toDomainModel(MachineEnt machineEnt) {
        if (machineEnt instanceof MachineGamingEnt) {
            MachineGamingEnt machineGamingEnt = (MachineGamingEnt) machineEnt;
            MachineGaming machineGaming = new MachineGaming(machineGamingEnt.getName(), machineGamingEnt.getCores(), machineGamingEnt.getRamSize(), machineGamingEnt.getHddSize(), machineGamingEnt.getGpuPower(), machineGamingEnt.getGpuVram());
            machineGaming.setId(machineGamingEnt.getId());
            return machineGaming;
        } else {
            MachineWorkstationEnt machineWorkstationEnt = (MachineWorkstationEnt) machineEnt;
            MachineWorkstation machineWorkstation = new MachineWorkstation(machineWorkstationEnt.getName(), machineWorkstationEnt.getCores(), machineWorkstationEnt.getRamSize(), machineWorkstationEnt.getHddSize(), machineWorkstationEnt.getSsdSize(), machineWorkstationEnt.getNetCards(), machineWorkstationEnt.getRaidSupport());
            machineWorkstation.setId(machineWorkstationEnt.getId());
            return machineWorkstation;
        }
    }

    public static MachineEnt fromDomainModel(Machine machine) {
        if (machine instanceof MachineGaming) {
            MachineGaming machineGaming = (MachineGaming) machine;
            MachineGamingEnt machineGamingEnt = new MachineGamingEnt(machineGaming.getName(), machineGaming.getCores(), machineGaming.getRamSize(), machineGaming.getHddSize(), machineGaming.getGpuPower(), machineGaming.getGpuVram());
            machineGamingEnt.setId(machineGaming.getId());
            return machineGamingEnt;
        } else {
            MachineWorkstation machineWorkstation = (MachineWorkstation) machine;
            MachineWorkstationEnt machineWorkstationEnt = new MachineWorkstationEnt(machineWorkstation.getName(), machineWorkstation.getCores(), machineWorkstation.getRamSize(), machineWorkstation.getHddSize(), machineWorkstation.getSsdSize(), machineWorkstation.getNetCards(), machineWorkstation.getRaidSupport());
            machineWorkstationEnt.setId(machine.getId());
            return machineWorkstationEnt;
        }
    }
}