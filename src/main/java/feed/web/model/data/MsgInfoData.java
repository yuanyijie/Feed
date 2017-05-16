package feed.web.model.data;

/**
 * 元消息分页展示的对象
 * @author Boxbox
 *
 */
public class MsgInfoData {
	/**
	 * 用户Id
	 */
	private Integer userId;
	
	/**
	 * 用户名
	 */
	private String userName;

	/**
	 * 消息Id
	 */
	private Integer msgId;

	/**
	 * 消息内容
	 */
	private String content;

	/**
	 * 消息类型 0原创 1评论 2转发
	 */
	private Integer type;

	/**
	 * 当前评论数量
	 */
	private Integer commentCount;

	/**
	 * 当前转发数量
	 */
	private Integer transferCount;

	/**
	 * 时间戳
	 */
	private Integer timeStamp;

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Integer getMsgId() {
		return msgId;
	}

	public void setMsgId(Integer msgId) {
		this.msgId = msgId;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public Integer getCommentCount() {
		return commentCount;
	}

	public void setCommentCount(Integer commentCount) {
		this.commentCount = commentCount;
	}

	public Integer getTransferCount() {
		return transferCount;
	}

	public void setTransferCount(Integer transferCount) {
		this.transferCount = transferCount;
	}

	public Integer getTimeStamp() {
		return timeStamp;
	}

	public void setTimeStamp(Integer timeStamp) {
		this.timeStamp = timeStamp;
	}
	
	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	@Override
	public String toString() {
		return "MsgInfoData [userId=" + userId + ", msgId=" + msgId + ", content=" + content + ", type=" + type
				+ ", commentCount=" + commentCount + ", transferCount=" + transferCount + ", timeStamp=" + timeStamp
				+ "]";
	}
	
}
