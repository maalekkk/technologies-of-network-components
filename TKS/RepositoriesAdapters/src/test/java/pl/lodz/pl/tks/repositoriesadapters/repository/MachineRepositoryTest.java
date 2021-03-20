package pl.lodz.pl.tks.repositoriesadapters.repository;


import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import pl.lodz.p.tks.repositoriesadapters.data.machine.MachineGamingEnt;
import pl.lodz.p.tks.repositoriesadapters.repository.MachineRepository;

import java.util.UUID;

public class MachineRepositoryTest {

    private MachineRepository machineRepository;
    private MachineGamingEnt machine;

    @Before
    public void init() {
        machineRepository = new MachineRepository();
        machine = new MachineGamingEnt("machine", 8, 256, 256, 3000, 200);
    }

    @Test
    public void findAllTest() {
        Assert.assertTrue(machineRepository.findAll().isEmpty());
        machineRepository.save(machine);
        Assert.assertEquals(machineRepository.findAll().get(0), machine);
        Assert.assertFalse(machineRepository.findAll().isEmpty());
    }

    @Test
    public void findByIdTest() {
        machineRepository.save(machine);
        Assert.assertEquals(machineRepository.findById(machine.getId()).orElseThrow(), machine);
        Assert.assertTrue(machineRepository.findById(UUID.randomUUID()).isEmpty());
    }

    @Test
    public void existByIdTest() {
        machineRepository.save(machine);
        Assert.assertTrue(machineRepository.existsById(machine.getId()));
        Assert.assertFalse(machineRepository.existsById(UUID.randomUUID()));
    }

    @Test
    public void countTest() {
        Assert.assertEquals(machineRepository.count(), 0);
        machineRepository.save(machine);
        Assert.assertEquals(machineRepository.count(), 1);
    }

    @Test
    public void saveTest() {
        Assert.assertTrue(machineRepository.findAll().isEmpty());
        machineRepository.save(machine);
        Assert.assertEquals(machineRepository.count(), 1);
        Assert.assertEquals(machineRepository.findAll().get(0), machine);
    }

    @Test
    public void updateTest() {
        Assert.assertTrue(machineRepository.findAll().isEmpty());
        machineRepository.save(machine);
        Assert.assertEquals(machineRepository.count(), 1);
        Assert.assertEquals(machineRepository.findAll().get(0), machine);
        machine.setName("new machine");
        machineRepository.save(machine);
        Assert.assertEquals(machineRepository.count(), 1);
        Assert.assertEquals(machineRepository.findAll().get(0).getName(), "new machine");
    }

    @Test
    public void deleteTest() {
        Assert.assertTrue(machineRepository.findAll().isEmpty());
        machineRepository.save(machine);
        Assert.assertEquals(machineRepository.count(), 1);
        machineRepository.delete(machine);
        Assert.assertTrue(machineRepository.findAll().isEmpty());
    }

    @Test
    public void deleteByIdTest() {
        Assert.assertTrue(machineRepository.findAll().isEmpty());
        machineRepository.save(machine);
        Assert.assertEquals(machineRepository.count(), 1);
        machineRepository.deleteById(machine.getId());
        Assert.assertTrue(machineRepository.findAll().isEmpty());
    }

    @Test
    public void deleteAllTest() {
        Assert.assertTrue(machineRepository.findAll().isEmpty());
        MachineGamingEnt machine2 = new MachineGamingEnt("machine2", 8, 256, 256, 3000, 200);
        machineRepository.save(machine);
        machineRepository.save(machine2);
        Assert.assertEquals(machineRepository.count(), 2);
        machineRepository.deleteAll();
        Assert.assertTrue(machineRepository.findAll().isEmpty());
    }
}