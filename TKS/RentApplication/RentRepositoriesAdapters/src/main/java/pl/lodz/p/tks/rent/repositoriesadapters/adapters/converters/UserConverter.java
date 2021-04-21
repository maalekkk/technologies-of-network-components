package pl.lodz.p.tks.rent.repositoriesadapters.adapters.converters;

import pl.lodz.p.tks.rent.core.domainmodel.user.Role;
import pl.lodz.p.tks.rent.core.domainmodel.user.User;
import pl.lodz.p.tks.rent.repositoriesadapters.data.user.RoleEnt;
import pl.lodz.p.tks.rent.repositoriesadapters.data.user.UserEnt;

import java.util.Set;
import java.util.stream.Collectors;

public class UserConverter {

    public static User toDomainModel(UserEnt user) {
        User userDomainModel = new User(
                user.getUsername(), user.getFullname(), user.isEnabled(), rolesToDomainModel(user.getRoles()));
        userDomainModel.setId(user.getId());
        return userDomainModel;
    }

    public static UserEnt fromDomainModel(User user) {
        UserEnt userRepository = new UserEnt(
                user.getUsername(), user.getFullname(), user.isEnabled(), rolesFromDomainModel(user.getRoles()));
        userRepository.setId(user.getId());
        return userRepository;
    }

    private static Set<Role> rolesToDomainModel(Set<RoleEnt> roles) {
        return roles.stream().map(role -> Role.values()[role.ordinal()]).collect(Collectors.toSet());
    }

    private static Set<RoleEnt> rolesFromDomainModel(Set<Role> roles) {
        return roles.stream().map(role -> RoleEnt.values()[role.ordinal()]).collect(Collectors.toSet());
    }
}