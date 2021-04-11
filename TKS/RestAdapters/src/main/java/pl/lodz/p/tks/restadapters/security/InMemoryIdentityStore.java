package pl.lodz.p.tks.restadapters.security;

import pl.lodz.p.tks.applicationports.view.UserUseCase;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.security.enterprise.credential.Credential;
import javax.security.enterprise.credential.UsernamePasswordCredential;
import javax.security.enterprise.identitystore.CredentialValidationResult;
import javax.security.enterprise.identitystore.IdentityStore;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static javax.security.enterprise.identitystore.CredentialValidationResult.INVALID_RESULT;
import static javax.security.enterprise.identitystore.CredentialValidationResult.NOT_VALIDATED_RESULT;
import static javax.security.enterprise.identitystore.IdentityStore.ValidationType.PROVIDE_GROUPS;
import static javax.security.enterprise.identitystore.IdentityStore.ValidationType.VALIDATE;

@ApplicationScoped
public class InMemoryIdentityStore implements IdentityStore {
    @Inject
    private UserUseCase userUseCase;

    @Override
    public CredentialValidationResult validate(Credential credential) {
        if (credential instanceof UsernamePasswordCredential) {
            final UsernamePasswordCredential cred = (UsernamePasswordCredential) credential;
            return userUseCase.findUserByUsername(cred.getCaller())
                    .filter(user -> cred.getPassword().compareTo(user.getPassword()) && user.isEnabled())
                    .map(user -> new CredentialValidationResult(user.getUsername(), user.getRolesAsString()))
                    .orElse(INVALID_RESULT);
        }
        return NOT_VALIDATED_RESULT;
    }

    @Override
    public Set<ValidationType> validationTypes() {
        return Stream.of(VALIDATE, PROVIDE_GROUPS).collect(Collectors.toSet());
    }
}
