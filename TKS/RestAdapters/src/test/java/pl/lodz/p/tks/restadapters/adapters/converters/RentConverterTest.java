package pl.lodz.p.tks.restadapters.adapters.converters;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import pl.lodz.p.tks.restadapters.data.machine.MachineGamingRest;
import pl.lodz.p.tks.restadapters.data.machine.MachineRest;
import pl.lodz.p.tks.restadapters.data.rent.PeriodRest;
import pl.lodz.p.tks.restadapters.data.rent.RentRest;
import pl.lodz.p.tks.restadapters.data.user.UserRest;
import pl.lodz.p.tks.view.domainmodel.model.machine.Machine;
import pl.lodz.p.tks.view.domainmodel.model.machine.MachineGaming;
import pl.lodz.p.tks.view.domainmodel.model.rent.Period;
import pl.lodz.p.tks.view.domainmodel.model.rent.Rent;
import pl.lodz.p.tks.view.domainmodel.model.user.User;

import java.time.LocalDateTime;
import java.util.UUID;

import static pl.lodz.p.tks.restadapters.adapters.converters.TestUtils.compareRents;

public class RentConverterTest {

    private Rent rent;
    private RentRest rentRest;

    private Machine machineGaming;
    private MachineRest machineGamingRest;

    private Period period;
    private PeriodRest periodRest;

    private User user;
    private UserRest userRest;

    @Before
    public void init() {
        machineGaming = new MachineGaming("Acer x21y37", 4, 200, 100, 4, 4);
        machineGamingRest = new MachineGamingRest("Acer x21y37", 4, 200, 100, 4, 4);

        UUID uuid = UUID.randomUUID();
        machineGaming.setId(uuid);
        machineGamingRest.setId(uuid);

        period = new Period();
        period.setStartDate(LocalDateTime.now());
        period.setEndDate(LocalDateTime.MAX);

        periodRest = new PeriodRest();
        periodRest.setStartDate(period.getStartDate());
        periodRest.setEndDate(period.getEndDate());

        user = new User("Bolek", "Bolesław Chrobry", true);
        userRest = new UserRest("Bolek", "Bolesław Chrobry", true);

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
