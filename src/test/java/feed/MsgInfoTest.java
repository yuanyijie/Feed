package feed;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

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
		System.out.println(msgInfoService.getHome(1,10));
	}
}
