package pl.lodz.p.tks.rent.applicationports.persistance.rent;

import pl.lodz.p.tks.rent.core.domainmodel.rent.Rent;

public interface DeleteRentPort {
    void deleteRent(Rent rent);
}