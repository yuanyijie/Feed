package feed.web.service.task;

import java.util.List;
import java.util.concurrent.Callable;

import org.springframework.beans.factory.annotation.Autowired;

import feed.web.dao.UserMsgIndexDao;
import feed.web.model.po.UserMsgIndexPo;

/**
 * 简要的push feed 应该使用任务调度框架+中间件队列的方式来实现
 * 
 * @author Boxbox
 * 
 */
public class PushFeedTask implements Callable<Integer> {
	// 粉丝列表
	private List<Integer> fansList;
	// 作者用户Id
	private int userId;
	// 消息Id
	private int msgId;
	// Feed产生时间戳
	private int timeStamp;

	@Autowired
	private UserMsgIndexDao userMsgIndexDao;

	public PushFeedTask(List<Integer> fansList, int userId, int msgId, int timeStamp) {
		this.fansList = fansList;
		this.userId = userId;
		this.msgId = msgId;
		this.timeStamp = timeStamp;
	}

	@Override
	public Integer call() throws Exception {
		int count = 0;
		for (int followId : fansList) {
			UserMsgIndexPo msgIndex = new UserMsgIndexPo();
			msgIndex.setAuthId(userId);
			msgIndex.setUserId(followId);
			msgIndex.setTimeStamp(timeStamp);
			msgIndex.setMsgId(msgId);
			userMsgIndexDao.add(msgIndex);
			count++;
		}
		return count;
	}

}
