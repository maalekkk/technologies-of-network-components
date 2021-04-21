package pl.lodz.p.tks.rent.core.domainmodel.user;


import pl.lodz.p.tks.rent.core.domainmodel.Entity;

import javax.json.bind.annotation.JsonbTransient;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class User extends Entity {
    @Size(min = 3, max = 20)
    @NotBlank
    private String username;

    @NotBlank
    private String fullname;

    private boolean enabled;

    private Set<Role> roles;

    public User() {
        this.roles = new HashSet<>();
        this.roles.add(Role.Client);
    }

    public User(String username, String fullname, boolean enabled, @NotEmpty Set<Role> roles) {
        this.username = username;
        this.fullname = fullname;
        this.enabled = enabled;
        this.roles = roles;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(@NotEmpty Set<Role> roles) {
        this.roles = roles;
    }

    @JsonbTransient
    public Set<String> getRolesAsString() {
        return roles.stream().map(Role::name).collect(Collectors.toSet());
    }
}