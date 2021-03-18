package pl.lodz.p.tks.view.applicationservices.service;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.*;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.runners.MockitoJUnitRunner;
import pl.lodz.p.tks.applicationports.persistence.rent.DeleteRentPort;
import pl.lodz.p.tks.applicationports.persistence.rent.ExistRentPort;
import pl.lodz.p.tks.applicationports.persistence.rent.GetRentPort;
import pl.lodz.p.tks.applicationports.persistence.rent.SaveRentPort;
import pl.lodz.p.tks.view.domainmodel.model.machine.MachineGaming;
import pl.lodz.p.tks.view.domainmodel.model.rent.Period;
import pl.lodz.p.tks.view.domainmodel.model.rent.Rent;
import pl.lodz.p.tks.view.domainmodel.model.user.User;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;

@RunWith(MockitoJUnitRunner.class)
public class RentServiceTest {
    @Mock
    private DeleteRentPort deleteRentPort;

    @Mock
    private GetRentPort getRentPort;

    @Mock
    private SaveRentPort saveRentPort;

    @Mock
    private ExistRentPort existRentPort;

    @InjectMocks
    private RentService service;

    private List<Rent> rents;

    @Before
    public void init() {
        service = new RentService();
        MockitoAnnotations.initMocks(this);
        rents = new ArrayList<>();

        MachineGaming machineGaming = new MachineGaming("Jaro", 2137, 12, 32, 32, 32);
        machineGaming.setId(UUID.randomUUID());
        User user = new User("pawlacz", "Warol Kojtyla", true);
        user.setId(UUID.randomUUID());

        Period period = new Period();
        period.setStartDate(LocalDateTime.now());
        period.setEndDate(LocalDateTime.MAX);

        Rent rent = new Rent(machineGaming, user, period);
        rent.setId(UUID.randomUUID());
        rents.add(rent);

        // GetRentPort
        Mockito.when(getRentPort.getAll()).thenReturn(rents);
        Mockito.when(getRentPort.findRentById(Matchers.any()))
                .thenAnswer((InvocationOnMock inv) -> rents.stream().filter(x -> x.getId().equals(inv.getArguments()[0])).findFirst());

        // SaveRentPort
        Mockito.when(saveRentPort.saveRent(Matchers.any())).thenAnswer( (InvocationOnMock inv) -> {
            Rent m = (Rent) (inv.getArguments()[0]);
            if(m.getId() == null) {
                m.setId(UUID.randomUUID());
            }
            boolean exist = rents.stream().anyMatch(x -> x.getId().equals(m.getId()));
            if(exist) {
                rents = rents.stream().filter(x -> !(x.getId().equals(m.getId()))).collect(Collectors.toList());
            }
            rents.add(m);
            return m;
        });

        // DeleteRentPort
        Mockito.doAnswer(x -> {
            Rent r = (Rent)(x.getArguments()[0]);
            rents = rents.stream().filter(z -> !(z.getId().equals(r.getId()))).collect(Collectors.toList());
            return r;
        }).when(deleteRentPort).deleteRent(Mockito.isA(Rent.class));

        // ExistRentPort
        Mockito.when(existRentPort.existsRentById(Matchers.any())).thenAnswer( (InvocationOnMock inv) -> {
            UUID uuid = (UUID) (inv.getArguments()[0]);
            return rents.stream().anyMatch(x -> x.getId().equals(uuid));
        });
    }

    @Test
    public void test() {
        assertEquals(service.getAll().size(), 1);
        assertFalse(service.findRentById(rents.get(0).getId()).isEmpty());

        MachineGaming machineGaming = new MachineGaming("Acer x69", 2137, 1, 33, 32, 32);
        machineGaming.setId(UUID.randomUUID());
        User user = new User("nowy", "Janusz Pudzian", true);
        user.setId(UUID.randomUUID());

        Period period = new Period();
        period.setStartDate(LocalDateTime.now());
        period.setEndDate(LocalDateTime.MAX);

        Rent rent = new Rent(machineGaming, user, period);
        service.saveRent(rent);
        assertTrue(service.existsRent(rent));
        assertEquals(service.getAll().size(), 2);
    }
}
