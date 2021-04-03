package pl.lodz.p.tks.soapadapters.data.user;

import pl.lodz.p.tks.soapadapters.data.EntitySoap;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;

@XmlType(namespace="http://soap.tks.p.lodz.pl/users", name = "User")
@XmlAccessorType(XmlAccessType.FIELD)
public class UserSoap extends EntitySoap {
    private String username;

    private String fullname;

    private boolean enabled;

    public UserSoap() {
    }

    public UserSoap(String username, String fullname, boolean enabled) {
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