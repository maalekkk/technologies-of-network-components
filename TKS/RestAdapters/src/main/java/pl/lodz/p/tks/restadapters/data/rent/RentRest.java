package pl.lodz.p.tks.restadapters.data.rent;


import pl.lodz.p.tks.restadapters.data.EntityRest;
import pl.lodz.p.tks.restadapters.data.machine.MachineRest;
import pl.lodz.p.tks.restadapters.data.user.UserRest;

import javax.validation.Valid;

public class RentRest extends EntityRest
{
    @Valid
    private MachineRest machine;

    @Valid
    private UserRest user;

    @Valid
    private PeriodRest period;

    public RentRest()
    {
        this.period = new PeriodRest();
    }

    public RentRest(MachineRest machine, UserRest user, PeriodRest period)
    {
        this.machine = machine;
        this.user = user;
        this.period = period;
    }

    public MachineRest getMachine()
    {
        return machine;
    }

    public void setMachine(MachineRest machine)
    {
        this.machine = machine;
    }

    public UserRest getUser()
    {
        return user;
    }

    public void setUser(UserRest user)
    {
        this.user = user;
    }

    public PeriodRest getPeriod()
    {
        return period;
    }

    public void setPeriod(PeriodRest period)
    {
        this.period = period;
    }
}
