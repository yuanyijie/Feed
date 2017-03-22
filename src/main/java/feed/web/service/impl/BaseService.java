package feed.web.service.impl;

import java.security.Key;

import feed.web.common.auth.LocalObtainer;
import feed.web.common.util.FeedAssert;
import feed.web.model.UserInfoSession;

/**
 * 所有Service的基类
 * @author Boxbox
 *
 */
public abstract class BaseService {
	
	private LocalObtainer obtainer  = LocalObtainer.getInstance();
	
	/**
	 * 获取当前用户的userId
	 * @return
	 */
	protected int getUserId(){
		UserInfoSession session = obtainer.getSession();
		FeedAssert.notNull(session, "usersession is null");
		int userId = session.getUserId();
		FeedAssert.numberIsNotZero(userId, "userId is null");
		return userId;
	}
	
	/**
	 * 获取当前应用在启动时生成的Key
	 * @return
	 */
	protected Key getKey(){
		return obtainer.getKey();
	}
}
