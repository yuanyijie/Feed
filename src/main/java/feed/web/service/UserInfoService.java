package feed.web.service;

import java.util.List;

import org.springframework.stereotype.Service;

import feed.web.model.vo.MsgInfoVo;
import feed.web.model.vo.UserInfoVo;

/**
 * 用户信息Service
 * @author Boxbox
 *
 */
@Service
public interface UserInfoService {
	
	/**
	 * 添加用户
	 * @param userInfo
	 */
	void add(UserInfoVo userInfo);
	
	/**
	 * 获取用户信息
	 * @param userName
	 */
	UserInfoVo get(String userName);
	
	
	/**
	 * 更新用户属性
	 * @param userId
	 * @param userInfo
	 */
	void update(UserInfoVo userInfo);
	
	/**
	 * 用户登录
	 * @param email
	 * @param password
	 * @return 登录成功返回一个JWT字符串，否则返回一个null
	 */
	String login(String email, String password);
	
	/**
	 * 获取当前用户的home feed
	 * @return
	 */
	List<MsgInfoVo> getHomeFeeds();  
}
