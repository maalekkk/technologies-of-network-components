package pl.lodz.p.tks.soapadapters.adapters;

import pl.lodz.p.tks.applicationports.view.UserUseCase;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.jws.WebService;

@Named
@ApplicationScoped
@WebService(endpointInterface = "pl.lodz.p.tks.soapadapters.adapters.IUserAdapter")
public class UserAdapter implements IUserAdapter {

    private final String message = "Hello, ";

    @Inject
    private UserUseCase userUseCase;

    public UserAdapter() {
    }

    @Override
    public String sayHello() {
        return message;
    }

    @Override
    public String getName() {
        return userUseCase.getAll().get(0).getFullname();
    }
}
