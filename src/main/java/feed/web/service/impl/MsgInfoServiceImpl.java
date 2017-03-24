package feed.web.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import feed.web.common.Page;
import feed.web.common.TypeEnum.FeedEnum;
import feed.web.common.util.FeedAssert;
import feed.web.dao.MsgInfoDao;
import feed.web.dao.UserInfoDao;
import feed.web.dao.UserRelationDao;
import feed.web.model.data.MsgInfoData;
import feed.web.model.po.MsgInfoPo;
import feed.web.service.MsgInfoService;
import feed.web.service.task.PushFeedTask;

@Service
public class MsgInfoServiceImpl extends BaseService implements MsgInfoService {

	@Autowired
	private MsgInfoDao msgInfoDao;

	@Autowired
	private UserInfoDao userInfoDao;

	@Autowired
	private UserRelationDao relationDao;

	@Autowired
	private PushFeedTask task;

	@Transactional
	@Override
	public void postFeed(String feedContent) {
		MsgInfoPo msgInfo = new MsgInfoPo();
		// 从session中取出UserId
		int userId = getUserId();
		msgInfo.setUserId(userId);
		msgInfo.setContent(feedContent);
		// 标记为原创消息
		msgInfo.setType(FeedEnum.ORIGIN.code());
		// 设置unix时间戳
		int timeStamp = (int) (System.currentTimeMillis() / 1000);
		msgInfo.setTimeStamp(timeStamp);

		// 取出msgId
		userInfoDao.msgCountIncrement(userId);
		int msgCount = userInfoDao.getMsgCount(userId);
		msgInfo.setMsgId(msgCount);
		msgInfoDao.add(msgInfo);

		// 获取粉丝列表
		List<Integer> fansList = relationDao.getFansList(userId);
		// fasout
		if (fansList != null && !fansList.isEmpty())
			task.call(fansList, userId, msgCount, timeStamp);
	}

	@Override
	public List<MsgInfoData> getHome(Page page) {
		FeedAssert.pageIsCorrect(page);
		return msgInfoDao.getMsgList(getUserId(), page.getIndex(), page.getChunk());
	}
}
