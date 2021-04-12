package pl.lodz.p.tks.restadapters.adapters;

import pl.lodz.p.tks.applicationports.view.MachineUseCase;
import pl.lodz.p.tks.applicationports.view.RentUseCase;
import pl.lodz.p.tks.applicationports.view.UserUseCase;
import pl.lodz.p.tks.restadapters.adapters.converters.PeriodConverter;
import pl.lodz.p.tks.restadapters.data.rent.RentRest;
import pl.lodz.p.tks.view.domainmodel.model.rent.Rent;

import javax.annotation.security.RolesAllowed;
import javax.inject.Inject;
import javax.validation.constraints.NotNull;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.util.List;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;
import static pl.lodz.p.tks.restadapters.data.user.RoleRest.CLIENT;

@Path("/rents")
@Consumes(APPLICATION_JSON)
@Produces(APPLICATION_JSON)
public class RentAdapter {

    @Inject
    private RentUseCase rentUseCase;

    @Inject
    private UserUseCase userUseCase;

    @Inject
    private MachineUseCase machineUseCase;

    @POST
    @Path("/create")
    @RolesAllowed({CLIENT})
    public Response createRent(@NotNull RentRest.SimpleRent simpleRent) {
        return machineUseCase.findMachineByName(simpleRent.getMachineName())
                .map(machine -> new Rent(machine, userUseCase.getCurrentUser(), PeriodConverter.toDomainModel(simpleRent.getPeriod())))
                .map(rentUseCase::saveRent)
                .map(Response::ok)
                .orElseThrow(BadRequestException::new)
                .build();
    }

    @GET
    @Path("/me")
    @RolesAllowed({CLIENT})
    public List<Rent> currentUserRents() {
        return rentUseCase.findRentsByUser(userUseCase.getCurrentUser());
    }
}