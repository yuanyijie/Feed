package feed.web.common;

/**
 * 请求回应体 包含一个data和一个请求回应
 * @author Boxbox
 *
 * @param <T>
 */
public class ResponseEntity<T> {
	
    private T data;
	
	private ResponseCode response;
	
	public ResponseEntity(){}
	
	public ResponseEntity(T data, ResponseCode response){
		this.data = data;
		this.response = response;
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

	public ResponseCode getResponse() {
		return response;
	}

	public void setResponse(ResponseCode response) {
		this.response = response;
	}
	
	
}
