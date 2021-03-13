package pl.lodz.p.tks.repositoriesadapters.adapters.converters;

import pl.lodz.p.tks.view.domainmodel.model.rent.Rent;
import pl.lodz.p.tks.repositoriesadapters.data.rent.RentEnt;

public class RentConverter {

    public static Rent convertRentEnt(RentEnt rentEnt) {
        Rent rent = new Rent(MachineConverter.convertMachineEnt(rentEnt.getMachine()), UserConverter.convertUserEnt(rentEnt.getUser()), PeriodConverter.convertPeriodEnt(rentEnt.getPeriod()));
        rent.setId(rent.getId());
        return rent;
    }

    public static RentEnt convertRent(Rent rent) {
        RentEnt rentEnt = new RentEnt(MachineConverter.convertMachine(rent.getMachine()), UserConverter.convertUser(rent.getUser()), PeriodConverter.convertPeriod(rent.getPeriod()));
        rentEnt.setId(rent.getId());
        return rentEnt;
    }
}
