package tw.gov.idb.base.util;

import org.apache.commons.lang3.Validate;
import org.apache.commons.lang3.math.NumberUtils;

import java.lang.reflect.Array;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.Optional;

public class NumberUtility {

    // alternative to Double.valueOf(String), (IMPORTANT!!!!) but input string must be validated outside or else return 0
    public static Double toDouble(String input) {
        return NumberUtils.toDouble(input);
    }

    // alternative to Double.valueOf(double)
    public static Double toDouble(double input) {
        return NumberUtils.toDouble(String.valueOf(input));
    }

    // alternative to BigDecimal.doubleValue(BigDecimal)
    public static double toDouble(BigDecimal bigDecimal) {
        return toDouble(Optional.of(bigDecimal)
                .get()
                .toString());
    }
    
    /**
     * Long = null 給予Long預設值 0
     * @param num
     * @return
     */
    public static Long defaultLong(Long num) {
        return num != null ? num : NumberUtils.LONG_ZERO;
    }
    
    /**
     * Integer = null 給予Integer預設值 0
     * @param num
     * @return
     */
    public static Integer defaultInt(Integer num) {
        return num != null ? num : NumberUtils.INTEGER_ZERO;
    }
    
    /**
     * BigDecimal = null 給予BigDecimal預設值 0
     * @param num
     * @return
     */
    public static BigDecimal defaultBigDecimal(BigDecimal num) {
        return num != null ? num : BigDecimal.ZERO;
    }
    
    private static final DecimalFormat DF = new DecimalFormat("#,###");// 千分位
    
    /**
     * 轉換千分位 1,000
     */
    public static String formatMoney(Object money) {
        return money != null ? DF.format(NumberUtils.toDouble(money.toString())) : NumberUtils.INTEGER_ZERO.toString();
    }
    
    /**
     * <p>Returns the minimum value in an array. filter zero</p>
     *
     * @param array  an array, must not be null or empty
     * @return the minimum value in the array
     * @throws IllegalArgumentException if {@code array} is {@code null}
     * @throws IllegalArgumentException if {@code array} is empty
     * @since 3.4 Changed signature from min(int[]) to min(int...)
     */
    public static int minWithoutZero(final int... array) {
        // Validates input
        validateArray(array);

        // Finds and returns min
        Integer min = null;
        for (int j = 0; j < array.length; j++) {
        	if (array[j] == 0) {
        		continue;
        	}
        	
			if (min == null) {
				min = array[j];
			} 
			
			if (array[j] < min) {
				min = array[j];
			}
        	
        }

        return min != null ? min : 0;
    }
    
    /**
     * Checks if the specified array is neither null nor empty.
     *
     * @param array  the array to check
     * @throws IllegalArgumentException if {@code array} is either {@code null} or empty
     */
    private static void validateArray(final Object array) {
        Validate.notNull(array, "array");
        Validate.isTrue(Array.getLength(array) != 0, "Array cannot be empty.");
    }
    
    
}
