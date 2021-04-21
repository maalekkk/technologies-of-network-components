package pl.lodz.p.tks.rent.restadapters.adapters;

import pl.lodz.p.tks.rent.applicationports.view.MachineUseCase;
import pl.lodz.p.tks.rent.applicationports.view.RentUseCase;
import pl.lodz.p.tks.rent.restadapters.adapters.converters.PeriodConverter;
import pl.lodz.p.tks.rent.restadapters.data.rent.RentRest;
import pl.lodz.p.tks.rent.core.domainmodel.rent.Rent;
import pl.lodz.p.tks.rent.restadapters.data.user.RoleRest;

import javax.annotation.security.RolesAllowed;
import javax.inject.Inject;
import javax.validation.constraints.NotNull;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.util.List;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;

@Path("/rents")
@Consumes(APPLICATION_JSON)
@Produces(APPLICATION_JSON)
public class RentAdapter {

    @Inject
    private RentUseCase rentUseCase;

//    @Inject
//    private UserUseCase userUseCase;

    @Inject
    private MachineUseCase machineUseCase;

    @POST
    @Path("/create")
    @RolesAllowed({RoleRest.CLIENT})
    public Response createRent(@NotNull RentRest.SimpleRent simpleRent) {
//        return machineUseCase.findMachineByName(simpleRent.getMachineName())
//                .map(machine -> new Rent(machine, userUseCase.getCurrentUser(), PeriodConverter.toDomainModel(simpleRent.getPeriod())))
//                .map(rentUseCase::saveRent)
//                .map(Response::ok)
//                .orElseThrow(BadRequestException::new)
//                .build();
        return null;
    }

    @GET
    @Path("/me")
    @RolesAllowed({RoleRest.CLIENT})
    public List<Rent> currentUserRents() {
        return null;
//        return rentUseCase.findRentsByUser(userUseCase.getCurrentUser());
    }
}