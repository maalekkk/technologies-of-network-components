package pl.lodz.p.tks.rent.restadapters.adapters.converters;

import pl.lodz.p.tks.rent.restadapters.data.rent.RentRest;
import pl.lodz.p.tks.rent.core.domainmodel.rent.Rent;

public class RentConverter {

    public static Rent toDomainModel(RentRest rentRest) {
        Rent rent = new Rent(
                MachineConverter.toDomainModel(rentRest.getMachine()), UserConverter.toDomainModel(rentRest.getUser()),
                PeriodConverter.toDomainModel(rentRest.getPeriod()));
        rent.setId(rentRest.getId());
        return rent;
    }

    public static RentRest fromDomainModel(Rent rent) {
        RentRest RentRest = new RentRest(
                MachineConverter.fromDomainModel(rent.getMachine()), UserConverter.fromDomainModel(rent.getUser()),
                PeriodConverter.fromDomainModel(rent.getPeriod()));
        RentRest.setId(rent.getId());
        return RentRest;
    }
}