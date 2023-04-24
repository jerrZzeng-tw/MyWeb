package tw.gov.idb.base.framework.validation.validators;

import tw.gov.idb.base.framework.validation.MinValue;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.extern.slf4j.Slf4j;

import java.math.BigDecimal;

/**
 * MinValueValidatorForCharSequence
 *
 *  
 */
@Slf4j
public class MinValueValidatorForCharSequence implements ConstraintValidator<MinValue, CharSequence> {

    private BigDecimal minValue;

    @Override
    public void initialize(MinValue parameters) {
        this.minValue = BigDecimal.valueOf(parameters.value());
    }

    @Override
    public boolean isValid(CharSequence str, ConstraintValidatorContext constraintValidatorContext) {
        if (str == null) {
            return true;
        }

        try {
            return new BigDecimal(str.toString()).compareTo(minValue) != -1;
        } catch (NumberFormatException nfe) {
            return false;
        }
    }

}
