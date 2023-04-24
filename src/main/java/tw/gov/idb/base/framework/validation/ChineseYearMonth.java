package tw.gov.idb.base.framework.validation;

import tw.gov.idb.base.framework.validation.validators.ChineseYearMonthValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.ANNOTATION_TYPE;
import static java.lang.annotation.ElementType.CONSTRUCTOR;
import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.ElementType.PARAMETER;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * 檢核字串是否為合法的民國年月，若字串為 <code>null</code> 或 空值 將不予檢核
 * <p>
 * 用法：
 * <p>
 * <code>@ChineseYearMonth(label = "欄位中文說明")</code>
 * <p>
 *
 *  
 */
@Target({METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER})
@Retention(RUNTIME)
@Constraint(validatedBy = ChineseYearMonthValidator.class)
@Documented
public @interface ChineseYearMonth {

    String label();

    String message() default "{validation.chineseYearMonth.message}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    @Target({METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER})
    @Retention(RUNTIME)
    @Documented
    @interface List {
        ChineseYearMonth[] value();
    }

}
