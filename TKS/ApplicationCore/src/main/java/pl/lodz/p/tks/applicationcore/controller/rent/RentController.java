package pl.lodz.p.tks.applicationcore.controller.rent;

import pl.lodz.p.tks.applicationcore.applicationservices.service.MachineService;
import pl.lodz.p.tks.applicationcore.applicationservices.service.RentService;
import pl.lodz.p.tks.applicationcore.applicationservices.service.UserService;
import pl.lodz.p.tks.applicationcore.domainmodel.model.rent.Rent;
import pl.lodz.p.tks.applicationcore.domainmodel.model.user.Role;

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
    private RentService rentService;

    @Inject
    private MachineService machineService;

    @Inject
    private UserService userService;

    @PostConstruct
    private void init()
    {
        rent.setUser(userService.getCurrentUser());
    }

    public String initRent()
    {
        rentService.findRentById(rent.getId()).ifPresent(r -> rent = r);
        return rentService.existsRent(rent) &&
                (userService.getCurrentRole() == Role.Owner ||
                        rent.getUser().equals(userService.getCurrentUser())) ? null : "error";
    }

    public String initRentMachine()
    {
        machineService.findMachineById(machineId).ifPresent(m -> rent.setMachine(m));
        return machineService.existsMachine(rent.getMachine()) ? null : "error";
    }

    public String submit()
    {
        return rentService.saveRent(rent) == null ? null : "show-rents.xhtml?faces-redirect=true";
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
