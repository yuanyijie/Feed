package feed.web.common.exception;

/**
 * feed系统数据库异常
 * 
 * @author Boxbox
 * 
 */
public class FeedBaseException extends RuntimeException {

	private static final long serialVersionUID = -5324408538502217532L;

	private int errorCode;

	private String errorMsg;

	public FeedBaseException(int errorCode, String errorMsg) {
		this.errorCode = errorCode;
		this.errorMsg = errorMsg;
	}

	public int getCode() {
		return errorCode;
	}

	@Override
	public String getMessage() {
		return errorMsg;
	}

	public void setErrorCode(int errorCode) {
		this.errorCode = errorCode;
	}

	public void setErrorMsg(String errorMsg) {
		this.errorMsg = errorMsg;
	}

}
