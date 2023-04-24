package tw.gov.idb.base.framework.validation.validators;

import tw.gov.idb.base.framework.validation.WestYearMonth;
import tw.gov.idb.base.framework.validation.support.RegexUtility;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

/**
 * WestYearMonth
 *
 *  
 */
@Slf4j
public class WestYearMonthValidator implements ConstraintValidator<WestYearMonth, CharSequence> {

    private static final String PATTERN = "^([0-9]{4})([0]+[1-9]|1[0-2])$";

    @Override
    public void initialize(WestYearMonth parameters) {

    }

    @Override
    public boolean isValid(CharSequence str, ConstraintValidatorContext constraintValidatorContext) {
        if (str == null)
            return true;

        String value = str.toString();

        if (StringUtils.isBlank(StringUtils.trimToEmpty(value)))
            return true;

        try {
            if (!RegexUtility.isMatches(value, PATTERN)) {
                return false;
            }

            // 年度不可能是 000 年
            return !StringUtils.equals(StringUtils.substring(value, 0, 4), "0000");
        } catch (Exception e) {
            log.error("@WestYearMonth Validator 發生錯誤");
            return false;
        }
    }

}
