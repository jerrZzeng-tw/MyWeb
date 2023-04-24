package tw.gov.idb.base.framework.validation.validators;

import tw.gov.idb.base.framework.validation.NotEmpty;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

/**
 * IdNoFormatValidator
 *
 *  
 */
@Slf4j
public class NotEmptyValidator implements ConstraintValidator<NotEmpty, CharSequence> {

    @Override
    public void initialize(NotEmpty parameters) {

    }

    @Override
    public boolean isValid(CharSequence str, ConstraintValidatorContext constraintValidatorContext) {
        if (str == null)
            return false;

        String value = str.toString();

        if (StringUtils.isBlank(StringUtils.trimToEmpty(value)))
            return false;

        return true;

    }


}
