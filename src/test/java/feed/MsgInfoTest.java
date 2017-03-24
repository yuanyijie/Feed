package feed;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;

import feed.web.common.Page;
import feed.web.service.MsgInfoService;

public class MsgInfoTest extends SpringTestBase {
	
	@Autowired
	private MsgInfoService msgInfoService; 
	
//	@Rollback(false)
//	@Test
//	public void postTest(){
//		msgInfoService.postFeed("test");
//	}
//	
	@Test
	public void homeTest(){
		changeUserId(2);
		Page page = new Page();
		page.setIndex(1);
		page.setChunk(10);
		System.out.println(msgInfoService.getHome(page));
	}
}
