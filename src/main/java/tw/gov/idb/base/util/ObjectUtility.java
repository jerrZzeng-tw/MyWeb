package tw.gov.idb.base.util;

import org.apache.commons.lang3.ObjectUtils;

public class ObjectUtility {

    /**
     * for Fortify
     *
     * @param target
     * @return
     */
    @SuppressWarnings("unchecked")
    public static <T> T normalizeObject(T target) {
        if (target == null) {
            return null;
        }
        return (T) ObjectUtils.CONST(target);
    }
}
