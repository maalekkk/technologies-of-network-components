package pl.lodz.p.tks.applicationcore.controller.rent;


import pl.lodz.p.tks.applicationcore.applicationservices.service.RentService;
import pl.lodz.p.tks.applicationcore.applicationservices.service.UserService;
import pl.lodz.p.tks.applicationcore.domainmodel.model.rent.Rent;
import pl.lodz.p.tks.applicationcore.domainmodel.model.rent.Status;
import pl.lodz.p.tks.applicationcore.domainmodel.model.user.Role;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

@Named
@ViewScoped
public class RentListController implements Serializable
{
    @Inject
    private UserService userService;

    @Inject
    private RentService rentService;

    private List<Rent> rents;

    private String machineFilter = "";
    private String userFilter = "";

    @PostConstruct
    private void init()
    {
        if (userService.getCurrentRole() != Role.Owner)
        {
            rents = rentService.findRentsByUser(userService.getCurrentUser());
        }
        else
        {
            rents = rentService.getAll();
        }
    }

    public String finishRent(Rent rent)
    {
        rentService.finishRent(rent);
        return "show-rents.xhtml?faces-redirect=true";
    }

    public String deleteRent(Rent rent)
    {
        rentService.deleteRent(rent);
        return "show-rents.xhtml?faces-redirect=true";
    }

    public void filter()
    {
        List<Predicate<Rent>> predicates = new ArrayList<>();
        if (!userFilter.isEmpty())
        {
            predicates.add(rent -> rent.getUser().getUsername().contains(userFilter));
        }
        if (!machineFilter.isEmpty())
        {
            predicates.add(rent -> rent.getMachine().getName().contains(machineFilter));
        }
        rents = rentService.filterRents(predicates);
    }

    public boolean isReserved(Rent rent)
    {
        return rentService.getRentStatus(rent) == Status.RESERVED;
    }

    public boolean isInProgress(Rent rent)
    {
        return rentService.getRentStatus(rent) == Status.IN_PROGRESS;
    }

    public boolean isFinished(Rent rent)
    {
        return rentService.getRentStatus(rent) == Status.FINISHED;
    }

    public List<Rent> getRents()
    {
        return rents;
    }

    public String getMachineFilter()
    {
        return machineFilter;
    }

    public void setMachineFilter(String machineFilter)
    {
        this.machineFilter = machineFilter;
    }

    public String getUserFilter()
    {
        return userFilter;
    }

    public void setUserFilter(String userFilter)
    {
        this.userFilter = userFilter;
    }
}
