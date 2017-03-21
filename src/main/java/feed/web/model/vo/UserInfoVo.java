package feed.web.model.vo;

import feed.web.model.po.UserInfoPo;

/**
 * 用户信息Po对象
 * @author Boxbox
 *
 */
public class UserInfoVo {
	
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

	public UserInfoVo(){}
	
	
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


	public UserInfoPo toPo(){
		UserInfoPo po = new UserInfoPo();
		po.setUserId(userId);
		po.setUserName(userName);
		po.setUserPwd(userPwd);
		po.setMsgCount(msgCount);
		po.setFansCount(fansCount);
		po.setFollowCount(followCount);
		po.setUserEmail(userEmail);
		return po;
	}
	
}
