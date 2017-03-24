package feed.web.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import feed.web.model.data.MsgInfoData;
import feed.web.model.po.MsgInfoPo;

@Repository
public interface MsgInfoDao {

	/**
	 * 添加消息
	 * 
	 * @param po
	 */
	void add(MsgInfoPo po);

	/**
	 * 以分页的方式
	 * 获取指定userId的feed列表
	 * @param userId
	 * @param index
	 * @param chunk
	 * @return
	 */
	List<MsgInfoData> getMsgList(@Param("userId") int userId,
			@Param("index") int index, @Param("chunk") int chunk);

}
