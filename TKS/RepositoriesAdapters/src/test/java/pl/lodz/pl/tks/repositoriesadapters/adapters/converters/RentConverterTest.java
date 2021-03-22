package pl.lodz.pl.tks.repositoriesadapters.adapters.converters;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import pl.lodz.p.tks.repositoriesadapters.adapters.converters.PeriodConverter;
import pl.lodz.p.tks.repositoriesadapters.adapters.converters.RentConverter;
import pl.lodz.p.tks.repositoriesadapters.data.machine.MachineEnt;
import pl.lodz.p.tks.repositoriesadapters.data.machine.MachineGamingEnt;
import pl.lodz.p.tks.repositoriesadapters.data.rent.PeriodEnt;
import pl.lodz.p.tks.repositoriesadapters.data.rent.RentEnt;
import pl.lodz.p.tks.repositoriesadapters.data.user.UserEnt;
import pl.lodz.p.tks.view.domainmodel.model.machine.Machine;
import pl.lodz.p.tks.view.domainmodel.model.machine.MachineGaming;
import pl.lodz.p.tks.view.domainmodel.model.rent.Period;
import pl.lodz.p.tks.view.domainmodel.model.rent.Rent;
import pl.lodz.p.tks.view.domainmodel.model.user.User;

import java.time.LocalDateTime;
import java.util.UUID;

import static pl.lodz.pl.tks.repositoriesadapters.adapters.converters.TestUtils.*;

public class RentConverterTest {

    private Rent rent;
    private RentEnt rentEnt;

    private Machine machineGaming;
    private MachineEnt machineGamingEnt;

    private Period period;
    private PeriodEnt periodEnt;

    private User user;
    private UserEnt userEnt;

    @Before
    public void init() {
        machineGaming = new MachineGaming("Acer x21y37", 4, 200, 100, 4, 4);
        machineGamingEnt = new MachineGamingEnt("Acer x21y37", 4, 200, 100, 4, 4);

        UUID uuid = UUID.randomUUID();
        machineGaming.setId(uuid);
        machineGamingEnt.setId(uuid);

        period = new Period();
        period.setStartDate(LocalDateTime.now());
        period.setEndDate(LocalDateTime.MAX);

        periodEnt = new PeriodEnt();
        periodEnt.setStartDate(period.getStartDate());
        periodEnt.setEndDate(period.getEndDate());

        user = new User("Bolek", "Bolesław Chrobry", true);
        userEnt = new UserEnt("Bolek", "Bolesław Chrobry", true);

        UUID userId = UUID.randomUUID();
        user.setId(userId);
        userEnt.setId(userId);

        rent = new Rent(machineGaming, user, period);
        rentEnt = new RentEnt(machineGamingEnt, userEnt, periodEnt);

        UUID rentId = UUID.randomUUID();
        rent.setId(rentId);
        rentEnt.setId(rentId);
    }

    @Test
    public void domainToEntConversion() {
        RentEnt rentEnt = RentConverter.fromDomainModel(rent);
        Assert.assertTrue(compareRents(rentEnt, this.rentEnt));
    }

    @Test
    public void entToDomainConversion() {
        Rent rent = RentConverter.toDomainModel(rentEnt);
        Assert.assertTrue(compareRents(rent, this.rent));
    }

}
