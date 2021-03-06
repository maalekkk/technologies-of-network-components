package pl.lodz.p.tks.view.domainmodel.model.rent;

import pl.lodz.p.tks.view.domainmodel.model.Entity;
import pl.lodz.p.tks.view.domainmodel.model.machine.Machine;
import pl.lodz.p.tks.view.domainmodel.model.user.User;

import javax.validation.Valid;

public class Rent extends Entity {
    @Valid
    private Machine machine;

    @Valid
    private User user;

    @Valid
    private Period period;

    public Rent() {
        this.period = new Period();
    }

    public Rent(Machine machine, User user, Period period) {
        this.machine = machine;
        this.user = user;
        this.period = period;
    }

    public Machine getMachine() {
        return machine;
    }

    public void setMachine(Machine machine) {
        this.machine = machine;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Period getPeriod() {
        return period;
    }

    public void setPeriod(Period period) {
        this.period = period;
    }
}