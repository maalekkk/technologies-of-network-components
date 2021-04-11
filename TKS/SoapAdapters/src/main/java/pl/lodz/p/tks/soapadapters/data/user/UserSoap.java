package pl.lodz.p.tks.soapadapters.data.user;

import pl.lodz.p.tks.soapadapters.data.EntitySoap;
import pl.lodz.p.tks.view.domainmodel.model.user.Role;

import javax.json.bind.annotation.JsonbTransient;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class UserSoap extends EntitySoap {
    @Size(min = 3, max = 20)
    @NotBlank
    private String username;

    @Size(min = 8, max = 30)
    @NotBlank
    private String password;

    @NotBlank
    private String fullname;

    private boolean enabled;

    private Set<RoleSoap> roles;

    public UserSoap() {
        this.roles = new HashSet<>();
        this.roles.add(RoleSoap.Client);
    }

    public UserSoap(String username, String password, String fullname, boolean enabled, @NotEmpty Set<RoleSoap> roles) {
        this.username = username;
        this.fullname = fullname;
        this.password = password;
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

    @JsonbTransient
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public Set<RoleSoap> getRoles() {
        return roles;
    }

    public void setRoles(@NotEmpty Set<RoleSoap> roles) {
        this.roles = roles;
    }

    @JsonbTransient
    public Set<String> getRolesAsString() {
        return roles.stream().map(RoleSoap::name).collect(Collectors.toSet());
    }
}