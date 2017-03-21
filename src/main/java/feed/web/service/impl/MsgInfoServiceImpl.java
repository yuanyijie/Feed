package feed.web.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import feed.web.common.auth.LocalObtainer;
import feed.web.dao.MsgInfoDao;
import feed.web.dao.UserInfoDao;
import feed.web.model.vo.MsgInfoVo;
import feed.web.service.MsgInfoService;

@Service
public class MsgInfoServiceImpl implements MsgInfoService {

	@Autowired
	private MsgInfoDao msgInfoDao;

	@Autowired
	private UserInfoDao userInfoDao;

	private LocalObtainer obtainer = LocalObtainer.getInstance();

	@Transactional
	@Override
	public void postFeed(MsgInfoVo msgInfo) {
		// 从session中取出UserId
		int userId = obtainer.getSession().getUserId();
		msgInfo.setUserId(userId);
		
		// 取出msgId
		userInfoDao.msgCountIncrement(userId);
		int msgCount = userInfoDao.getMsgCount(userId);
		msgInfo.setMsgId(msgCount);
		
		msgInfoDao.add(msgInfo.toPo());
	}

}
