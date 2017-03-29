package feed.exec.storage.redis.structure;

import java.util.Map;

import org.apache.log4j.Logger;

import feed.exec.storage.HashStorage;
import feed.web.common.exception.FeedDaoException;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.Pipeline;

public class Hash extends RedisStorage<Map<String, String>> implements
		HashStorage<String, String, String> {
	private final static Logger log = Logger.getLogger(Hash.class);

	@Override
	public void addToStorage(String key, Map<String, String> data) {
		Jedis jedis = null;
		boolean exceptionOccured = false;
		try {
			jedis = getJedis();
			jedis.hmset(key, data);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			exceptionOccured = true;
			throw FeedDaoException.JEDIS_EXCEPTION;
		} finally {
			releaseJedis(jedis, exceptionOccured);
		}
	}

	@Override
	public void removeFromStorage(String key, Map<String, String> data) {
		Jedis jedis = null;
		boolean exceptionOccured = false;
		try {
			jedis = getJedis();
			jedis.hdel(key, data.keySet().toArray(new String[0]));
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			exceptionOccured = true;
			throw FeedDaoException.JEDIS_EXCEPTION;
		} finally {
			releaseJedis(jedis, exceptionOccured);
		}
	}

	@Override
	public Map<String, String> getFromStorage(String key) {
		Jedis jedis = null;
		boolean exceptionOccured = false;
		Map<String, String> result = null;
		try {
			jedis = getJedis();
			result = jedis.hgetAll(key);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			exceptionOccured = true;
			throw FeedDaoException.JEDIS_EXCEPTION;
		} finally {
			releaseJedis(jedis, exceptionOccured);
		}
		return result;
	}

	@Override
	public void set(String key, String hash, String value) {
		Jedis jedis = null;
		boolean exceptionOccured = false;
		try {
			jedis = getJedis();
			jedis.hset(key, hash, value);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			exceptionOccured = true;
			throw FeedDaoException.JEDIS_EXCEPTION;
		} finally {
			releaseJedis(jedis, exceptionOccured);
		}
	}

	@Override
	public String get(String key, String hash) {
		Jedis jedis = null;
		boolean exceptionOccured = false;
		String result = null;
		try {
			jedis = getJedis();
			result = jedis.hget(key, hash);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			exceptionOccured = true;
			throw FeedDaoException.JEDIS_EXCEPTION;
		} finally {
			releaseJedis(jedis, exceptionOccured);
		}
		return result;
	}

	@Override
	public long incrby(String key, String hash, long value) {
		Jedis jedis = null;
		boolean exceptionOccured = false;
		long result = 0;
		try {
			jedis = getJedis();
			result = jedis.hincrBy(key, hash, value);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			exceptionOccured = true;
			throw FeedDaoException.JEDIS_EXCEPTION;
		} finally {
			releaseJedis(jedis, exceptionOccured);
		}
		return result;
	}

	@Override
	public long decrby(String key, String hash, long value) {
		Jedis jedis = null;
		boolean exceptionOccured = false;
		long result = 0;
		try {
			jedis = getJedis();
			result = jedis.hincrBy(key, hash, -value);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			exceptionOccured = true;
			throw FeedDaoException.JEDIS_EXCEPTION;
		} finally {
			releaseJedis(jedis, exceptionOccured);
		}
		return result;
	}

	@Override
	public void delete(String key, String hash) {
		Jedis jedis = null;
		boolean exceptionOccured = false;
		try {
			jedis = getJedis();
			jedis.hdel(key, hash);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			exceptionOccured = true;
			throw FeedDaoException.JEDIS_EXCEPTION;
		} finally {
			releaseJedis(jedis, exceptionOccured);
		}
	}

	@Override
	protected void addToStorage(Pipeline pipline, String key, Map<String, String> t) {
		// TODO Auto-generated method stub
		
	}

}
