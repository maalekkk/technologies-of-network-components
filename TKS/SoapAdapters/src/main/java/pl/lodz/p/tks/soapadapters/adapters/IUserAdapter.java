package pl.lodz.p.tks.soapadapters.adapters;

import pl.lodz.p.tks.soapadapters.data.user.UserSoap;

import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.ws.rs.PathParam;
import javax.ws.rs.QueryParam;
import java.util.List;

@WebService
public interface IUserAdapter {

    @WebMethod
    String getName();

    @WebMethod
    void addUser(UserSoap user);

    @WebMethod
    UserSoap getUserById(String userId);

    @WebMethod
    UserSoap getUserByUsername(@QueryParam("username") String username);

    @WebMethod
    List<UserSoap> getUsers();

    @WebMethod
    void updateUserById(@PathParam("id") String userId, UserSoap user);
}
