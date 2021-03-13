package pl.lodz.p.tks.view.controller.rent;

import pl.lodz.p.tks.applicationports.input.MachineUseCase;
import pl.lodz.p.tks.applicationports.input.RentUseCase;
import pl.lodz.p.tks.applicationports.input.UserUseCase;
import pl.lodz.p.tks.view.domainmodel.model.rent.Rent;
import pl.lodz.p.tks.view.domainmodel.model.user.Role;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.UUID;

@Named
@ViewScoped
public class RentController implements Serializable
{
    private Rent rent = new Rent();

    private UUID machineId;

    @Inject
    private RentUseCase rentUseCase;

    @Inject
    private MachineUseCase machineUseCase;

    @Inject
    private UserUseCase userUseCase;

    @PostConstruct
    private void init()
    {
        rent.setUser(userUseCase.getCurrentUser());
    }

    public String initRent()
    {
        rentUseCase.findRentById(rent.getId()).ifPresent(r -> rent = r);
        return rentUseCase.existsRent(rent) &&
                (userUseCase.getCurrentRole() == Role.Owner ||
                        rent.getUser().equals(userUseCase.getCurrentUser())) ? null : "error";
    }

    public String initRentMachine()
    {
        machineUseCase.findMachineById(machineId).ifPresent(m -> rent.setMachine(m));
        return machineUseCase.existsMachine(rent.getMachine()) ? null : "error";
    }

    public String submit()
    {
        return rentUseCase.saveRent(rent) == null ? null : "show-rents.xhtml?faces-redirect=true";
    }

    public Rent getRent()
    {
        return rent;
    }

    public void setRent(Rent rent)
    {
        this.rent = rent;
    }

    public UUID getMachineId()
    {
        return machineId;
    }

    public void setMachineId(UUID machineId)
    {
        this.machineId = machineId;
    }
}
