package tw.gov.idb.base.util;

import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@Slf4j
public class CollectionUtility {

    /**
     * 複製並回傳 List 中的所有物件
     *
     * @param list
     * @return
     */
    @SuppressWarnings(value = {"unchecked", "rawtypes"})
    public static List cloneListElements(List list) {
        List returnList = new ArrayList();

        if (list != null) {
            for (Object object : list) {
                returnList.add(BeanUtility.cloneBean(object));
            }
        }

        return returnList;
    }
    
	/**
	 * <pre>
	 * 	ex: ['A','B','C','D'] -> [['A','B'],['C','D']]
	 * </pre>
	 * 
	 * @param <T>
	 * @param list
	 * @param splitSize split個數
	 * @return
	 */
	public static <T> List<List<T>> partition(List<T> list, int splitSize) {

		if (list == null) {
			return null;
		}
		final AtomicInteger counter = new AtomicInteger();

		List<List<T>> resultList = new ArrayList<>();

		for (T element : list) {
			if (counter.getAndIncrement() % splitSize == 0) {
				resultList.add(new ArrayList<>());
			}
			resultList.get(resultList.size() - 1).add(element);
		}
		return resultList;
	}

}
