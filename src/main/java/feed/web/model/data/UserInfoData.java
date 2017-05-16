package feed.web.model.data;

/**
 * 用户信息
 * @author BoxBox
 *
 */
public class UserInfoData {
	
	/**
	 * 用户名
	 */
	private String userName;
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
	
}
