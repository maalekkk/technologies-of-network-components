package pl.lodz.p.tks.applicationports.persistence.rent;

import pl.lodz.p.tks.view.domainmodel.model.rent.Rent;

import javax.validation.Valid;

public interface SaveRentPort {
    Rent saveRent(@Valid Rent rent);
}
