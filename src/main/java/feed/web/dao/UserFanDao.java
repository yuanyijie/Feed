package feed.web.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import feed.web.model.po.UserFanPo;

/**
 * 粉丝记录Dao
 * @author Boxbox
 *
 */
@Repository
public interface UserFanDao {
	
	/**
	 * 添加粉丝
	 * @param userFan
	 */
	void add(UserFanPo userFan);
	
	/**
	 * 获取匹配关系的数目
	 * @param userFan
	 * @return
	 */
	int getCount(UserFanPo userFan);
	
	/**
	 * 删除粉丝
	 * @param userFan
	 */
	void delete(UserFanPo userFan);
	
	/**
	 * 更新粉丝的类型
	 * @param userFan
	 */
	void updateType(UserFanPo userFan);
	
	/**
	 * 获取粉丝列表
	 * @param userId
	 * @return
	 */
	List<Integer> getFansList(@Param(value="userId") int userId);
}
