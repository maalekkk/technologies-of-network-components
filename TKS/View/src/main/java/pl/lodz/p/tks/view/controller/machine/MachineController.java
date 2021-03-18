package pl.lodz.p.tks.view.controller.machine;

import pl.lodz.p.tks.applicationports.view.MachineUseCase;
import pl.lodz.p.tks.applicationports.view.RentUseCase;
import pl.lodz.p.tks.view.domainmodel.model.machine.Machine;
import pl.lodz.p.tks.view.domainmodel.model.machine.MachineGaming;
import pl.lodz.p.tks.view.domainmodel.model.machine.MachineWorkstation;
import pl.lodz.p.tks.view.validator.unique.machinename.UniqueMachineName;

import javax.faces.event.ValueChangeEvent;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Named
@ViewScoped
public class MachineController implements Serializable {
    @Inject
    private MachineUseCase machineUseCase;

    @Inject
    private RentUseCase rentUseCase;

    @UniqueMachineName
    @Size(min = 3, max = 20)
    private String machineName;

    private Machine machine = new MachineGaming();

    public String initMachine() {
        machineUseCase.findMachineById(machine.getId()).ifPresent(m -> machine = m);
        return !rentUseCase.isMachineAllocated(machine) ? null : "error";
    }

    public String submit() {
        if (machine.getName() == null) {
            machine.setName(machineName);
        }
        machineUseCase.saveMachine(machine);
        return "/dashboard/show-vms?faces-redirect=true";
    }

    public void onTypeChange(ValueChangeEvent event) {
        String type = (String) event.getNewValue();
        switch (type) {
            case "Gaming": {
                machine = new MachineGaming();
                break;
            }
            case "Workstation": {
                machine = new MachineWorkstation();
                break;
            }
        }
    }

    public boolean isUpdate() {
        return machineUseCase.existsMachine(machine);
    }

    public Machine getMachine() {
        return machine;
    }

    public void setMachine(Machine machine) {
        this.machine = machine;
    }

    public MachineGaming getMachineGaming() {
        return machine instanceof MachineGaming ? (MachineGaming) machine : null;
    }

    public MachineWorkstation getMachineWorkstation() {
        return machine instanceof MachineWorkstation ? (MachineWorkstation) machine : null;
    }

    public String getMachineName() {
        return machineName;
    }

    public void setMachineName(String machineName) {
        this.machineName = machineName;
    }
}
