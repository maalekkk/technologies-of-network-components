package pl.lodz.p.tks.soapadapters.adapters;

import javax.jws.WebMethod;
import javax.jws.WebService;

@WebService
public class UserAdapter {

    private final String message = "Hello, ";

    public UserAdapter() {
    }

    @WebMethod
    public String sayHello(String name) {
        return message + name + ".";
    }
}
