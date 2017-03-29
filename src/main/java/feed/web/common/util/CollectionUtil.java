package feed.web.common.util;

import java.util.ArrayList;
import java.util.List;

public class CollectionUtil {

	public static <T> List<String> convertToStringList(List<T> tList) {
		if (tList == null || tList.size() == 0) {
			return null;
		}
		List<String> resultList = new ArrayList<>(tList.size());
		for (T t : tList) {
			resultList.add(String.valueOf(t));
		}
		return resultList;
	}

	public static <T> List<String> convertToStringList(String prefix,
			List<T> tList) {
		if (tList == null || tList.size() == 0) {
			return null;
		}
		List<String> resultList = new ArrayList<>(tList.size());
		for (T t : tList) {
			resultList.add(prefix + String.valueOf(t));
		}
		return resultList;
	}
	
	public static List<Integer> convertToIntegerList(List<String> tList){
		if (tList == null || tList.size() == 0) {
			return null;
		}
		List<Integer> resultList = new ArrayList<>(tList.size());
		for(String t: tList){
			resultList.add(Integer.valueOf(t));
		}
		return resultList;
	}
}
