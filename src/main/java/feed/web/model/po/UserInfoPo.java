package feed.web.model.po;

import feed.web.model.vo.UserInfoVo;

/**
 * 用户信息Po对象
 * @author Boxbox
 *
 */
public class UserInfoPo {
	
	/**
	 * 用户Id
	 */
	private Integer userId;
	
	/**
	 * 用户名
	 */
	private String userName;
	
	/**
	 * 用户密码
	 */
	private String userPwd;
	
	/**
	 * 用户邮箱
	 */
	private String userEmail;
	
	/**
	 * 消息总数
	 */
	private Integer msgCount;
	
	/**
	 * 粉丝数
	 */
	private Integer fansCount;
	
	/**
	 * 关注数
	 */
	private Integer followCount;

	public UserInfoPo(){}
	
	
	public Integer getUserId() {
		return userId;
	}
	
	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public Integer getMsgCount() {
		return msgCount;
	}

	public void setMsgCount(Integer msgCount) {
		this.msgCount = msgCount;
	}

	public Integer getFansCount() {
		return fansCount;
	}

	public void setFansCount(Integer fansCount) {
		this.fansCount = fansCount;
	}

	public Integer getFollowCount() {
		return followCount;
	}

	public void setFollowCount(Integer followCount) {
		this.followCount = followCount;
	}
	
	public String getUserPwd() {
		return userPwd;
	}

	public void setUserPwd(String userPwd) {
		this.userPwd = userPwd;
	}
	
	public String getUserEmail() {
		return userEmail;
	}

	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}

	public UserInfoVo toVo(){
		UserInfoVo vo = new UserInfoVo();
		vo.setUserId(userId);
		vo.setUserName(userName);
		vo.setUserPwd(userPwd);
		vo.setMsgCount(msgCount);
		vo.setFansCount(fansCount);
		vo.setFollowCount(followCount);
		vo.setUserEmail(userEmail);
		return vo;
	}
	
}
