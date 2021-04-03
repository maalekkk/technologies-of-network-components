package pl.lodz.p.tks.soapadapters.adapters;

import jakarta.jws.WebMethod;
import jakarta.jws.WebService;
import pl.lodz.p.tks.applicationports.view.UserUseCase;

import javax.inject.Inject;

@WebService(serviceName = "ClientAPI")
public class UserAdapter {

    @Inject
    private UserUseCase userUseCase;

    @WebMethod
    public int getUsersCount() {
        return userUseCase.getAll().size();
    }
}
