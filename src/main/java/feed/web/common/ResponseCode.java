package feed.web.common;

/**
 * 请求响应
 * 编码
 * 
 * @author Boxbox
 * 
 */
public class ResponseCode {

	private int code;

	private String message;
	
	public ResponseCode(int code, String message){
		this.code = code;
		this.message = message;
	}
	
	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
}
