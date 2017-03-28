package feed.exec.storage.redis.structure;

import org.apache.log4j.Logger;

import redis.clients.jedis.Jedis;
import feed.exec.storage.Storage;
import feed.web.common.exception.FeedDaoException;
import feed.web.common.util.RedisUtil;

public abstract class RedisStorage<T> implements Storage<String, T> {
	private final static Logger log = Logger.getLogger(RedisStorage.class);
	
	@Override
	public void flush() {
		Jedis jedis = null;
		boolean exceptionOccured = false;
		try {
			jedis = getJedis();
			jedis.flushDB();
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			exceptionOccured = true;
			throw FeedDaoException.JEDIS_EXCEPTION;
		} finally {
			releaseJedis(jedis, exceptionOccured);
		}
	}
	
	@Override
	public void delete(String key) {
		Jedis jedis = null;
		boolean exceptionOccured = false;
		try {
			jedis = getJedis();
			jedis.del(key);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			exceptionOccured = true;
			throw FeedDaoException.JEDIS_EXCEPTION;
		} finally{
			releaseJedis(jedis, exceptionOccured);
		}
	}
	
	/**
	 * 获取jedis连接
	 * @return
	 * @throws Exception
	 */
	protected Jedis getJedis() throws Exception {
		return RedisUtil.getJedisResource();
	}
	
	/**
	 * 释放连接回jedis连接池
	 * @param jedis
	 * @param exceptionOccured
	 */
	protected void releaseJedis(Jedis jedis, boolean exceptionOccured){
		RedisUtil.returnJedisResource(jedis, exceptionOccured);
	}
}
