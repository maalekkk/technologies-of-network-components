package pl.lodz.p.tks.repositoriesadapters.adapters.converters;

import pl.lodz.p.tks.view.domainmodel.model.machine.Machine;
import pl.lodz.p.tks.view.domainmodel.model.machine.MachineGaming;
import pl.lodz.p.tks.view.domainmodel.model.machine.MachineWorkstation;
import pl.lodz.p.tks.repositoriesadapters.data.machine.MachineEnt;
import pl.lodz.p.tks.repositoriesadapters.data.machine.MachineGamingEnt;
import pl.lodz.p.tks.repositoriesadapters.data.machine.MachineWorkstationEnt;

public class MachineConverter {

//    public static MachineGaming convertMachineGamingEnt(MachineGamingEnt machineGamingEnt) {
//        MachineGaming machine = new MachineGaming(machineGamingEnt.getName(), machineGamingEnt.getCores(), machineGamingEnt.getRamSize(), machineGamingEnt.getHddSize(), machineGamingEnt.getGpuPower(), machineGamingEnt.getGpuVram());
//        machine.setId(machineGamingEnt.getId());
//        return machine;
//    }
//
//    public static MachineGamingEnt convertMachineGaming(MachineGaming machineGaming) {
//        MachineGamingEnt machine = new MachineGamingEnt(machineGaming.getName(), machineGaming.getCores(), machineGaming.getRamSize(), machineGaming.getHddSize(), machineGaming.getGpuPower(), machineGaming.getGpuVram());
//        machine.setId(machineGaming.getId());
//        return machine;
//    }
//
//    public static MachineWorkstation convertMachineWorkstationEnt(MachineWorkstationEnt machineWorkstationEnt) {
//        MachineWorkstation machine = new MachineWorkstation(machineWorkstationEnt.getName(), machineWorkstationEnt.getCores(), machineWorkstationEnt.getRamSize(), machineWorkstationEnt.getHddSize(), machineWorkstationEnt.getSsdSize(), machineWorkstationEnt.getNetCards(), machineWorkstationEnt.getRaidSupport());
//        machine.setId(machineWorkstationEnt.getId());
//        return machine;
//    }
//
//    public static MachineWorkstationEnt convertMachineWorkstation(MachineWorkstation machineWorkstation) {
//        MachineWorkstationEnt machine = new MachineWorkstationEnt(machineWorkstation.getName(), machineWorkstation.getCores(), machineWorkstation.getRamSize(), machineWorkstation.getHddSize(), machineWorkstation.getSsdSize(), machineWorkstation.getNetCards(), machineWorkstation.getRaidSupport());
//        machine.setId(machineWorkstation.getId());
//        return machine;
//    }

    public static Machine convertMachineEnt(MachineEnt machineEnt) {
        if (machineEnt.getClass().isInstance(MachineGamingEnt.class)) {
            MachineGamingEnt machineGamingEnt = (MachineGamingEnt) machineEnt;
            return new MachineGaming(machineGamingEnt.getName(), machineGamingEnt.getCores(), machineGamingEnt.getRamSize(), machineGamingEnt.getHddSize(), machineGamingEnt.getGpuPower(), machineGamingEnt.getGpuVram());
        }
        else {
            MachineWorkstationEnt machineWorkstationEnt = (MachineWorkstationEnt) machineEnt;
            return new MachineWorkstation(machineWorkstationEnt.getName(), machineWorkstationEnt.getCores(), machineWorkstationEnt.getRamSize(), machineWorkstationEnt.getHddSize(), machineWorkstationEnt.getSsdSize(), machineWorkstationEnt.getNetCards(), machineWorkstationEnt.getRaidSupport());
        }
    }

    public static MachineEnt convertMachine(Machine machine) {
        if (machine.getClass().isInstance(MachineGaming.class)) {
            MachineGaming machineGaming = (MachineGaming) machine;
            return new MachineGamingEnt(machineGaming.getName(), machineGaming.getCores(), machineGaming.getRamSize(), machineGaming.getHddSize(), machineGaming.getGpuPower(), machineGaming.getGpuVram());
        }
        else {
            MachineWorkstation machineWorkstation = (MachineWorkstation) machine;
            return new MachineWorkstationEnt(machineWorkstation.getName(), machineWorkstation.getCores(), machineWorkstation.getRamSize(), machineWorkstation.getHddSize(), machineWorkstation.getSsdSize(), machineWorkstation.getNetCards(), machineWorkstation.getRaidSupport());
        }
    }
}
