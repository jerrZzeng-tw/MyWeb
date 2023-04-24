package tw.gov.idb.base.framework.validation.validators;

import tw.gov.idb.base.util.DateUtility;
import tw.gov.idb.base.framework.validation.ChineseDateTime;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

/**
 * ChineseDateTimeValidator
 *
 *  
 */
@Slf4j
public class ChineseDateTimeValidator implements ConstraintValidator<ChineseDateTime, CharSequence> {

    @Override
    public void initialize(ChineseDateTime parameters) {

    }

    @Override
    public boolean isValid(CharSequence str, ConstraintValidatorContext constraintValidatorContext) {
        if (str == null)
            return true;

        String value = str.toString();

        if (StringUtils.isBlank(StringUtils.trimToEmpty(value)))
            return true;

        try {
            if (StringUtils.length(value) != 11 && StringUtils.length(value) != 13)
                return false;

            String date = DateUtility.splitChineseDateTime(value, true);
            String time = DateUtility.splitChineseDateTime(value, false);

            return (DateUtility.isValidDate(date) && DateUtility.isValidTime(time));
        } catch (Exception e) {
            log.error("@ChineseDateTimeValidator Validator 發生錯誤");
            return false;
        }
    }

}
