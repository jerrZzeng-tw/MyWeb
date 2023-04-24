package tw.gov.idb.base.framework.validation.validators;

import tw.gov.idb.base.framework.validation.RequiredPositive;
import tw.gov.idb.base.framework.validation.support.RegexUtility;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

/**
 * RequiredPositiveValidator
 *
 *  
 */
@Slf4j
public class RequiredPositiveValidator implements ConstraintValidator<RequiredPositive, CharSequence> {

    private static final String PATTERN = "^\\d*$";

    @Override
    public void initialize(RequiredPositive parameters) {

    }

    @Override
    public boolean isValid(CharSequence str, ConstraintValidatorContext constraintValidatorContext) {
        if (str == null)
            return true;

        String value = str.toString();

        if (StringUtils.isBlank(StringUtils.trimToEmpty(value)))
            return true;

        try {
            return RegexUtility.isMatches(value, PATTERN);
        } catch (Exception e) {
            log.error("@RequiredPositive Validator 發生錯誤");
            return false;
        }
    }

}
