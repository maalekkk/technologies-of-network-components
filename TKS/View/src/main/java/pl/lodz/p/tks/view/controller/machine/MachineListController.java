package pl.lodz.p.tks.view.controller.machine;

import pl.lodz.p.tks.applicationports.input.MachineUseCase;
import pl.lodz.p.tks.applicationports.input.RentUseCase;
import pl.lodz.p.tks.view.domainmodel.model.machine.Machine;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;

@Named
@ViewScoped
public class MachineListController implements Serializable
{
    @Inject
    private MachineUseCase machineUseCase;

    @Inject
    private RentUseCase rentUseCase;

    private List<Machine> machines;

    private String machineFilter = "";

    @PostConstruct
    public void init()
    {
        machines = machineUseCase.getAll();
    }

    public String deleteMachine(Machine machine)
    {
        if (rentUseCase.isMachineAllocated(machine))
        {
            return null;
        }
        machineUseCase.deleteMachine(machine);
        return "show-vms.xhtml?faces-redirect=true";
    }

    public void filter()
    {
        if (!machineFilter.isEmpty())
        {
            machines = machineUseCase.filterMachineByName(machineFilter);
        }
        else
        {
            machines = machineUseCase.getAll();
        }
    }

    public boolean isAllocated(Machine machine)
    {
        return rentUseCase.isMachineAllocated(machine);
    }

    public List<Machine> getMachines()
    {
        return machines;
    }

    public String getMachineFilter()
    {
        return machineFilter;
    }

    public void setMachineFilter(String machineFilter)
    {
        this.machineFilter = machineFilter;
    }
}
