package tw.gov.idb.base.framework.validation;

import tw.gov.idb.base.framework.validation.validators.FieldCompareValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 檢核兩個欄位是否符合指定的比較邏輯，若欄位無法取值或欄位未實作{@link Comparable}或欄位型別不同，將不予檢核。
 *
 *  
 */
@Documented
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = {FieldCompareValidator.class})
public @interface FieldCompare {

    String message() default "{validation.fieldCompare.message}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    Operator operator();

    String fromField();

    String fromLabel();

    String thenField();

    String thenLabel();

    boolean nullable() default false;

    public enum Operator {

        /**
         * 等於
         */
        EQUAL {
            @Override
            public String toString() {
                return "等於";
            }
        },

        /**
         * 大於
         */
        MORE_THAN {
            @Override
            public String toString() {
                return "大於";
            }
        },

        /**
         * 大於等於
         */
        MORE_THAN_OR_EQUAL {
            @Override
            public String toString() {
                return "大於等於";
            }
        },

        /**
         * 小於
         */
        LESS_THAN {
            @Override
            public String toString() {
                return "小於";
            }
        },

        /**
         * 小於等於
         */
        LESS_THAN_OR_EQUAL {
            @Override
            public String toString() {
                return "小於等於";
            }
        },

    }

    @Documented
    @Target(ElementType.TYPE)
    @Retention(RetentionPolicy.RUNTIME)
    public @interface List {
        FieldCompare[] value();
    }

}
