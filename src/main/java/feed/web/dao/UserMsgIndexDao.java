package feed.web.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import feed.web.model.po.UserMsgIndexPo;

/**
 * 用户消息索引(相当于FeedList)Dao
 * 
 * @author Boxbox
 * 
 */
@Repository
public interface UserMsgIndexDao {

	/**
	 * 添加一个用户消息索引
	 * 
	 * @param userMsgIndex
	 */
	void add(UserMsgIndexPo userMsgIndex);

	void addAll(@Param("msgtemp") UserMsgIndexPo userMsgIndex,
			@Param("fansList") List<Integer> fansList);
}
