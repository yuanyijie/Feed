package feed.web.common.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.springframework.core.io.support.PropertiesLoaderUtils;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/**
 * Redis工具类
 * 
 * @author Boxbox
 * 
 */
public class RedisUtil {
	private static Logger log = Logger.getLogger(RedisUtil.class);

	// Jedis连接池
	private static JedisPool jedisPool = null;
	// Jedis连接池配置
	private static JedisPoolConfig poolConfig = null;
	// 主机
	private static String redisHost;
	// 端口
	private static int redisPort;
	// 对象锁，用于lazy加载
	private static Object lock = new Object();

	/**
	 * 获取redis连接
	 * 
	 * @return
	 * @throws Exception
	 */
	public static Jedis getJedisResource() throws Exception {
		if (jedisPool == null) {
			synchronized (lock) {
				if (poolConfig == null) {
					loadConfig();
				}

				if (jedisPool == null) {
					jedisPool = new JedisPool(poolConfig, redisHost, redisPort);
				}
			}
		}
		try {
			return jedisPool.getResource();
		} catch (Exception e) {
			jedisPool = null;
			throw new Exception("Redis服务[" + redisHost + ":" + redisPort
					+ "]无法正常使用!");
		}

	}

	/**
	 * 释放redis连接
	 * 
	 * @param jedis
	 * @param isJedisConnectExeptionOccured
	 */
	public static void returnJedisResource(Jedis jedis,
			boolean isJedisConnectExeptionOccured) {
		if (jedis != null) {
			if (isJedisConnectExeptionOccured) {
				// 如果jedis连接出错必须调用此方法释放jedis到池中（释放之前清空缓冲区,否则可能出现脏数据）
				jedisPool.returnBrokenResource(jedis);
			} else {
				jedisPool.returnResource(jedis);
			}
		}

	}

	/**
	 * 重置redis连接池配置
	 */
	public static void resetJedisPool() {
		if (jedisPool != null)
			jedisPool.destroy();
		jedisPool = null;
		poolConfig = null;
	}

	private static void loadConfig() {
		// 加载配置文件
		InputStream stream = null;
		try {
			Properties props = PropertiesLoaderUtils
					.loadAllProperties("redis.properties");

			redisHost = props.getProperty("redis.host").trim();
			redisPort = Integer
					.parseInt(props.getProperty("redis.port").trim());

			poolConfig = new JedisPoolConfig();
			poolConfig.setMaxIdle(Integer.parseInt(props.getProperty(
					"redis.pool.maxIdle").trim()));
			poolConfig.setMaxTotal(Integer.parseInt(props.getProperty(
					"redis.pool.maxActive").trim()));
			poolConfig.setMaxWaitMillis(Long.parseLong(props.getProperty(
					"redis.pool.maxWait").trim()));
			poolConfig.setMinEvictableIdleTimeMillis(Long.parseLong(props
					.getProperty("redis.pool.minEvictableIdleTimeMillis")
					.trim()));
			poolConfig.setTimeBetweenEvictionRunsMillis(Long.parseLong(props
					.getProperty("redis.pool.timeBetweenEvictableRunsMillis")
					.trim()));
			poolConfig.setTestOnBorrow(Boolean.parseBoolean(props.getProperty(
					"redis.pool.testOnBorrow").trim()));
			poolConfig.setTestOnReturn(Boolean.parseBoolean(props.getProperty(
					"redis.pool.testOnReturn").trim()));
			poolConfig.setTestWhileIdle(Boolean.parseBoolean(props.getProperty(
					"redis.pool.testWhileIdle").trim()));

		} catch (Exception e) {
			log.error(e.getMessage(), e);
		} finally {
			if (stream != null) {
				try {
					stream.close();
				} catch (IOException e) {
					log.error(e.getMessage(), e);
				}
				stream = null;
			}
		}
	}
}
