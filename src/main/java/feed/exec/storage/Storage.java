package feed.exec.storage;

import java.util.List;
import java.util.Set;

/**
 * feed存储介质接口
 * 
 * @author Boxbox
 * 
 */
public interface Storage<N, T> {
	
	/**
	 * 清空存储空间
	 */
	void flush();

	/**
	 * 添加元素List到存储空间N中
	 * 
	 * @param key
	 * @param tlist
	 */
	void addToStorage(N key, List<T> tlist);

	/**
	 * 从存储空间N中获取start-end的数据 
	 * @param key
	 * @param start 
	 * @param end
	 * @return
	 */
	List<T> getFromStorage(N key, int start, int end);
	
	/**
	 * 从存储空间N中移除指定集合的元素
	 * @param key
	 * @param tlist
	 */
	void removeFromStorage(N key, List<T> tlist);
}
