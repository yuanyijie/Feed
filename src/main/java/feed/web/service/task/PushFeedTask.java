package feed.web.service.task;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import feed.exec.manager.FeedManager;
import feed.web.dao.UserMsgIndexDao;
import feed.web.model.po.UserMsgIndexPo;

/**
 * 简要的push feed 应该使用任务调度框架+中间件队列的方式来实现
 * 
 * @author Boxbox
 * 
 */
@Component
public class PushFeedTask {
	private final static Logger log = Logger.getLogger(PushFeedTask.class);

	@Autowired
	private UserMsgIndexDao userMsgIndexDao;

	private FeedManager feedManager = FeedManager.getInstance();

	private boolean infoEnable = log.isInfoEnabled();

	@Async
	@Transactional(propagation = Propagation.NOT_SUPPORTED)
	// 大批量插入的时候不使用事务
	public void call(List<Integer> fansList, int userId, int msgId,
			int timeStamp) {
		long start = 0, end;
		UserMsgIndexPo msgIndex = new UserMsgIndexPo();
		// 将自身添加到粉丝队列的头部
		fansList.add(0, userId);
		if (infoEnable)
			start = System.currentTimeMillis();
		msgIndex.setAuthId(userId);
		msgIndex.setTimeStamp(timeStamp);
		msgIndex.setMsgId(msgId);
		
		try {
			userMsgIndexDao.addAll(msgIndex, fansList);
			// 推到Redis中粉丝的feed优先级队列
			feedManager.addActivities(fansList, userId, msgId);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}
		
		if (infoEnable) {
			end = System.currentTimeMillis();
			// 落任务日志
			log.info("userId:"+userId+" msgId:"+msgId+" fansCount:"+fansList.size()+"-"+(end-start)+"ms");
		}
	}

}
