package feed.web.common;

public class TypeEnum {
	
	/**
	 * 关注枚举类
	 * @author Boxbox
	 *
	 */
	public enum FollowEnum{
		
		/** 
		 * 动词关系  拥有一个粉丝 拥有一个关注
		 */
		FANS(0),FOLLOW(1);
		
		private int code;
		
		private FollowEnum(int code){
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
