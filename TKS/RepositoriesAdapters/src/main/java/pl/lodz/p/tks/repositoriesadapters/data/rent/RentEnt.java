package pl.lodz.p.tks.repositoriesadapters.data.rent;

import pl.lodz.p.tks.repositoriesadapters.data.EntityEnt;

import pl.lodz.p.tks.repositoriesadapters.data.machine.MachineEnt;
import pl.lodz.p.tks.repositoriesadapters.data.user.UserEnt;

import javax.validation.Valid;

public class RentEnt extends EntityEnt {
    @Valid
    private MachineEnt machine;

    @Valid
    private UserEnt user;

    @Valid
    private PeriodEnt period;

    public RentEnt() {
        this.period = new PeriodEnt();
    }

    public RentEnt(MachineEnt machine, UserEnt user, PeriodEnt period) {
        this.machine = machine;
        this.user = user;
        this.period = period;
    }

    public MachineEnt getMachine() {
        return machine;
    }

    public void setMachine(MachineEnt machine) {
        this.machine = machine;
    }

    public UserEnt getUser() {
        return user;
    }

    public void setUser(UserEnt user) {
        this.user = user;
    }

    public PeriodEnt getPeriod() {
        return period;
    }

    public void setPeriod(PeriodEnt period) {
        this.period = period;
    }
}