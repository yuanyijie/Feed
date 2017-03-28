package feed.exec.storage;

import java.util.List;

/**
 * 线性存储介质接口
 * @author Boxbox
 *
 * @param <N>
 * @param <T>
 */
public interface TimeLineStorage<N, T> {
	
	/**
	 * 获取元素t在n空间的index
	 * @param n
	 * @param t
	 * @return
	 */
	long indexOf(N n , T t);
	
	
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
	long count(N n);
	
	/**
	 * 分页从redis中取数据
	 * @param n
	 * @param start
	 * @param end
	 * @return
	 */
	List<T> getSlice(N n, int start, int end);
}
