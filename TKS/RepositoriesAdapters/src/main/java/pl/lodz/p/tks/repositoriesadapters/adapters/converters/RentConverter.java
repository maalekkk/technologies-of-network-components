package pl.lodz.p.tks.repositoriesadapters.adapters.converters;

import pl.lodz.p.tks.view.domainmodel.model.rent.Rent;
import pl.lodz.p.tks.repositoriesadapters.data.rent.RentEnt;

public class RentConverter {

    public static Rent toDomainModel(RentEnt rentEnt) {
        Rent rent = new Rent(MachineConverter.toDomainModel(rentEnt.getMachine()), UserConverter.toDomainModel(rentEnt.getUser()), PeriodConverter.toDomainModel(rentEnt.getPeriod()));
        rent.setId(rent.getId());
        return rent;
    }

    public static RentEnt fromDomainModel(Rent rent) {
        RentEnt rentEnt = new RentEnt(MachineConverter.fromDomainModel(rent.getMachine()), UserConverter.fromDomainModel(rent.getUser()), PeriodConverter.fromDomainModel(rent.getPeriod()));
        rentEnt.setId(rent.getId());
        return rentEnt;
    }
}
