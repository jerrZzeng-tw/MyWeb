package tw.gov.idb.base.framework.validation.validators;

import tw.gov.idb.base.framework.validation.MinValue;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.extern.slf4j.Slf4j;

import java.math.BigDecimal;
import java.math.BigInteger;

/**
 * MinValueValidatorForNumber
 *
 *  
 */
@Slf4j
public class MinValueValidatorForNumber implements ConstraintValidator<MinValue, Number> {

    private long minValue;

    @Override
    public void initialize(MinValue parameters) {
        this.minValue = parameters.value();
    }

    @Override
    public boolean isValid(Number value, ConstraintValidatorContext constraintValidatorContext) {
        if (value == null) {
            return true;
        }

        if (value instanceof BigDecimal) {
            return ((BigDecimal) value).compareTo(BigDecimal.valueOf(minValue)) != -1;
        } else if (value instanceof BigInteger) {
            return ((BigInteger) value).compareTo(BigInteger.valueOf(minValue)) != -1;
        } else {
            double longValue = value.doubleValue();
            return longValue >= minValue;
        }
    }

}
