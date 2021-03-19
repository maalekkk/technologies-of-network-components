package pl.lodz.p.tks.repositoriesadapters.adapters.converters;

import pl.lodz.p.tks.view.domainmodel.model.user.User;
import pl.lodz.p.tks.repositoriesadapters.data.user.UserEnt;

public class UserConverter {

    public static User toDomainModel(UserEnt user) {
        User userDomainModel = new User(user.getUsername(), user.getFullname(), user.isEnabled());
        userDomainModel.setId(user.getId());
        return userDomainModel;
    }

    public static UserEnt fromDomainModel(User user) {
        UserEnt userRepository = new UserEnt(user.getUsername(), user.getFullname(), user.isEnabled());
        userRepository.setId(user.getId());
        return userRepository;
    }
}