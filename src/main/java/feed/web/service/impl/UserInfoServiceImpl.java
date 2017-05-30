package feed.web.service.impl;

import java.io.IOException;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSON;

import feed.web.common.GlobalConfig;
import feed.web.common.exception.FeedServiceException;
import feed.web.common.filestorage.FileStorage;
import feed.web.common.picture.PictureTool;
import feed.web.common.util.FeedAssert;
import feed.web.common.util.MD5Util;
import feed.web.dao.UserInfoDao;
import feed.web.model.UserInfoSession;
import feed.web.model.data.AvatarData;
import feed.web.model.data.UserInfoData;
import feed.web.model.po.UserInfoPo;
import feed.web.model.vo.UserInfoVo;
import feed.web.service.UserInfoService;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Service
public class UserInfoServiceImpl extends BaseService implements UserInfoService {

	private final static Logger log = Logger.getLogger(UserInfoServiceImpl.class);

	@Autowired
	private UserInfoDao userInfoDao;

	@Autowired
	private FileStorage fileStorage;

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
		loginToken = Jwts.builder().setSubject(JSON.toJSONString(session)).signWith(SignatureAlgorithm.HS256, getKey())
				.compact();
		return loginToken;
	}

	@Override
	public UserInfoData getCard() {
		int userId = getUserId();
		UserInfoData result = userInfoDao.getUserDataById(userId);
		return result;
	}

	@Override
	public String uploadAvatar(MultipartFile avatarFile, AvatarData avatarData) {
		String fileId = null;
		try {
			byte[] dataBytes = PictureTool.getInstance().cropAvatar(avatarFile.getInputStream(), avatarData);
			fileId = fileStorage.save(dataBytes, "png");
		} catch (IOException e) {
			log.error(e.getMessage(), e);
			throw new FeedServiceException(-1, "upload fail");
		}
		fileId = GlobalConfig.FDFS_ADDRESS + fileId;
		userInfoDao.updateUserAvatar(getUserId(), fileId);
		return fileId;
	}

}
