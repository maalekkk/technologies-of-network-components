package pl.lodz.p.tks.view.validator.unique.username;

import pl.lodz.p.tks.applicationports.view.UserUseCase;

import javax.inject.Inject;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class UniqueUsernameValidator implements ConstraintValidator<UniqueUsername, String>
{
    @Inject
    private UserUseCase userUseCase;

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext)
    {
        return !userUseCase.findUserByUsername(s).isPresent();
    }
}
