package pl.lodz.p.tks.rent.restadapters.adapters.converters;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import pl.lodz.p.tks.rent.core.domainmodel.user.Role;
import pl.lodz.p.tks.rent.core.domainmodel.user.User;
import pl.lodz.p.tks.rent.restadapters.data.machine.MachineGamingRest;
import pl.lodz.p.tks.rent.restadapters.data.machine.MachineRest;
import pl.lodz.p.tks.rent.restadapters.data.rent.PeriodRest;
import pl.lodz.p.tks.rent.restadapters.data.rent.RentRest;
import pl.lodz.p.tks.rent.restadapters.data.user.RoleRest;
import pl.lodz.p.tks.rent.restadapters.data.user.UserRest;
import pl.lodz.p.tks.rent.core.domainmodel.machine.Machine;
import pl.lodz.p.tks.rent.core.domainmodel.machine.MachineGaming;
import pl.lodz.p.tks.rent.core.domainmodel.rent.Period;
import pl.lodz.p.tks.rent.core.domainmodel.rent.Rent;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.UUID;

import static pl.lodz.p.tks.rent.restadapters.adapters.converters.TestUtils.compareRents;

public class RentConverterTest {

    private Rent rent;
    private RentRest rentRest;

    @Before
    public void init() {
        Machine machineGaming = new MachineGaming("Acer x21y37", 4, 200, 100, 4, 4);
        MachineRest machineGamingRest = new MachineGamingRest("Acer x21y37", 4, 200, 100, 4, 4);

        UUID uuid = UUID.randomUUID();
        machineGaming.setId(uuid);
        machineGamingRest.setId(uuid);

        Period period = new Period();
        period.setStartDate(LocalDateTime.now());
        period.setEndDate(LocalDateTime.MAX);

        PeriodRest periodRest = new PeriodRest();
        periodRest.setStartDate(period.getStartDate());
        periodRest.setEndDate(period.getEndDate());

        User user = new User("Bolek", "Bolesław Chrobry", true, Collections.singleton(Role.Client));
        UserRest userRest = new UserRest("Bolek", "Bolesław Chrobry", true, Collections.singleton(RoleRest.Client));

        UUID userId = UUID.randomUUID();
        user.setId(userId);
        userRest.setId(userId);

        rent = new Rent(machineGaming, user, period);
        rentRest = new RentRest(machineGamingRest, userRest, periodRest);

        UUID rentId = UUID.randomUUID();
        rent.setId(rentId);
        rentRest.setId(rentId);
    }

    @Test
    public void domainToRestConversion() {
        RentRest rentRest = RentConverter.fromDomainModel(rent);
        Assert.assertTrue(compareRents(rentRest, this.rentRest));
    }

    @Test
    public void entToDomainConversion() {
        Rent rent = RentConverter.toDomainModel(rentRest);
        Assert.assertTrue(compareRents(rent, this.rent));
    }

}
