package tw.gov.idb.base.framework.validation.validators;

import tw.gov.idb.base.util.DateUtility;
import tw.gov.idb.base.framework.validation.Time;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

/**
 * TimeValidator
 *
 *  
 */
@Slf4j
public class TimeValidator implements ConstraintValidator<Time, CharSequence> {

    @Override
    public void initialize(Time parameters) {

    }

    @Override
    public boolean isValid(CharSequence str, ConstraintValidatorContext constraintValidatorContext) {
        if (str == null)
            return true;

        String value = str.toString();

        if (StringUtils.isBlank(StringUtils.trimToEmpty(value)))
            return true;

        try {

            if (StringUtils.length(value) != 4 && StringUtils.length(value) != 6)
                return false;

            return DateUtility.isValidTime(value);
        } catch (Exception e) {
            log.error("@Time Validator 發生錯誤");
            return false;
        }
    }

}
