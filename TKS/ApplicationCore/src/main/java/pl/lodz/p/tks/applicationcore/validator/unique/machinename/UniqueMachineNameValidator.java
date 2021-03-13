package pl.lodz.p.tks.applicationcore.validator.unique.machinename;

import pl.lodz.p.tks.applicationcore.applicationservices.service.MachineService;

import javax.inject.Inject;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class UniqueMachineNameValidator implements ConstraintValidator<UniqueMachineName, String>
{
    @Inject
    private MachineService machineService;

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext)
    {
        return !machineService.findMachineByName(s).isPresent();
    }
}
