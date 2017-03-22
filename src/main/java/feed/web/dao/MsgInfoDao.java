package feed.web.dao;

import org.springframework.stereotype.Repository;

import feed.web.model.po.MsgInfoPo;

@Repository
public interface MsgInfoDao {
	
	
	/**
	 * 添加消息
	 * @param po
	 */
	void add(MsgInfoPo po);

}
