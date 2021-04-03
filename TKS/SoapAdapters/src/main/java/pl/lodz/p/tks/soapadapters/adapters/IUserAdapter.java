package pl.lodz.p.tks.soapadapters.adapters;

import javax.jws.WebMethod;
import javax.jws.WebService;

@WebService
public interface IUserAdapter {
    @WebMethod
    String sayHello();

    @WebMethod
    String getName();
}
