package feed.exec.storage;

public interface HashStorage<V, M, N> {
	
	/**
	 * 为某个键的某个hash赋值
	 * @param key
	 * @param hash
	 * @param value
	 */
	void set(N key, M hash, V value);
	
	/**
	 * 取出某个键的hash对应的值
	 * @param key
	 * @param hash
	 * @return
	 */
	V get(N key, M hash);
	
	/**
	 * 对某个键的某个hash对应的值递增
	 * @param key
	 * @param hash
	 * @param value
	 */
	long incrby(N key, M hash, long value);
	
	/**
	 * 对某个键的某个hash对应的值递减
	 * @param key
	 * @param hash
	 * @param value
	 */
	long decrby(N key, M hash, long value);
	
	/**
	 * 删除某个键的某个hash
	 * @param key
	 * @param hash
	 */
	void delete(N key, M hash);
}
