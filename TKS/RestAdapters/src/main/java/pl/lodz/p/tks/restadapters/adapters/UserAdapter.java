package pl.lodz.p.tks.restadapters.adapters;

import pl.lodz.p.tks.applicationports.view.UserUseCase;
import pl.lodz.p.tks.view.domainmodel.model.user.User;

import javax.inject.Inject;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;
import static javax.ws.rs.core.Response.Status.BAD_REQUEST;

@Path("/users")
@Consumes(APPLICATION_JSON)
@Produces(APPLICATION_JSON)
public class UserAdapter {

    @Inject
    private UserUseCase userUseCase;

    @POST
    public Response addUser(@NotNull @Valid User user) {
        return Optional.ofNullable(user.getId())
                .map(u -> Response.status(BAD_REQUEST))
                .orElse(Response.ok(userUseCase.saveUser(user)))
                .build();
    }

    @GET
    @Path("/{id}")
    public Response getUserById(@PathParam("id") UUID userId) {
        return userUseCase.findUserById(userId)
                .map(Response::ok)
                .orElseThrow(NotFoundException::new)
                .build();
    }

    @GET
    @Path("/user")
    public Response getUserByUsername(@QueryParam("username") String username) {
        return userUseCase.findUserByUsername(username)
                .map(Response::ok)
                .orElseThrow(NotFoundException::new)
                .build();
    }

    @GET
    public List<User> getUsers() {
        return userUseCase.getAll();
    }

    @PUT
    @Path("/{id}")
    public Response updateUserById(@PathParam("id") UUID userId, @NotNull @Valid User user) {
        return userUseCase.findUserById(userId)
                .filter(u -> u.getId().equals(user.getId()) &&
                        u.getUsername().equals(user.getUsername()))
                .map(u -> userUseCase.saveUser(user))
                .map(Response::ok)
                .orElseThrow(BadRequestException::new)
                .build();
    }

    @GET
    @Path("/me")
//    @RolesAllowed({CLIENT, ADMIN, OWNER})
    public Response currentUser() {
        return Response.ok(userUseCase.getCurrentUser()).build();
    }
}
