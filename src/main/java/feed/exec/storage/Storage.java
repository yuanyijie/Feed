package feed.exec.storage;


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
	void addToStorage(N key, T data);

	/**
	 * 从存储空间N中获取start-end的数据 
	 * @param key
	 * @param start 
	 * @param end
	 * @return
	 */
	T getFromStorage(N key);
	
	/**
	 * 从存储空间N中移除指定集合的元素
	 * @param key
	 * @param tlist
	 */
	void removeFromStorage(N key, T t);
	
	/**
	 * 删除整个存储空间
	 * @param n
	 */
	void delete(N n);
}
