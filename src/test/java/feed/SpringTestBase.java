package feed;

import org.junit.After;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;

import feed.web.common.auth.LocalObtainer;
import feed.web.model.UserInfoSession;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration(value="src/main/webapp")
@ContextConfiguration({"classpath*:applicationContext.xml"})
@TransactionConfiguration(defaultRollback = true)  
@Transactional 
public class SpringTestBase extends AbstractTransactionalJUnit4SpringContextTests{
	
	protected  LocalObtainer obtainer = LocalObtainer.getInstance();
	private UserInfoSession session;
	
	@Before
	public void init(){
		session = new UserInfoSession();
		// 默认测试的userId为1
		session.setUserId(1);
		obtainer.putSession(session);
	}
	
	@After
	public void end(){
		obtainer.clearSession();
	}
	
	protected void changeUserId(int userId){
		session.setUserId(userId);
	}
	
}
