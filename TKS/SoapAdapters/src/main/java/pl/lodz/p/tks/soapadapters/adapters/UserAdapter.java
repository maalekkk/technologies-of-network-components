package pl.lodz.p.tks.soapadapters.adapters;


import javax.jws.WebMethod;
import javax.jws.WebService;
import pl.lodz.p.tks.applicationports.view.UserUseCase;
import pl.lodz.p.tks.view.applicationservices.service.UserService;

import javax.inject.Inject;

@WebService(serviceName = "ClientAPI", wsdlLocation = "https://raw.githubusercontent.com/maalekkk/wsdlTest/master/UserAdapter.wsdl")
public class UserAdapter {

    @Inject
    private UserService userUseCase;

    @WebMethod
    public int getUsersCount() {
        return userUseCase.getAll().size();
    }
}
