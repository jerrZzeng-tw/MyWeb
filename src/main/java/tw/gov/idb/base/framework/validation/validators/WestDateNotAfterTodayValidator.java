package tw.gov.idb.base.framework.validation.validators;

import tw.gov.idb.base.util.DateUtility;
import tw.gov.idb.base.framework.validation.WestDateNotAfterToday;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

/**
 * WestDateNotAfterTodayValidator
 *
 *  
 */
@Slf4j
public class WestDateNotAfterTodayValidator implements ConstraintValidator<WestDateNotAfterToday, CharSequence> {

    @Override
    public void initialize(WestDateNotAfterToday parameters) {

    }

    @Override
    public boolean isValid(CharSequence str, ConstraintValidatorContext constraintValidatorContext) {
        if (str == null)
            return true;

        String value = str.toString();

        if (StringUtils.isBlank(StringUtils.trimToEmpty(value)) || !DateUtility.isValidDate(value)) // 日期格式有錯則不進行檢核
            return true;

        try {
            return Integer.parseInt(value) <= Integer.parseInt(DateUtility.getNowWestDate());
        } catch (Exception e) {
            // Should not happen
            log.error("@WestDateNotAfterToday Validator 發生錯誤");
            return true;
        }
    }

}
