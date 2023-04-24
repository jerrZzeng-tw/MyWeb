package tw.gov.idb.base.framework.validation.validators;

import tw.gov.idb.base.framework.validation.RequiredAssess;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class RequiredAssessValidator implements ConstraintValidator<RequiredAssess, Boolean> {

    private boolean result;

    @Override
    public void initialize(RequiredAssess constraintAnnotation) {
        this.result = constraintAnnotation.result();
    }

    @Override
    public boolean isValid(Boolean value, ConstraintValidatorContext context) {
        return value == null || Boolean.logicalAnd(value, result);
    }

}
