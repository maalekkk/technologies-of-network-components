package pl.lodz.p.tks.restadapters.security.jwt;

import javax.security.enterprise.credential.Credential;
import java.util.Set;

public class JwtCredential implements Credential {
    private final String username;
    private final Set<String> roles;

    public JwtCredential(String username, Set<String> roles) {
        this.username = username;
        this.roles = roles;
    }

    public String getUsername() {
        return username;
    }

    public Set<String> getRoles() {
        return roles;
    }
}