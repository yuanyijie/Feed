package feed.exec.manager;

import java.util.ArrayList;
import java.util.List;

import feed.exec.storage.redis.structure.SortedSet;
import feed.web.common.util.CollectionUtil;

/**
 * Feed的timeline存储
 * 
 * @author Boxbox
 * 
 */
public class FeedManager {
	// 用户feed在redis中的前缀
	private final static String TMLINE_PREFIX = "feed_";
	private final SortedSet sortedSet = new SortedSet();

	public static FeedManager getInstance() {
		return TimeLineManagerHolder.instance;
	}

	/**
	 * 添加activity
	 * 
	 * @param keysList
	 * @param userId
	 * @param msgId
	 */
	public void addActivities(List<Integer> keysList, int userId, int msgId) {
		List<String> keys = CollectionUtil.convertToStringList(TMLINE_PREFIX,
				keysList);
		List<String> data = new ArrayList<>(1);
		data.add(String.valueOf(msgId));
		sortedSet.addToStorageUspipline(data, keys);
	}

	/**
	 * 获取activity
	 * 
	 * @param userId
	 * @param start
	 * @param end
	 * @return 返回指定用户的分页activities
	 */
	public List<Integer> getActivities(int userId, int start, int end) {
		String feedName = TMLINE_PREFIX + userId;
		List<String> sliceList = sortedSet.getSlice(feedName, start, end);
		return CollectionUtil.convertToIntegerList(sliceList);
	}

	private static class TimeLineManagerHolder {
		private final static FeedManager instance = new FeedManager();
	}

	private FeedManager() {
	}
}
