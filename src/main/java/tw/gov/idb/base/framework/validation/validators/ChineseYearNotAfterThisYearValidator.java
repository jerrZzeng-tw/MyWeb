package tw.gov.idb.base.framework.validation.validators;

import tw.gov.idb.base.util.DateUtility;
import tw.gov.idb.base.framework.validation.ChineseYearNotAfterThisYear;
import tw.gov.idb.base.framework.validation.support.RegexUtility;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

/**
 * ChineseYearNotAfterThisYearValidator
 *
 *  
 */
@Slf4j
public class ChineseYearNotAfterThisYearValidator implements ConstraintValidator<ChineseYearNotAfterThisYear, CharSequence> {

    private static final String PATTERN = "^([0-9]{3})$";

    @Override
    public void initialize(ChineseYearNotAfterThisYear parameters) {

    }

    @Override
    public boolean isValid(CharSequence str, ConstraintValidatorContext constraintValidatorContext) {
        if (str == null)
            return true;

        String value = str.toString();

        if (StringUtils.isBlank(StringUtils.trimToEmpty(value)))
            return true;

        // 年度格式有錯則不進行檢核
        try {
            if (!RegexUtility.isMatches(value, PATTERN)) {
                return true;
            }

            // 年度不可能是 000 年
            if (StringUtils.equals(value, "000")) {
                return true;
            }
        } catch (Exception e) {
            return true;
        }

        try {
            return Integer.parseInt(value) <= Integer.parseInt(DateUtility.getNowChineseYear());
        } catch (Exception e) {
            // Should not happen
            log.error("@ChineseYearNotAfterThisYear Validator 發生錯誤");
            return true;
        }
    }

}
