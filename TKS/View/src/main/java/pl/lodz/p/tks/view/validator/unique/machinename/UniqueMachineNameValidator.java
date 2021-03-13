package pl.lodz.p.tks.view.validator.unique.machinename;

import pl.lodz.p.tks.applicationports.input.MachineUseCase;

import javax.inject.Inject;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class UniqueMachineNameValidator implements ConstraintValidator<UniqueMachineName, String>
{
    @Inject
    private MachineUseCase machineUseCase;

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext)
    {
        return !machineUseCase.findMachineByName(s).isPresent();
    }
}
