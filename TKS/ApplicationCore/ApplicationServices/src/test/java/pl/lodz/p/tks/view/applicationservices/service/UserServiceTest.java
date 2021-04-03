package pl.lodz.p.tks.view.applicationservices.service;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.*;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.runners.MockitoJUnitRunner;
import pl.lodz.p.tks.applicationports.persistence.user.ExistUserPort;
import pl.lodz.p.tks.applicationports.persistence.user.GetUserPort;
import pl.lodz.p.tks.applicationports.persistence.user.SaveUserPort;
import pl.lodz.p.tks.view.domainmodel.model.user.User;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class UserServiceTest {

    @Mock
    private GetUserPort getUserPort;

    @Mock
    private SaveUserPort saveUserPort;

    @Mock
    private ExistUserPort existUserPort;

    @InjectMocks
    private UserService userService;

    private List<User> users;

    @Before
    public void init() {
        userService = new UserService();
        MockitoAnnotations.initMocks(this);

        users = new ArrayList<>();
        User user = new User("pawlacz", "Warol Kojtyla", true);
        user.setId(UUID.randomUUID());
        users.add(user);


        // getUserPort
        Mockito.when(getUserPort.getAll()).thenReturn(users);
        Mockito.when(getUserPort.findUserByUsername(Matchers.anyString()))
                .thenAnswer((InvocationOnMock inv) -> users.stream().filter(x -> x.getUsername().equals(inv.getArguments()[0])).findFirst());

        // SaveUserPort
        Mockito.when(saveUserPort.saveUser(Matchers.any())).thenAnswer((InvocationOnMock inv) -> {
            User m = (User) (inv.getArguments()[0]);
            if (m.getId() == null) {
                m.setId(UUID.randomUUID());
            }
            boolean exist = users.stream().anyMatch(x -> x.getId().equals(m.getId()));
            if (exist) {
                users = users.stream().filter(x -> !(x.getId().equals(m.getId()))).collect(Collectors.toList());
            }
            users.add(m);
            return m;
        });

        // ExistUserPort
        Mockito.when(existUserPort.existsUserById(Matchers.any())).thenAnswer((InvocationOnMock inv) -> {
            UUID uuid = (UUID) (inv.getArguments()[0]);
            return users.stream().anyMatch(x -> x.getId().equals(uuid));
        });

    }

    @Test
    public void test() {
//        assertEquals(userService.getAll().size(), 1);
//        assertFalse(userService.findUserByUsername("pawlacz").isEmpty());
//        User user = new User("peja", "Asd eeef", true);
//        userService.saveUser(user);
//        assertTrue(userService.existsUser(user));
//        assertEquals(userService.getAll().size(), 2);
    }
}