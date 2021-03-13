package pl.lodz.p.tks.applicationcore.validator.unique.username;

import pl.lodz.p.tks.applicationcore.applicationservices.service.UserService;

import javax.inject.Inject;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class UniqueUsernameValidator implements ConstraintValidator<UniqueUsername, String>
{
    @Inject
    private UserService userService;

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext)
    {
        return !userService.findUserByUsername(s).isPresent();
    }
}
