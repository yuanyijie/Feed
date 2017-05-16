package feed.web.dao;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import feed.web.model.data.UserInfoData;
import feed.web.model.po.UserInfoPo;

@Repository
public interface UserInfoDao {
	
	/**
	 * 添加用户
	 * @param po
	 */
	void add(UserInfoPo userInfo);
	
	/**
	 * 根据用户姓名查看用户信息
	 * @param userName
	 * @return 
	 */
	UserInfoPo get(@Param(value="userName") String userName);
	
	/**
	 * 根据用户Id修改用户信息
	 * @param userId
	 * @param po
	 */
	void update(@Param(value="_userId") int userId,@Param(value="userInfo") UserInfoPo userInfo);
	
	
	/**
	 * 粉丝数累加
	 * @param userId
	 */
	void fansIncrement(@Param(value="userId") int userId);
	
	/**
	 * 粉丝数递减
	 */
	void fansDecrement(@Param(value="userId") int userId);
	
	/**
	 * 关注数递增
	 * @param userId
	 */
	void followIncrement(@Param(value="userId") int userId);
	
	/**
	 * 关注数递减
	 * @param userId
	 */
	void followDecrement(@Param(value="userId") int userId);
	
	/**
	 * 更新用户消息数
	 * @param userId
	 * @return
	 */
	void msgCountIncrement(@Param(value="userId") int userId);
	
	/**
	 * 获取用户消息数
	 * @param userId
	 * @return
	 */
	int getMsgCount(@Param(value="userId") int userId);
	
	/**
	 * 检查用户名密码是否有对应的条目
	 * @param userEmail
	 * @param userPwd
	 * @return null或者查询到的userId
	 */
	Integer checkAccount(@Param(value="userEmail") String userEmail, @Param(value="userPwd") String userPwd);
	
	/**
	 * 统计当前用户表中指定email的个数
	 * @param userEmail
	 * @return
	 */
	int emailCount(@Param(value="userEmail") String userEmail);
	
	/**
	 * 根据userId查询数目
	 * @param userId
	 * @return
	 */
	int countById(@Param(value="userId") int userId);
	
	/**
	 * 根据userId查询用户Data
	 * @param userId
	 * @return
	 */
	UserInfoData getUserDataById(@Param(value="userId") int userId);
}
