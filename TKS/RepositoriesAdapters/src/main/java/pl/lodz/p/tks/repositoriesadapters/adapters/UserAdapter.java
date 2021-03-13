package pl.lodz.p.tks.repositoriesadapters.adapters;

import pl.lodz.p.tks.view.domainmodel.model.user.User;
import pl.lodz.p.tks.applicationports.output.User.GetUserPort;
import pl.lodz.p.tks.applicationports.output.User.SaveUserPort;
import pl.lodz.p.tks.repositoriesadapters.adapters.converters.UserConverter;
import pl.lodz.p.tks.repositoriesadapters.data.user.UserEnt;
import pl.lodz.p.tks.repositoriesadapters.repository.UserRepository;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@ApplicationScoped
public class UserAdapter implements GetUserPort, SaveUserPort {

    @Inject
    UserRepository userRepository;

    @Override
    public Optional<User> findUserById(UUID userId) {
        return userRepository.findById(userId).map(UserConverter::convertUserEnt);
    }

    @Override
    public Optional<User> findUserByUsername(String username) {
        return userRepository.findByUniquePredicate(userEnt -> userEnt.getUsername().equals(username)).map(UserConverter::convertUserEnt);
    }

    @Override
    public List<User> findByPredicate(Predicate<User> predicate) {
        return getAll().stream().filter(predicate).collect(Collectors.toList());
    }

    @Override
    public List<User> getAll() {
        List<User> users = new ArrayList<>();
        for (UserEnt userEnt : userRepository.findAll()) {
            users.add(UserConverter.convertUserEnt(userEnt));
        }
        return users;
    }

    @Override
    public User saveUser(@Valid User user) {
        return UserConverter.convertUserEnt(userRepository.save(UserConverter.convertUser(user)));
    }
}
