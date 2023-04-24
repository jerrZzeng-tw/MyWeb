package tw.gov.idb.base.framework.validation.validators;

import tw.gov.idb.base.framework.validation.NumberFormat;
import tw.gov.idb.base.util.StringUtility;
import tw.gov.idb.base.framework.validation.support.RegexUtility;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

/**
 * NumberFormatValidator
 *
 *  
 */
@Slf4j
public class NumberFormatValidator implements ConstraintValidator<NumberFormat, CharSequence> {

    private int integer;
    private int decimal;

    @Override
    public void initialize(NumberFormat parameters) {
        integer = parameters.integer();
        decimal = parameters.decimal();
    }

    @Override
    public boolean isValid(CharSequence str, ConstraintValidatorContext constraintValidatorContext) {
        if (str == null)
            return true;

        String value = str.toString();

        if (StringUtils.isBlank(StringUtils.trimToEmpty(value)))
            return true;

        // 若 integer 和 decimal > 0 才驗
        if (integer < 1 || decimal < 1)
            return true;

        String pattern = "^\\d{1," + integer + "}\\.\\d{1," + decimal + "}$";

        try {
            if (RegexUtility.isMatches(value, pattern)) {
                return true;
            }
        } catch (Exception e) {
            log.error("@NumberFormat Validator 發生錯誤，請檢查 pattern 的格式是否設定正確，目前所指定的 pattern 為：{}",
                    StringUtility.safeLog(StringUtils.defaultString(pattern, "null 或 空值")));
            return false;
        }

        return false;
    }

}
