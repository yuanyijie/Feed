package feed.web.common;

import com.alibaba.fastjson.JSON;

/**
 * 结果编码
 * 
 * @author Boxbox
 * 
 */
public class ResponseEnum {
	// code
	public final static int CODE_SUCCESS = 0;
	public final static int CODE_DBFAILED = -1;
	public final static int CODE_LOGICFAILED = -2;
	public final static int CODE_UNCATCHED = -3;
	public final static int CODE_AUTHFAILED = -4;
	
	// 成功
	public final static ResponseCode SUCCESS = new ResponseCode(CODE_SUCCESS,
			"success");
	// 数据库异常
	public final static ResponseCode DBFAILED = new ResponseCode(CODE_DBFAILED,
			"dbfailed");
	// 业务异常
	public final static ResponseCode LOGICFAILED = new ResponseCode(
			CODE_LOGICFAILED, "logicfailed");
	// 未知异常
	public final static ResponseCode UNCATCHED = new ResponseCode(
			CODE_UNCATCHED, "uncatched");
	// 授权异常
	public final static ResponseCode AUTHFAILED = new ResponseCode(
			CODE_AUTHFAILED, "authfailed");

	public final static ResponseEntity<Void> VOID_SUCCESS = new ResponseEntity<Void>(
			null, SUCCESS);
	public final static ResponseEntity<Void> VOID_DBFAILED = new ResponseEntity<Void>(
			null, DBFAILED);
	public final static ResponseEntity<Void> VOID_LOGICFAILED = new ResponseEntity<Void>(
			null, LOGICFAILED);
	public final static ResponseEntity<Void> VOID_UNCATCHED = new ResponseEntity<Void>(
			null, UNCATCHED);
	public final static ResponseEntity<Void> VOID_AUTHFAILED = new ResponseEntity<Void>(
			null, AUTHFAILED);

	public final static String VOID_AUTHFAILED_JSON = JSON
			.toJSONString(VOID_AUTHFAILED);
}
