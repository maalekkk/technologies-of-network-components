package pl.lodz.p.tks.user.repositoriesadapters.adapters.converters;

import pl.lodz.p.tks.user.core.domainmodel.user.Role;
import pl.lodz.p.tks.user.core.domainmodel.user.User;
import pl.lodz.p.tks.user.repositoriesadapters.data.user.RoleEnt;
import pl.lodz.p.tks.user.repositoriesadapters.data.user.UserEnt;

import java.util.Set;
import java.util.stream.Collectors;

public class UserConverter {

    public static User toDomainModel(UserEnt user) {
        User userDomainModel = new User(
                user.getUsername(), user.getPassword(), user.getFullname(), user.isEnabled(), rolesToDomainModel(user.getRoles()));
        userDomainModel.setId(user.getId());
        return userDomainModel;
    }

    public static UserEnt fromDomainModel(User user) {
        UserEnt userRepository = new UserEnt(
                user.getUsername(), user.getPassword(), user.getFullname(), user.isEnabled(), rolesFromDomainModel(user.getRoles()));
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