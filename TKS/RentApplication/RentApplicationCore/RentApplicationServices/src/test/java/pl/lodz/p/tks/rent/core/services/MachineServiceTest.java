package pl.lodz.p.tks.rent.core.services;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.*;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.runners.MockitoJUnitRunner;
import pl.lodz.p.tks.rent.applicationports.persistance.machine.DeleteMachinePort;
import pl.lodz.p.tks.rent.applicationports.persistance.machine.ExistMachinePort;
import pl.lodz.p.tks.rent.applicationports.persistance.machine.GetMachinePort;
import pl.lodz.p.tks.rent.applicationports.persistance.machine.SaveMachinePort;
import pl.lodz.p.tks.rent.core.domainmodel.machine.Machine;
import pl.lodz.p.tks.rent.core.domainmodel.machine.MachineGaming;
import pl.lodz.p.tks.rent.core.domainmodel.machine.MachineWorkstation;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class MachineServiceTest {
    @Mock
    private GetMachinePort getMachinePort;

    @Mock
    private SaveMachinePort saveMachinePort;

    @Mock
    private DeleteMachinePort deleteMachinePort;

    @Mock
    private ExistMachinePort existMachinePort;

    @InjectMocks
    private MachineService service;

    private List<Machine> machines;

    @Before
    public void init() {
        service = new MachineService();
        MockitoAnnotations.initMocks(this);
        machines = new ArrayList<>();

        MachineGaming machineGaming = new MachineGaming("Jaro", 2137, 12, 32, 32, 32);
        machineGaming.setId(UUID.randomUUID());
        machines.add(machineGaming);

        // GetMachinePort
        Mockito.when(getMachinePort.getAll()).thenReturn(machines);
        Mockito.when(getMachinePort.findMachineByName(Matchers.anyString()))
                .thenAnswer((InvocationOnMock inv) -> machines.stream().filter(x -> x.getName().equals(inv.getArguments()[0])).findFirst());

        // SaveMachinePort
        Mockito.when(saveMachinePort.saveMachine(Matchers.any())).thenAnswer((InvocationOnMock inv) -> {
            Machine m = (Machine) (inv.getArguments()[0]);
            if (m.getId() == null) {
                m.setId(UUID.randomUUID());
            }
            boolean exist = machines.stream().anyMatch(x -> x.getId().equals(m.getId()));
            if (exist) {
                machines = machines.stream().filter(x -> !(x.getId().equals(m.getId()))).collect(Collectors.toList());
            }
            machines.add(m);
            return m;
        });

        // DeleteMachinePort
        Mockito.when(deleteMachinePort.deleteById(Matchers.any())).thenAnswer((InvocationOnMock inv) -> {
            UUID uuid = (UUID) (inv.getArguments()[0]);
            return machines.removeIf(x -> x.getId().equals(uuid));
        });

        // ExistMachinePort
        Mockito.when(existMachinePort.existsMachineById(Matchers.any())).thenAnswer((InvocationOnMock inv) -> {
            UUID uuid = (UUID) (inv.getArguments()[0]);
            return machines.stream().anyMatch(x -> x.getId().equals(uuid));
        });
    }

    @Test
    public void allTest() {
        assertEquals(service.getAll().size(), 1);
        assertFalse(service.findMachineByName("Jaro").isEmpty());
        MachineWorkstation workstation = new MachineWorkstation("Workstatione", 20, 20, 30, 24, 69, false);
        service.saveMachine(workstation);
        assertTrue(service.existsMachine(workstation));
        assertEquals(service.getAll().size(), 2);
        service.deleteMachineById(workstation.getId());
        assertEquals(service.getAll().size(), 1);
        assertFalse(service.existsMachine(workstation));
    }
}