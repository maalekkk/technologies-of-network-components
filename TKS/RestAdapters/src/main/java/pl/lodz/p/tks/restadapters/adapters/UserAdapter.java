package pl.lodz.p.tks.restadapters.adapters;

import pl.lodz.p.tks.applicationports.view.UserUseCase;
import pl.lodz.p.tks.restadapters.adapters.converters.UserConverter;
import pl.lodz.p.tks.restadapters.data.user.UserRest;
import pl.lodz.p.tks.view.domainmodel.model.user.User;

import javax.inject.Inject;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.ws.rs.BadRequestException;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
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
    @Produces("application/json")
    @Consumes({MediaType.APPLICATION_JSON})
    public Response addUser(@NotNull @Valid UserRest user) {
        return Optional.ofNullable(user.getId())
                .map(u -> Response.status(BAD_REQUEST))
                .orElse(Response.ok(userUseCase.saveUser(UserConverter.toDomainModel(user))))
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
    @Consumes({MediaType.APPLICATION_JSON})
    @Path("/{id}")
    public Response updateUserById(@PathParam("id") UUID userId, @NotNull @Valid UserRest user) {
        return userUseCase.findUserById(userId)
                .filter(u -> u.getId().equals(user.getId()) &&
                        u.getUsername().equals(user.getUsername()))
                .map(u -> userUseCase.saveUser(UserConverter.toDomainModel(user)))
                .map(Response::ok)
                .orElseThrow(BadRequestException::new)
                .build();
    }

    @GET
    @Path("/me")
    public Response currentUser() {
        return Response.ok(userUseCase.getCurrentUser()).build();
    }
}