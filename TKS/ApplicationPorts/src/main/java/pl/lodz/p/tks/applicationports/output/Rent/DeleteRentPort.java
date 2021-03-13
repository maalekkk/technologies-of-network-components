package pl.lodz.p.tks.applicationports.output.Rent;

import pl.lodz.p.tks.view.domainmodel.model.rent.Rent;

public interface DeleteRentPort {
    void deleteRent(Rent rent);
}
