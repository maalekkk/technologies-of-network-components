package pl.lodz.p.tks.restadapters.adapters.converters;


import pl.lodz.p.tks.restadapters.data.user.UserRest;
import pl.lodz.p.tks.view.domainmodel.model.user.User;

public class UserConverter {

    public static User toDomainModel(UserRest user) {
        User userDomainModel = new User(user.getUsername(), user.getFullname(), user.isEnabled());
        userDomainModel.setId(user.getId());
        return userDomainModel;
    }

    public static UserRest fromDomainModel(User user) {
        UserRest userRepository = new UserRest(user.getUsername(), user.getFullname(), user.isEnabled());
        userRepository.setId(user.getId());
        return userRepository;
    }
}
