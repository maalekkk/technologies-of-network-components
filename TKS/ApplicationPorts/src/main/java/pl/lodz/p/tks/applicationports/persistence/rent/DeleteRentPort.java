package pl.lodz.p.tks.applicationports.persistence.rent;

import pl.lodz.p.tks.view.domainmodel.model.rent.Rent;

public interface DeleteRentPort {
    void deleteRent(Rent rent);
}
