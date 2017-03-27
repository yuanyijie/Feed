package feed.exec.storage.redis.structure;

import static java.nio.charset.StandardCharsets.UTF_8;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import redis.clients.jedis.Jedis;
import feed.exec.storage.TimeLineStorage;
import feed.web.common.util.RedisUtil;

public class SortedSet implements TimeLineStorage<String, String> {
	private final static Logger log = Logger.getLogger(SortedSet.class);

	@Override
	public void flush() {
		Jedis jedis = null;
		boolean exceptionOccured = false;
		try {
			jedis = RedisUtil.getJedisResource();
			jedis.flushDB();
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			exceptionOccured = true;
		} finally {
			RedisUtil.returnJedisResource(jedis, exceptionOccured);
		}
	}

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
			jedis = RedisUtil.getJedisResource();
			jedis.zadd(key.getBytes(UTF_8), valueScoreMap);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			exceptionOccured = true;
		} finally {
			RedisUtil.returnJedisResource(jedis, exceptionOccured);
		}
	}

	@Override
	public List<String> getFromStorage(String key, int start, int end) {
		Jedis jedis = null;
		boolean exceptionOccured = false;
		List<String> result = null;
		try {
			jedis = RedisUtil.getJedisResource();
			result = new ArrayList<>(jedis.zrevrange(key, start, end));
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			exceptionOccured = true;
		} finally {
			RedisUtil.returnJedisResource(jedis, exceptionOccured);
		}
		return result;
	}

	@Override
	public void removeFromStorage(String key, List<String> tlist) {
		// TODO Auto-generated method stub

	}

	@Override
	public int indexOf(String n, String t) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void trim(String n) {
		// TODO Auto-generated method stub

	}

	@Override
	public int count(String n) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void delete(String n) {
		// TODO Auto-generated method stub

	}

}
