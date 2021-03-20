package pl.lodz.pl.tks.repositoriesadapters.repository;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import pl.lodz.p.tks.repositoriesadapters.data.user.UserEnt;
import pl.lodz.p.tks.repositoriesadapters.repository.UserRepository;

import java.util.UUID;

public class UserRepositoryTest {

    private UserRepository userRepository;

    @Before
    public void init() {
        userRepository = new UserRepository();
    }

    @Test
    public void findAllTest() {
        Assert.assertTrue(userRepository.findAll().isEmpty());
        UserEnt user = new UserEnt("user", "user user", true);
        userRepository.save(user);
        Assert.assertEquals(userRepository.findAll().get(0), user);
        Assert.assertFalse(userRepository.findAll().isEmpty());
    }

    @Test
    public void findByIdTest() {
        UserEnt user = new UserEnt("user", "user user", true);
        userRepository.save(user);
        Assert.assertEquals(userRepository.findById(user.getId()).orElseThrow(), user);
        Assert.assertTrue(userRepository.findById(UUID.randomUUID()).isEmpty());
    }

    @Test
    public void existByIdTest() {
        UserEnt user = new UserEnt("user", "user user", true);
        userRepository.save(user);
        Assert.assertTrue(userRepository.existsById(user.getId()));
        Assert.assertFalse(userRepository.existsById(UUID.randomUUID()));
    }

    @Test
    public void countTest() {
        Assert.assertEquals(userRepository.count(), 0);
        UserEnt user = new UserEnt("user", "user user", true);
        userRepository.save(user);
        Assert.assertEquals(userRepository.count(), 1);
    }

    @Test
    public void saveTest() {
        Assert.assertTrue(userRepository.findAll().isEmpty());
        UserEnt user = new UserEnt("user", "user user", true);
        userRepository.save(user);
        Assert.assertEquals(userRepository.count(), 1);
        Assert.assertEquals(userRepository.findAll().get(0), user);
    }

    @Test
    public void updateTest() {
        Assert.assertTrue(userRepository.findAll().isEmpty());
        UserEnt user = new UserEnt("user", "user user", true);
        userRepository.save(user);
        Assert.assertEquals(userRepository.count(), 1);
        Assert.assertEquals(userRepository.findAll().get(0), user);
        user.setUsername("new user");
        userRepository.save(user);
        Assert.assertEquals(userRepository.count(), 1);
        Assert.assertEquals(userRepository.findAll().get(0).getUsername(), "new user");
    }

    @Test
    public void deleteTest() {
        Assert.assertTrue(userRepository.findAll().isEmpty());
        UserEnt user = new UserEnt("user", "user user", true);
        userRepository.save(user);
        Assert.assertEquals(userRepository.count(), 1);
        userRepository.delete(user);
        Assert.assertTrue(userRepository.findAll().isEmpty());
    }

    @Test
    public void deleteByIdTest() {
        Assert.assertTrue(userRepository.findAll().isEmpty());
        UserEnt user = new UserEnt("user", "user user", true);
        userRepository.save(user);
        Assert.assertEquals(userRepository.count(), 1);
        userRepository.deleteById(user.getId());
        Assert.assertTrue(userRepository.findAll().isEmpty());
    }

    @Test
    public void deleteAllTest() {
        Assert.assertTrue(userRepository.findAll().isEmpty());
        UserEnt user = new UserEnt("user", "user user", true);
        UserEnt user2 = new UserEnt("user2", "user user2", true);
        userRepository.save(user);
        userRepository.save(user2);
        Assert.assertEquals(userRepository.count(), 2);
        userRepository.deleteAll();
        Assert.assertTrue(userRepository.findAll().isEmpty());
    }
}