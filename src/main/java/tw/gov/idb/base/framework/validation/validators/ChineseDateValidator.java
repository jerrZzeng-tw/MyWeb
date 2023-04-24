package tw.gov.idb.base.framework.validation.validators;

import tw.gov.idb.base.util.DateUtility;
import tw.gov.idb.base.framework.validation.ChineseDate;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

/**
 * ChineseDateValidator
 *
 *  
 */
@Slf4j
public class ChineseDateValidator implements ConstraintValidator<ChineseDate, CharSequence> {

    @Override
    public void initialize(ChineseDate parameters) {

    }

    @Override
    public boolean isValid(CharSequence str, ConstraintValidatorContext constraintValidatorContext) {
        if (str == null)
            return true;

        String value = str.toString();

        if (StringUtils.isBlank(StringUtils.trimToEmpty(value)))
            return true;

        try {
            boolean bBeforeROC = false;

            if ((StringUtils.length(value) == 8 && !StringUtils.startsWith(value, "-"))) {
                return false;
            }

            if (Integer.parseInt(value) < 0)
                bBeforeROC = true;

            return DateUtility.isValidDate(value.replace("-", ""), bBeforeROC);
        } catch (Exception e) {
            log.error("@ChineseDate Validator 發生錯誤");
            return false;
        }
    }

}
