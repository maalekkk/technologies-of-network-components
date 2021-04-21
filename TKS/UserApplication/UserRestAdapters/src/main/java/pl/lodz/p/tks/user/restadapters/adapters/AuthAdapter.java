package pl.lodz.p.tks.user.restadapters.adapters;

import pl.lodz.p.tks.user.applicationports.view.UserUseCase;
import pl.lodz.p.tks.user.restadapters.security.jwt.TokenProvider;
import pl.lodz.p.tks.user.core.domainmodel.user.User;

import javax.inject.Inject;
import javax.security.enterprise.credential.UsernamePasswordCredential;
import javax.security.enterprise.identitystore.IdentityStore;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Optional;

import static javax.security.enterprise.identitystore.CredentialValidationResult.Status.VALID;
import static javax.ws.rs.core.MediaType.APPLICATION_FORM_URLENCODED;
import static javax.ws.rs.core.MediaType.TEXT_PLAIN;
import static javax.ws.rs.core.Response.Status.UNAUTHORIZED;
import static pl.lodz.p.tks.user.restadapters.security.jwt.JwtAuthenticationMechanism.extractToken;

@Path("/auth")
@Produces(TEXT_PLAIN)
public class AuthAdapter {
    public static final MediaType APPLICATION_JWT = new MediaType("application", "jwt");

    @Inject
    private IdentityStore identityStore;

    @Inject
    private UserUseCase userUseCase;

    @Inject
    private TokenProvider tokenProvider;

    @POST
    @Path("/login")
    @Consumes(APPLICATION_FORM_URLENCODED)
    public Response login(@FormParam("username") String username, @FormParam("password") String password) {
        return Optional.of(identityStore.validate(new UsernamePasswordCredential(username, password)))
                .filter(result -> result.getStatus() == VALID)
                .map(result -> tokenProvider.createToken(result.getCallerPrincipal().getName(), result.getCallerGroups()))
                .map(token -> Response.ok(token).type(APPLICATION_JWT))
                .orElseGet(() -> Response.status(UNAUTHORIZED))
                .build();
    }

    @GET
    @Path("/refresh")
    public Response refresh(@Context HttpServletRequest request) {
        return extractToken(request)
                .filter(tokenProvider::isValid)
                .map(tokenProvider::extractCredential)
                .filter(cred -> userUseCase.findUserByUsername(cred.getUsername())
                        .filter(User::isEnabled)
                        .isPresent())
                .map(tokenProvider::createToken)
                .map(token -> Response.ok(token).type(APPLICATION_JWT))
                .orElseGet(() -> Response.status(UNAUTHORIZED))
                .build();
    }
}