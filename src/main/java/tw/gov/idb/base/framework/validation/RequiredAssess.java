package tw.gov.idb.base.framework.validation;


import tw.gov.idb.base.framework.validation.validators.RequiredAssessValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 檢核物件的方法回傳值必須為指定的布林值，該方法名稱必須為<b>is</b>開頭<br/>
 * 適用於條件檢核，例如當Ａ欄位有值時，Ｂ欄位必須輸入或須為某個值
 *
 *  
 */
@Documented
@Target({ElementType.TYPE_USE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = {RequiredAssessValidator.class})
public @interface RequiredAssess {

    String message() default "{validation.requiredString.message}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    String label();

    boolean result() default true;

}
