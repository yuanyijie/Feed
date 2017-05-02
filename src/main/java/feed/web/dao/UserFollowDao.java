package feed.web.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import feed.web.model.po.UserFollowPo;

/**
 * 用户关注Dao
 * @author Boxbox
 *
 */
@Repository
public interface UserFollowDao {
	/**
	 * 添加关注
	 * @param userFollow
	 */
	void add(UserFollowPo userFollow);
	
	/**
	 * 获取匹配关系的数目
	 * @param userFollow
	 */
	int getCount(UserFollowPo userFollow);
	
	/**
	 * 删除关注
	 * @param userFollow
	 */
	void delete(UserFollowPo userFollow);
	
	/**
	 * 更新关注的类型
	 * @param userFollow
	 */
	void updateType(UserFollowPo userFollow);
	
	/**
	 * 获取关注的类型
	 * @param userFollow
	 * @return
	 */
	int getType(UserFollowPo userFollow);
	
	/**
	 * 获取关注列表
	 * @param userId
	 * @return
	 */
	List<Integer> getFollowList(@Param(value="userId") int userId);
}
