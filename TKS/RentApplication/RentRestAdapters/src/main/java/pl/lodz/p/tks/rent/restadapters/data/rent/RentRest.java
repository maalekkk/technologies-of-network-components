package pl.lodz.p.tks.rent.restadapters.data.rent;

import pl.lodz.p.tks.rent.restadapters.data.EntityRest;
import pl.lodz.p.tks.rent.restadapters.data.machine.MachineRest;
import pl.lodz.p.tks.rent.restadapters.data.user.UserRest;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class RentRest extends EntityRest {
    @Valid
    @NotNull
    private MachineRest machine;

    @Valid
    @NotNull
    private UserRest user;

    @Valid
    @NotNull
    private PeriodRest period;

    public RentRest() {
        this.period = new PeriodRest();
    }

    public RentRest(MachineRest machine, UserRest user, PeriodRest period) {
        this.machine = machine;
        this.user = user;
        this.period = period;
    }

    public MachineRest getMachine() {
        return machine;
    }

    public void setMachine(MachineRest machine) {
        this.machine = machine;
    }

    public UserRest getUser() {
        return user;
    }

    public void setUser(UserRest user) {
        this.user = user;
    }

    public PeriodRest getPeriod() {
        return period;
    }

    public void setPeriod(PeriodRest period) {
        this.period = period;
    }

    public static class SimpleRent extends EntityRest {
        @NotBlank
        private String machineName;

        @Valid
        @NotNull
        private PeriodRest period;

        public SimpleRent() {
        }

        public SimpleRent(String machineName, PeriodRest period) {
            this.machineName = machineName;
            this.period = period;
        }

        public String getMachineName() {
            return machineName;
        }

        public void setMachineName(String machineName) {
            this.machineName = machineName;
        }

        public PeriodRest getPeriod() {
            return period;
        }

        public void setPeriod(PeriodRest period) {
            this.period = period;
        }
    }
}