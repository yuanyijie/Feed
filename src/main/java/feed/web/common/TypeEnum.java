package feed.web.common;

public class TypeEnum {
	
	/**
	 * 关注枚举类
	 * @author Boxbox
	 *
	 */
	public enum FollowEnum{
		
		/** 
		 * 0 关注 1互相关注
		 */
		FOLLOW(0),EACHOTHER(1);
		
		private int code;
		
		private FollowEnum(int code){
			this.code = code;
		}
		
		public int code(){
			return code;
		}
	}
	
	/**
	 * 粉丝枚举类
	 * @author Boxbox
	 *
	 */
	public enum FanEnum{
		/**
		 * 0粉丝 1互相关注
		 */
		FAN(0),EACHOTHER(1);
		
		private int code;
		
		private FanEnum(int code){
			this.code = code;
		}
		
		public int code(){
			return code;
		}
		
	}
	
	public enum FeedEnum{
		/**
		 * Feed的三种类型  原创 评论 转发
		 */
		ORIGIN(0),COMMENT(1),TRANS(2);
		
		private int code;
		
		private FeedEnum(int code){
			this.code = code;
		}
		
		public int code(){
			return code;
		}
	}
}
