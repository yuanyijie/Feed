package feed.web.model.po;

/**
 * 关注记录Po
 * @author Boxbox
 *
 */
public class UserFollowPo {
	
	/**
	 * 用户Id
	 */
	private Integer userId;
	
	/**
	 * 被关注者Id
	 */
	private Integer followId;
	
	/**
	 * 类型 0关注 1互相关注
	 */
	private Integer type;

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Integer getFollowId() {
		return followId;
	}

	public void setFollowId(Integer followId) {
		this.followId = followId;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}
	
}
