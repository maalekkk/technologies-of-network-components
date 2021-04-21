package pl.lodz.p.tks.view.controller.rent;


import pl.lodz.p.tks.rent.applicationports.view.RentUseCase;
import pl.lodz.p.tks.rent.core.domainmodel.rent.Rent;
import pl.lodz.p.tks.rent.core.domainmodel.rent.Status;
import pl.lodz.p.tks.user.core.domainmodel.user.Role;
import pl.lodz.p.tks.user.applicationports.view.UserUseCase;
import pl.lodz.p.tks.user.core.domainmodel.user.User;

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
    private UserUseCase userUseCase;

    @Inject
    private RentUseCase rentUseCase;

    private List<Rent> rents;

    private String machineFilter = "";
    private String userFilter = "";

    @PostConstruct
    private void init()
    {
        if (userUseCase.getCurrentRole() != Role.Owner)
        {
//            rents = rentUseCase.findRentsByUser(userUseCase.getCurrentUser());
        }
        else
        {
            rents = rentUseCase.getAll();
        }
    }

    public String finishRent(Rent rent)
    {
        rentUseCase.finishRent(rent);
        return "show-rents.xhtml?faces-redirect=true";
    }

    public String deleteRent(Rent rent)
    {
        rentUseCase.deleteRent(rent);
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
        rents = rentUseCase.filterRents(predicates);
    }

    public boolean isReserved(Rent rent)
    {
        return rentUseCase.getRentStatus(rent) == Status.RESERVED;
    }

    public boolean isInProgress(Rent rent)
    {
        return rentUseCase.getRentStatus(rent) == Status.IN_PROGRESS;
    }

    public boolean isFinished(Rent rent)
    {
        return rentUseCase.getRentStatus(rent) == Status.FINISHED;
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
