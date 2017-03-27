package feed.exec.storage;

/**
 * 线性存储介质接口
 * @author Boxbox
 *
 * @param <N>
 * @param <T>
 */
public interface TimeLineStorage<N, T> extends Storage<N, T> {
	
	/**
	 * 获取元素t在n空间的index
	 * @param n
	 * @param t
	 * @return
	 */
	int indexOf(N n , T t);
	
	
	/**
	 * 整理元素空间使其在既定的长度
	 * @param n
	 */
	void trim(N n);
	
	
	/**
	 * 获取元素空间的元素个数
	 * @param n
	 * @return
	 */
	int count(N n);
	
	/**
	 * 删除整个存储空间
	 * @param n
	 */
	void delete(N n);
}
