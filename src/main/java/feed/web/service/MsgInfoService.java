package feed.web.service;

import java.util.List;

import feed.web.common.Page;
import feed.web.model.data.MsgInfoData;


/**
 * 
 * @author Boxbox
 * 
 */
public interface MsgInfoService {

	/**
	 * post新的feed
	 * @param feedContent
	 */
	void postFeed(String feedContent);
	
	/**
	 * 分页获取新的home消息
	 * @param page
	 * @return
	 */
	List<MsgInfoData> getHome(Page page);
}
