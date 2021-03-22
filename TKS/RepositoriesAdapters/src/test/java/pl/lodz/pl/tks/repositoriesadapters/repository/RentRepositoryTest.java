package pl.lodz.pl.tks.repositoriesadapters.repository;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import pl.lodz.p.tks.repositoriesadapters.data.machine.MachineGamingEnt;
import pl.lodz.p.tks.repositoriesadapters.data.rent.PeriodEnt;
import pl.lodz.p.tks.repositoriesadapters.data.rent.RentEnt;
import pl.lodz.p.tks.repositoriesadapters.data.user.UserEnt;
import pl.lodz.p.tks.repositoriesadapters.repository.RentRepository;

import java.util.UUID;

public class RentRepositoryTest {

    private RentRepository rentRepository;
    private UserEnt user;
    private RentEnt rent;

    @Before
    public void init() {
        rentRepository = new RentRepository();
        user = new UserEnt("user", "user user", true);
        MachineGamingEnt machine = new MachineGamingEnt("machine", 8, 256, 256, 3000, 200);
        rent = new RentEnt(machine, user, new PeriodEnt());
    }

    @Test
    public void findAllTest() {
        Assert.assertTrue(rentRepository.findAll().isEmpty());
        rentRepository.save(rent);
        Assert.assertEquals(rentRepository.findAll().get(0), rent);
        Assert.assertFalse(rentRepository.findAll().isEmpty());
    }

    @Test(expected = IllegalArgumentException.class)
    public void findByIdTest() {
        rentRepository.save(rent);
        Assert.assertEquals(rentRepository.findById(rent.getId()).orElseThrow(IllegalArgumentException::new), rent);
        rentRepository.findById(UUID.randomUUID()).orElseThrow(IllegalArgumentException::new);
    }

    @Test
    public void existByIdTest() {
        rentRepository.save(rent);
        Assert.assertTrue(rentRepository.existsById(rent.getId()));
        Assert.assertFalse(rentRepository.existsById(UUID.randomUUID()));
    }

    @Test
    public void countTest() {
        Assert.assertEquals(rentRepository.count(), 0);
        rentRepository.save(rent);
        Assert.assertEquals(rentRepository.count(), 1);
    }

    @Test
    public void saveTest() {
        Assert.assertTrue(rentRepository.findAll().isEmpty());
        rentRepository.save(rent);
        Assert.assertEquals(rentRepository.count(), 1);
        Assert.assertEquals(rentRepository.findAll().get(0), rent);
    }

    @Test
    public void updateTest() {
        Assert.assertTrue(rentRepository.findAll().isEmpty());
        rentRepository.save(rent);
        Assert.assertEquals(rentRepository.count(), 1);
        Assert.assertEquals(rentRepository.findAll().get(0), rent);
        PeriodEnt period = new PeriodEnt();
        rent.setPeriod(period);
        rentRepository.save(rent);
        Assert.assertEquals(rentRepository.count(), 1);
        Assert.assertEquals(rentRepository.findAll().get(0).getPeriod(), period);
    }

    @Test
    public void deleteTest() {
        Assert.assertTrue(rentRepository.findAll().isEmpty());
        rentRepository.save(rent);
        Assert.assertEquals(rentRepository.count(), 1);
        rentRepository.delete(rent);
        Assert.assertTrue(rentRepository.findAll().isEmpty());
    }

    @Test
    public void deleteByIdTest() {
        Assert.assertTrue(rentRepository.findAll().isEmpty());
        rentRepository.save(rent);
        Assert.assertEquals(rentRepository.count(), 1);
        rentRepository.deleteById(rent.getId());
        Assert.assertTrue(rentRepository.findAll().isEmpty());
    }

    @Test
    public void deleteAllTest() {
        Assert.assertTrue(rentRepository.findAll().isEmpty());
        MachineGamingEnt machine2 = new MachineGamingEnt("machine", 8, 256, 256, 3000, 200);
        RentEnt rent2 = new RentEnt(machine2, user, new PeriodEnt());
        rentRepository.save(rent);
        rentRepository.save(rent2);
        Assert.assertEquals(rentRepository.count(), 2);
        rentRepository.deleteAll();
        Assert.assertTrue(rentRepository.findAll().isEmpty());
    }
}