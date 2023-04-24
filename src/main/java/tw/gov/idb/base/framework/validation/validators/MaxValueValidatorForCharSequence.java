package tw.gov.idb.base.framework.validation.validators;

import tw.gov.idb.base.framework.validation.MaxValue;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.extern.slf4j.Slf4j;

import java.math.BigDecimal;

/**
 * MaxValueValidatorForCharSequence
 *
 *  
 */
@Slf4j
public class MaxValueValidatorForCharSequence implements ConstraintValidator<MaxValue, CharSequence> {

    private BigDecimal maxValue;

    @Override
    public void initialize(MaxValue parameters) {
        this.maxValue = BigDecimal.valueOf(parameters.value());
    }

    @Override
    public boolean isValid(CharSequence str, ConstraintValidatorContext constraintValidatorContext) {
        if (str == null) {
            return true;
        }

        try {
            return new BigDecimal(str.toString()).compareTo(maxValue) != 1;
        } catch (NumberFormatException nfe) {
            return false;
        }
    }

}
