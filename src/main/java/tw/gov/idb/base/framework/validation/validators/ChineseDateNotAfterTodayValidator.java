package tw.gov.idb.base.framework.validation.validators;

import tw.gov.idb.base.framework.validation.ChineseDateNotAfterToday;
import tw.gov.idb.base.util.DateUtility;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

/**
 * ChineseDateNotAfterTodayValidator
 *
 *  
 */
@Slf4j
public class ChineseDateNotAfterTodayValidator implements ConstraintValidator<ChineseDateNotAfterToday, CharSequence> {

    @Override
    public void initialize(ChineseDateNotAfterToday parameters) {

    }

    @Override
    public boolean isValid(CharSequence str, ConstraintValidatorContext constraintValidatorContext) {
        if (str == null)
            return true;

        String value = str.toString();

        if (StringUtils.isBlank(StringUtils.trimToEmpty(value)) || !DateUtility.isValidDate(value)) // 日期格式有錯則不進行檢核
            return true;

        try {
            return Integer.parseInt(value) <= Integer.parseInt(DateUtility.getNowChineseDate());
        } catch (Exception e) {
            // Should not happen
            log.error("@ChineseDateNotAfterToday Validator 發生錯誤");
            return true;
        }
    }

}
