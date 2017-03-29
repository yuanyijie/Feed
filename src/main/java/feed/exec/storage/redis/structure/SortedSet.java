package feed.exec.storage.redis.structure;

import static java.nio.charset.StandardCharsets.UTF_8;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.Pipeline;
import feed.exec.storage.TimeLineStorage;
import feed.web.common.exception.FeedDaoException;

public class SortedSet extends RedisStorage<List<String>> implements
		TimeLineStorage<String, String> {

	private final static Logger log = Logger.getLogger(SortedSet.class);

	@Override
	public void addToStorage(String key, List<String> tlist) {
		Jedis jedis = null;
		boolean exceptionOccured = false;
		// 选择合适的长度
		Map<byte[], Double> valueScoreMap = new HashMap<>(
				(int) (tlist.size() * 1.25));
		for (String value : tlist) {
			valueScoreMap.put(value.getBytes(UTF_8), Double.valueOf(value));
		}
		try {
			jedis = getJedis();
			jedis.zadd(key.getBytes(UTF_8), valueScoreMap);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			exceptionOccured = true;
			throw FeedDaoException.JEDIS_EXCEPTION;
		} finally {
			releaseJedis(jedis, exceptionOccured);
		}
	}

	@Override
	public List<String> getFromStorage(String key) {
		Jedis jedis = null;
		boolean exceptionOccured = false;
		List<String> result = null;
		try {
			jedis = getJedis();
			result = new ArrayList<>(jedis.zrevrange(key, 0, -1));
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
	public void removeFromStorage(String key, List<String> tlist) {
		Jedis jedis = null;
		boolean exceptionOccured = false;
		try {
			jedis = getJedis();
			jedis.zrem(key, tlist.toArray(new String[0]));
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			exceptionOccured = true;
			throw FeedDaoException.JEDIS_EXCEPTION;
		} finally {
			releaseJedis(jedis, exceptionOccured);
		}
	}

	@Override
	public long indexOf(String key, String member) {
		Jedis jedis = null;
		boolean exceptionOccured = false;
		long index = 0;
		try {
			jedis = getJedis();
			index = jedis.zrank(key, member);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			exceptionOccured = true;
			throw FeedDaoException.JEDIS_EXCEPTION;
		} finally {
			releaseJedis(jedis, exceptionOccured);
		}
		return index;
	}

	@Override
	public void trim(String key) {
		// TODO 这里需要根据一定的策略，将优先级低的部分数据从队列中移除
	}

	@Override
	public long count(String key) {
		Jedis jedis = null;
		boolean exceptionOccured = false;
		long count = 0;
		try {
			jedis = getJedis();
			count = jedis.zcard(key);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			exceptionOccured = true;
			throw FeedDaoException.JEDIS_EXCEPTION;
		} finally {
			releaseJedis(jedis, exceptionOccured);
		}
		return count;
	}

	@Override
	public List<String> getSlice(String key, int start, int end) {
		Jedis jedis = null;
		boolean exceptionOccured = false;
		List<String> result = null;
		try {
			jedis = getJedis();
			result = new ArrayList<>(jedis.zrevrange(key, start, end));
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
	protected void addToStorage(Pipeline pipline, String key, List<String> tlist) {
		// 选择合适的长度
		Map<String, Double> valueScoreMap = new HashMap<>(
				(int) (tlist.size() * 1.25));
		for (String value : tlist) {
			valueScoreMap.put(value, Double.valueOf(value));
		}
		pipline.zadd(key, valueScoreMap);
	}
}
