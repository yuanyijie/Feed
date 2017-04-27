package feed.web.model.po;

/**
 * 粉丝记录Po
 * @author Boxbox
 *
 */
public class UserFanPo {
	
	/**
	 * 用户Id
	 */
	private Integer userId;
	
	/**
	 * 粉丝Id
	 */
	private Integer fanId;
	
	/**
	 * 类型 0粉丝 1互相关注
	 */
	private Integer type;

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Integer getFanId() {
		return fanId;
	}

	public void setFanId(Integer fanId) {
		this.fanId = fanId;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}
	
	
}
