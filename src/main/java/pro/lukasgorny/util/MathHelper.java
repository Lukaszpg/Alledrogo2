package pro.lukasgorny.util;

import java.math.BigDecimal;

/**
 * Created by Łukasz on 23.11.2017.
 */
public class MathHelper {
    public static final Integer BIG_DECIMAL_GREATER_CODE = 1;
    public static final Integer BIG_DECIMAL_EQUAL_CODE = 0;

    public static Boolean bigDecimalLessOrEqualToFirstValue(BigDecimal firstValue, BigDecimal secondValue) {
        return firstValue.compareTo(secondValue) == BIG_DECIMAL_EQUAL_CODE
                || firstValue.compareTo(secondValue) == BIG_DECIMAL_GREATER_CODE;
    }
}
