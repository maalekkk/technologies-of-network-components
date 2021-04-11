package pl.lodz.p.tks.restadapters.adapters;

import pl.lodz.p.tks.applicationports.view.MachineUseCase;
import pl.lodz.p.tks.restadapters.adapters.converters.MachineConverter;
import pl.lodz.p.tks.restadapters.data.machine.MachineGamingRest;
import pl.lodz.p.tks.restadapters.data.machine.MachineWorkstationRest;
import pl.lodz.p.tks.restadapters.data.user.RoleRest;
import pl.lodz.p.tks.view.domainmodel.model.machine.Machine;
import pl.lodz.p.tks.view.domainmodel.model.machine.MachineGaming;
import pl.lodz.p.tks.view.domainmodel.model.machine.MachineWorkstation;

import javax.annotation.security.RolesAllowed;
import javax.inject.Inject;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;
import static javax.ws.rs.core.Response.Status.BAD_REQUEST;
import static javax.ws.rs.core.Response.Status.NOT_FOUND;
import static pl.lodz.p.tks.restadapters.data.user.RoleRest.Admin;
import static pl.lodz.p.tks.restadapters.data.user.RoleRest.Client;
import static pl.lodz.p.tks.restadapters.data.user.RoleRest.Owner;

@Path("/machines")
@Consumes(APPLICATION_JSON)
@Produces(APPLICATION_JSON)
public class MachineAdapter {

    @Inject
    private MachineUseCase machineUseCase;

    @POST
    @Consumes({MediaType.APPLICATION_JSON})
    @Path("/gaming")
    public Response addMachineGaming(@NotNull @Valid MachineGamingRest machineGaming) {
        return addMachine(MachineConverter.toDomainModel(machineGaming));
    }

    @POST
    @Path("/workstation")
    public Response addMachineWorkstation(@NotNull @Valid MachineWorkstationRest machineWorkstation) {
        return addMachine(MachineConverter.toDomainModel(machineWorkstation));
    }

    @GET
    @Path("/{id}")
    public Response getMachineById(@PathParam("id") UUID machineId) {
        return machineUseCase.findMachineById(machineId)
                .map(Response::ok)
                .orElseThrow(NotFoundException::new)
                .build();
    }

    @GET
    @Path("/machine")
    public Response getMachineByName(@QueryParam("machinename") String machineName) {
        return machineUseCase.findMachineByName(machineName)
                .map(Response::ok)
                .orElseThrow(NotFoundException::new)
                .build();
    }

    @GET
//    @RolesAllowed({Client, Owner, Admin})
    public List<Machine> getMachines() {
        return machineUseCase.getAll();
    }

    @PUT
    @Path("/gaming/{id}")
    public Response updateMachineGaming(@PathParam("id") UUID machineId,
                                        @NotNull @Valid MachineGamingRest machineGaming) {
        return updateMachine(machineId, MachineConverter.toDomainModel(machineGaming));
    }

    @PUT
    @Path("/workstation/{id}")
    public Response updateMachineWorkstation(@PathParam("id") UUID machineId,
                                             @NotNull @Valid MachineWorkstationRest machineWorkstation) {
        return updateMachine(machineId, MachineConverter.toDomainModel(machineWorkstation));
    }

    @DELETE
    @Path("/{id}")
    public Response deleteMachineById(@PathParam("id") UUID machineId) {
        return machineUseCase.deleteMachineById(machineId)
                ? Response.ok().build()
                : Response.status(NOT_FOUND).build();
    }

    private Response addMachine(Machine machine) {
        return Optional.ofNullable(machine.getId())
                .map(m -> Response.status(BAD_REQUEST))
                .orElse(Response.ok(machineUseCase.saveMachine(machine)))
                .build();
    }

    private Response updateMachine(UUID machineId, Machine machine) {
        return machineUseCase.findMachineById(machineId)
                .filter(m -> m.getId().equals(machine.getId())
                        && m.getName().equals(machine.getName()))
                .map(m -> machineUseCase.saveMachine(machine))
                .map(Response::ok)
                .orElseThrow(BadRequestException::new)
                .build();
    }
}