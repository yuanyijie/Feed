package feed;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;

import feed.web.model.UserInfoSession;
import feed.web.service.UserRelationService;

/**
 * 
 * @author Boxbox
 *
 */
public class UserRelationTest extends SpringTestBase {
	
	@Autowired
	private UserRelationService service;
	
	@Test
	@Rollback(false)
	public void addFollow(){
		long startTime =  System.currentTimeMillis();
		UserInfoSession session = new UserInfoSession();
		session.setUserId(2);
		obtainer.putSession(session);
		
		service.follow(1);
		service.unFollow(1);
		long endTime = System.currentTimeMillis();
		System.out.println((endTime-startTime)+"ms");
		obtainer.clearSession();
	}
}
