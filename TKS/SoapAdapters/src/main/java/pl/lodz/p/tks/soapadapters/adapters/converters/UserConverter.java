package pl.lodz.p.tks.soapadapters.adapters.converters;

import pl.lodz.p.tks.soapadapters.data.user.UserSoap;
import pl.lodz.p.tks.view.domainmodel.model.user.User;

public class UserConverter {

    public static User toDomainModel(UserSoap user) {
        User userDomainModel = new User(user.getUsername(), user.getFullname(), user.isEnabled());
        userDomainModel.setId(user.getId());
        return userDomainModel;
    }

    public static UserSoap fromDomainModel(User user) {
        UserSoap userRepository = new UserSoap(user.getUsername(), user.getFullname(), user.isEnabled());
        userRepository.setId(user.getId());
        return userRepository;
    }
}