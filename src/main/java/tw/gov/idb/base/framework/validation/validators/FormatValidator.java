package tw.gov.idb.base.framework.validation.validators;

import tw.gov.idb.base.util.StringUtility;
import tw.gov.idb.base.framework.validation.Format;
import tw.gov.idb.base.framework.validation.support.RegexUtility;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

/**
 * FormatValidator
 *
 *  
 */
@Slf4j
public class FormatValidator implements ConstraintValidator<Format, CharSequence> {

    private boolean trim;
    private String pattern;
    private boolean caseSensitive;

    @Override
    public void initialize(Format parameters) {
        trim = parameters.trim();
        pattern = parameters.pattern();
        caseSensitive = parameters.caseSensitive();
    }

    @Override
    public boolean isValid(CharSequence str, ConstraintValidatorContext constraintValidatorContext) {
        if (str == null)
            return true;

        String value = str.toString();

        if (StringUtils.isBlank(StringUtils.trimToEmpty(value)))
            return true;

        if (trim)
            value = StringUtils.trimToEmpty(value);

        try {
            if (RegexUtility.isMatches(value, pattern, caseSensitive))
                return true;
        } catch (Exception e) {
            log.error("@Format Validator 發生錯誤，請檢查 pattern 的格式是否設定正確，目前所指定的 pattern 為：{}",
                    StringUtility.safeLog(StringUtils.defaultString(pattern, "null 或 空值")));
            return false;
        }

        return false;
    }

}
