package pl.lodz.p.tks.restadapters.security.jwt;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.security.enterprise.AuthenticationStatus;
import javax.security.enterprise.authentication.mechanism.http.HttpAuthenticationMechanism;
import javax.security.enterprise.authentication.mechanism.http.HttpMessageContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Optional;

@ApplicationScoped
public class JwtAuthenticationMechanism implements HttpAuthenticationMechanism {
    private static final String AUTHORIZATION_HEADER = "Authorization";
    private static final String BEARER = "Bearer ";

    @Inject
    private TokenProvider tokenProvider;

    public static Optional<String> extractToken(HttpServletRequest request) {
        String authHeader = request.getHeader(AUTHORIZATION_HEADER);
        return Optional.ofNullable(authHeader)
                .filter(str -> str.startsWith(BEARER))
                .map(str -> str.substring(BEARER.length()));
    }

    @Override
    public AuthenticationStatus validateRequest(HttpServletRequest request, HttpServletResponse response, HttpMessageContext context) {
        return extractToken(request)
                .filter(tokenProvider::isValid)
                .map(tokenProvider::extractCredential)
                .map(cred -> context.notifyContainerAboutLogin(cred.getUsername(), cred.getRoles()))
                .orElseGet(() -> context.isProtected() ? context.responseUnauthorized() : context.doNothing());
    }
}