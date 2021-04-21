package pl.lodz.p.tks.rent.applicationports.persistance.rent;

import pl.lodz.p.tks.rent.core.domainmodel.rent.Rent;

import javax.validation.Valid;

public interface SaveRentPort {
    Rent saveRent(@Valid Rent rent);
}