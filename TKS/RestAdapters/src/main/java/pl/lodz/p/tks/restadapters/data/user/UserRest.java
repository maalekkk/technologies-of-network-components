package pl.lodz.p.tks.restadapters.data.user;

import pl.lodz.p.tks.restadapters.data.EntityRest;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class UserRest extends EntityRest {
    @Size(min = 3, max = 20)
    private String username;

    @NotBlank
    private String fullname;

    private boolean enabled;

    public UserRest() {
    }

    public UserRest(String username, String fullname, boolean enabled) {
        this.username = username;
        this.fullname = fullname;
        this.enabled = enabled;
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
}