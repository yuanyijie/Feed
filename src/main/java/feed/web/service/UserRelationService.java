package feed.web.service;

/**
 * 用户关系Service
 * @author Boxbox
 *
 */
public interface UserRelationService {
	
	/**
	 * 关注
	 * @param userId
	 * @param followId
	 */
	void follow(int followId);
	
	
	/**
	 * 取消关注
	 * @param userId
	 * @param followId
	 */
	void unFollow(int followId);
}
