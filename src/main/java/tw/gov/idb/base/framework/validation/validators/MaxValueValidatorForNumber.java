package tw.gov.idb.base.framework.validation.validators;

import tw.gov.idb.base.framework.validation.MaxValue;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.extern.slf4j.Slf4j;

import java.math.BigDecimal;
import java.math.BigInteger;

/**
 * MaxValueValidatorForNumber
 *
 *  
 */
@Slf4j
public class MaxValueValidatorForNumber implements ConstraintValidator<MaxValue, Number> {

    private long maxValue;

    @Override
    public void initialize(MaxValue parameters) {
        this.maxValue = parameters.value();
    }

    @Override
    public boolean isValid(Number value, ConstraintValidatorContext constraintValidatorContext) {
        if (value == null) {
            return true;
        }

        if (value instanceof BigDecimal) {
            return ((BigDecimal) value).compareTo(BigDecimal.valueOf(maxValue)) != 1;
        } else if (value instanceof BigInteger) {
            return ((BigInteger) value).compareTo(BigInteger.valueOf(maxValue)) != 1;
        } else {
            long longValue = value.longValue();
            return longValue <= maxValue;
        }
    }

}
