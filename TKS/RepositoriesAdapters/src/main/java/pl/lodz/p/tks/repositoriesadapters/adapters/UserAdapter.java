package pl.lodz.p.tks.repositoriesadapters.adapters;

import pl.lodz.p.tks.applicationports.persistence.user.ExistUserPort;
import pl.lodz.p.tks.applicationports.persistence.user.GetUserPort;
import pl.lodz.p.tks.applicationports.persistence.user.SaveUserPort;
import pl.lodz.p.tks.repositoriesadapters.adapters.converters.UserConverter;
import pl.lodz.p.tks.repositoriesadapters.repository.UserRepository;
import pl.lodz.p.tks.view.domainmodel.model.user.User;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.validation.Valid;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@ApplicationScoped
public class UserAdapter implements GetUserPort, SaveUserPort, ExistUserPort {

    @Inject
    UserRepository userRepository;

    @Override
    public Optional<User> findUserById(UUID userId) {
        return userRepository.findById(userId)
                .map(UserConverter::toDomainModel);
    }

    @Override
    public Optional<User> findUserByUsername(String username) {
        return userRepository.findByUniquePredicate(userEnt -> userEnt.getUsername().equals(username))
                .map(UserConverter::toDomainModel);
    }

    @Override
    public List<User> findByPredicate(Predicate<User> predicate) {
        return getAll()
                .stream()
                .filter(predicate)
                .collect(Collectors.toList());
    }

    @Override
    public List<User> getAll() {
        return userRepository.findAll()
                .stream()
                .map(UserConverter::toDomainModel)
                .collect(Collectors.toList());
    }

    @Override
    public User saveUser(@Valid User user) {
        return UserConverter.toDomainModel(userRepository.save(UserConverter.fromDomainModel(user)));
    }

    @Override
    public boolean existsUserById(UUID uuid) {
        return userRepository.existsById(uuid);
    }
}