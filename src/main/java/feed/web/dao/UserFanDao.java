package feed.web.dao;

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
	 * 删除粉丝
	 * @param userFan
	 */
	void delete(UserFanPo userFan);
}
