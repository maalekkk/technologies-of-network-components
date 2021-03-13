package pl.lodz.p.tks.applicationports.output.Rent;

import pl.lodz.p.tks.applicationcore.domainmodel.model.rent.Rent;

public interface DeleteRentPort {
    void deleteRent(Rent rent);
}
