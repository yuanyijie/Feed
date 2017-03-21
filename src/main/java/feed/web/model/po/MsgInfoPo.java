package feed.web.model.po;

import feed.web.model.vo.MsgInfoVo;

/**
 * 元消息数据Po
 * 
 * @author Boxbox
 * 
 */
public class MsgInfoPo {

	/**
	 * 用户Id
	 */
	private Integer userId;

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
	 * 评论过数量 只增不减
	 */
	private Integer commentedCount;

	/**
	 * 当前评论数量
	 */
	private Integer commentCount;

	/**
	 * 转发过数量 只增不减
	 */
	private Integer transferredCount;

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

	public Integer getCommentedCount() {
		return commentedCount;
	}

	public void setCommentedCount(Integer commentedCount) {
		this.commentedCount = commentedCount;
	}

	public Integer getCommentCount() {
		return commentCount;
	}

	public void setCommentCount(Integer commentCount) {
		this.commentCount = commentCount;
	}

	public Integer getTransferredCount() {
		return transferredCount;
	}

	public void setTransferredCount(Integer transferredCount) {
		this.transferredCount = transferredCount;
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

	public MsgInfoVo toVo() {
		MsgInfoVo vo = new MsgInfoVo();
		vo.setUserId(userId);
		vo.setMsgId(msgId);
		vo.setContent(content);
		vo.setType(type);
		vo.setCommentedCount(commentedCount);
		vo.setCommentCount(commentCount);
		vo.setTransferredCount(transferredCount);
		vo.setTransferCount(transferCount);
		vo.setTimeStamp(timeStamp);
		return vo;
	}
}
