package tw.gov.idb.base.framework.validation.validators;

import tw.gov.idb.base.framework.validation.RequiredString;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

/**
 * RequiredStringValidator
 *
 *  
 */
@Slf4j
public class RequiredStringValidator implements ConstraintValidator<RequiredString, CharSequence> {

    private boolean trim;

    @Override
    public void initialize(RequiredString parameters) {
        trim = parameters.trim();
    }

    @Override
    public boolean isValid(CharSequence str, ConstraintValidatorContext constraintValidatorContext) {
        if (str == null)
            return false;

        String value = str.toString();

        if (trim)
            value = StringUtils.trimToEmpty(value);

        return StringUtils.length(value) > 0;
    }

}
