package feed.web.service.impl;

import java.util.List;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;

import feed.web.common.util.FeedAssert;
import feed.web.common.util.MD5Util;
import feed.web.dao.UserInfoDao;
import feed.web.model.UserInfoSession;
import feed.web.model.po.UserInfoPo;
import feed.web.model.vo.MsgInfoVo;
import feed.web.model.vo.UserInfoVo;
import feed.web.service.UserInfoService;

@Service
public class UserInfoServiceImpl extends BaseService implements UserInfoService {

	@Autowired
	private UserInfoDao userInfoDao;

	@Override
	public void add(UserInfoVo userInfo) {
		String userPwd = userInfo.getUserPwd();
		String userEmail = userInfo.getUserEmail();
		FeedAssert.notNullOrEmpty(userInfo.getUserName(), userPwd, userEmail);
		FeedAssert.numberIsZero(userInfoDao.emailCount(userEmail), "该邮箱已经被使用");
		// 将密码MD5化
		userInfo.setUserPwd(MD5Util.generate(userPwd));
		UserInfoPo userPo = userInfo.toPo();
		userInfoDao.add(userPo);
	}

	@Override
	public UserInfoVo get(String userName) {
		UserInfoPo userPo = userInfoDao.get(userName);
		return userPo.toVo();
	}

	@Override
	public void update(UserInfoVo userInfo) {
		UserInfoPo userPo = userInfo.toPo();
		userInfoDao.update(getUserId(), userPo);
	}

	@Override
	public String login(String email, String password) {
		FeedAssert.notNullOrEmpty(email, password);
		// 将密码MD5化
		password = MD5Util.generate(password);
		Integer userId = userInfoDao.checkAccount(email, password);
		FeedAssert.notNull(userId, "用户名或者密码不正确");

		String loginToken = null;
		UserInfoSession session = new UserInfoSession();
		session.setUserId(userId);
		session.setTimeStamp(System.currentTimeMillis());
		// 生成loginToken
		loginToken = Jwts.builder().setSubject(JSON.toJSONString(session))
				.signWith(SignatureAlgorithm.HS256, getKey()).compact();
		return loginToken;
	}

	@Override
	public List<MsgInfoVo> getHomeFeeds() {
		return null;
		/**
		 * SELECT a.* FROM t_msg_info a JOIN t_user_msg_index b ON a.user_id =
		 * b.author_id WHERE b.user_id = '2' LIMIT 0,10
		 */
	}

}
