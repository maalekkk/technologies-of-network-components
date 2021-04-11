package pl.lodz.p.tks.restadapters.adapters.converters;

import pl.lodz.p.tks.restadapters.data.user.RoleRest;
import pl.lodz.p.tks.restadapters.data.user.UserRest;
import pl.lodz.p.tks.view.domainmodel.model.user.Role;
import pl.lodz.p.tks.view.domainmodel.model.user.User;

import java.util.Set;
import java.util.stream.Collectors;

public class UserConverter {

    public static User toDomainModel(UserRest user) {
        User userDomainModel = new User(
                user.getUsername(), user.getPassword(), user.getFullname(), user.isEnabled(), rolesToDomainModel(user.getRoles()));
        userDomainModel.setId(user.getId());
        return userDomainModel;
    }

    public static UserRest fromDomainModel(User user) {
        UserRest userRepository = new UserRest(
                user.getUsername(), user.getPassword(), user.getFullname(), user.isEnabled(), rolesFromDomainModel(user.getRoles()));
        userRepository.setId(user.getId());
        return userRepository;
    }

    private static Set<Role> rolesToDomainModel(Set<RoleRest> roles) {
        return roles.stream().map(role -> Role.values()[role.ordinal()]).collect(Collectors.toSet());
    }

    private static Set<RoleRest> rolesFromDomainModel(Set<Role> roles) {
        return roles.stream().map(role -> RoleRest.values()[role.ordinal()]).collect(Collectors.toSet());
    }
}