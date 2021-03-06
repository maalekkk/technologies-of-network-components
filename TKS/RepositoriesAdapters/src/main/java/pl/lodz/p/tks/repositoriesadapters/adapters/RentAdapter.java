package pl.lodz.p.tks.repositoriesadapters.adapters;

import pl.lodz.p.tks.view.domainmodel.model.rent.Rent;
import pl.lodz.p.tks.view.domainmodel.model.user.User;
import pl.lodz.p.tks.applicationports.persistence.rent.*;
import pl.lodz.p.tks.repositoriesadapters.adapters.converters.RentConverter;
import pl.lodz.p.tks.repositoriesadapters.adapters.converters.UserConverter;
import pl.lodz.p.tks.repositoriesadapters.repository.RentRepository;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.validation.Valid;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@ApplicationScoped
public class RentAdapter implements DeleteRentPort, GetRentPort, SaveRentPort, ExistRentPort {

    @Inject
    RentRepository rentRepository;

    @Override
    public void deleteRent(Rent rent) {
        rentRepository.delete(RentConverter.fromDomainModel(rent));
    }

    @Override
    public Optional<Rent> findRentById(UUID fromString) {
        return rentRepository.findById(fromString)
                .map(RentConverter::toDomainModel);
    }

    @Override
    public List<Rent> findRentsByUser(User user) {
        return rentRepository.findByPredicate(rentEnt -> rentEnt.getUser().equals(UserConverter.fromDomainModel(user)))
                .stream()
                .map(RentConverter::toDomainModel)
                .collect(Collectors.toList());
    }

    @Override
    public List<Rent> findByPredicate(Predicate<Rent> predicate) {
        return getAll()
                .stream()
                .filter(predicate)
                .collect(Collectors.toList());
    }

    @Override
    public List<Rent> getAll() {
        return rentRepository.findAll()
                .stream()
                .map(RentConverter::toDomainModel)
                .collect(Collectors.toList());
    }

    @Override
    public Rent saveRent(@Valid Rent rent) {
        return RentConverter.toDomainModel(rentRepository.save(RentConverter.fromDomainModel(rent)));
    }

    @Override
    public boolean existsRentById(UUID uuid) {
        return rentRepository.existsById(uuid);
    }
}