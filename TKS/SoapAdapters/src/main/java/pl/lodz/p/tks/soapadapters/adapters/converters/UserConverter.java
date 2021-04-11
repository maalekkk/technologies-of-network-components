package pl.lodz.p.tks.soapadapters.adapters.converters;

import pl.lodz.p.tks.soapadapters.data.user.RoleSoap;
import pl.lodz.p.tks.soapadapters.data.user.UserSoap;
import pl.lodz.p.tks.view.domainmodel.model.user.Role;
import pl.lodz.p.tks.view.domainmodel.model.user.User;

import java.util.Set;
import java.util.stream.Collectors;

public class UserConverter {

    public static User toDomainModel(UserSoap user) {
        User userDomainModel = new User(
                user.getUsername(), user.getPassword(), user.getFullname(), user.isEnabled(), rolesToDomainModel(user.getRoles()));
        userDomainModel.setId(user.getId());
        return userDomainModel;
    }

    public static UserSoap fromDomainModel(User user) {
        UserSoap userRepository = new UserSoap(
                user.getUsername(), user.getPassword(), user.getFullname(), user.isEnabled(), rolesFromDomainModel(user.getRoles()));
        userRepository.setId(user.getId());
        return userRepository;
    }

    private static Set<Role> rolesToDomainModel(Set<RoleSoap> roles) {
        return roles.stream().map(role -> Role.values()[role.ordinal()]).collect(Collectors.toSet());
    }

    private static Set<RoleSoap> rolesFromDomainModel(Set<Role> roles) {
        return roles.stream().map(role -> RoleSoap.values()[role.ordinal()]).collect(Collectors.toSet());
    }
}