package pl.lodz.p.tks.repositoriesadapters.adapters.converters;

import pl.lodz.p.tks.view.domainmodel.model.machine.Machine;
import pl.lodz.p.tks.view.domainmodel.model.machine.MachineGaming;
import pl.lodz.p.tks.view.domainmodel.model.machine.MachineWorkstation;
import pl.lodz.p.tks.repositoriesadapters.data.machine.MachineEnt;
import pl.lodz.p.tks.repositoriesadapters.data.machine.MachineGamingEnt;
import pl.lodz.p.tks.repositoriesadapters.data.machine.MachineWorkstationEnt;

public class MachineConverter {

    public static Machine toDomainModel(MachineEnt machineEnt) {
        if (machineEnt.getClass().isInstance(MachineGamingEnt.class)) {
            MachineGamingEnt machineGamingEnt = (MachineGamingEnt) machineEnt;
            return new MachineGaming(machineGamingEnt.getName(), machineGamingEnt.getCores(), machineGamingEnt.getRamSize(), machineGamingEnt.getHddSize(), machineGamingEnt.getGpuPower(), machineGamingEnt.getGpuVram());
        } else {
            MachineWorkstationEnt machineWorkstationEnt = (MachineWorkstationEnt) machineEnt;
            return new MachineWorkstation(machineWorkstationEnt.getName(), machineWorkstationEnt.getCores(), machineWorkstationEnt.getRamSize(), machineWorkstationEnt.getHddSize(), machineWorkstationEnt.getSsdSize(), machineWorkstationEnt.getNetCards(), machineWorkstationEnt.getRaidSupport());
        }
    }

    public static MachineEnt fromDomainModel(Machine machine) {
        if (machine.getClass().isInstance(MachineGaming.class)) {
            MachineGaming machineGaming = (MachineGaming) machine;
            return new MachineGamingEnt(machineGaming.getName(), machineGaming.getCores(), machineGaming.getRamSize(), machineGaming.getHddSize(), machineGaming.getGpuPower(), machineGaming.getGpuVram());
        } else {
            MachineWorkstation machineWorkstation = (MachineWorkstation) machine;
            return new MachineWorkstationEnt(machineWorkstation.getName(), machineWorkstation.getCores(), machineWorkstation.getRamSize(), machineWorkstation.getHddSize(), machineWorkstation.getSsdSize(), machineWorkstation.getNetCards(), machineWorkstation.getRaidSupport());
        }
    }
}
