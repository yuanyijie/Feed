package feed.web.common.util;

import feed.web.common.ResponseEnum;
import feed.web.common.exception.FeedServiceException;

/**
 * Feed断言类
 * 
 * @author Boxbox
 * 
 */
public class FeedAssert {

	/**
	 * 断言某个字符串数组中的任何字符串都不能为空或者empty 否则抛出Service Runtime异常
	 * 
	 * @param strArray
	 */
	public static void notNullOrEmpty(String... strArray) {
		for (String str : strArray) {
			if (str == null || str.isEmpty()) {
				throw new FeedServiceException(
						ResponseEnum.LOGICFAILED.getCode(), "string is null");
			}
		}
	}

	/**
	 * 断言某个对象不为null 否则抛出Service Runtime异常
	 * 
	 * @param obj
	 * @param message
	 */
	public static void notNull(Object obj, String message) {
		if (obj == null) {
			throw new FeedServiceException(ResponseEnum.LOGICFAILED.getCode(), message);
		}
	}
	
	/**
	 * 断言某个数字就是预先期望的数字 否则抛出Service Runtime异常
	 * @param resultNumber
	 * @param preNumber
	 * @param message
	 */
	public static void numberLikeExcepted(int resultNumber, int preNumber, String message){
		if(resultNumber!=preNumber){
			throw new FeedServiceException(ResponseEnum.LOGICFAILED.getCode(), message);
		}
	}
	
	/**
	 * 断言某个数字就是0  否则抛出Service Runtime异常
	 * @param resultNumber
	 * @param message
	 */
	public static void numberIsZero(int resultNumber, String message){
		numberLikeExcepted(resultNumber, 0, message);
	}
	
	/**
	 * 断言某个数字不是预先期望的数字 否则抛出Service Runtime异常
	 * @param resultNumber
	 * @param preNumber
	 * @param message
	 */
	public static void numberUnlikeExcepted(int resultNumber, int preNumber, String message){
		if(resultNumber==preNumber){
			throw new FeedServiceException(ResponseEnum.LOGICFAILED.getCode(), message);
		}
	}
	
	/**
	 * 断言某个数字不是0  否则抛出Service Runtime异常
	 * @param resultNumber
	 * @param message
	 */
	public static void numberIsNotZero(int resultNumber, String message){
		numberUnlikeExcepted(resultNumber, 0, message);
	}
	
}
