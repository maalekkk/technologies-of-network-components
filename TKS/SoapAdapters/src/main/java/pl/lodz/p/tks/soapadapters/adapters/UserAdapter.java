package pl.lodz.p.tks.soapadapters.adapters;

import pl.lodz.p.tks.applicationports.view.UserUseCase;
import pl.lodz.p.tks.soapadapters.adapters.converters.UserConverter;
import pl.lodz.p.tks.soapadapters.data.user.UserSoap;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.jws.WebService;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Named
@ApplicationScoped
@WebService(endpointInterface = "pl.lodz.p.tks.soapadapters.adapters.IUserAdapter")
public class UserAdapter implements IUserAdapter {

    @Inject
    private UserUseCase userUseCase;

    public UserAdapter() {
    }

    @Override
    public String getName() {
        return userUseCase.getAll().get(0).getFullname();
    }

    @Override
    public void addUser(UserSoap user) {
        userUseCase.saveUser(UserConverter.toDomainModel(user));
    }

    @Override
    public UserSoap getUserById(String userId) {
        return userUseCase.findUserById(UUID.fromString(userId))
                .map(UserConverter::fromDomainModel)
                .orElse(null);
    }

    @Override
    public UserSoap getUserByUsername(String username) {
        return userUseCase.findUserByUsername(username)
                .map(UserConverter::fromDomainModel)
                .orElse(null);
    }

    @Override
    public List<UserSoap> getUsers() {
        return userUseCase.getAll()
                .stream()
                .map(UserConverter::fromDomainModel)
                .collect(Collectors.toList());
    }

    @Override
    public void updateUserById(String userId, UserSoap user) {
        userUseCase.findUserById(UUID.fromString(userId))
                .filter(u -> u.getId().equals(user.getId()) &&
                        u.getUsername().equals(user.getUsername()))
                .map(u -> userUseCase.saveUser(UserConverter.toDomainModel(user)));
    }
}
