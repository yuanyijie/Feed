package feed.web.common.auth;

import static feed.web.common.ResponseEnum.VOID_AUTHFAILED_JSON;
import feed.web.model.UserInfoSession;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureException;

import java.security.Key;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;

/**
 * 授权拦截器
 * 
 * @author Boxbox
 * 
 */
public class AuthInterceptor implements HandlerInterceptor {
	
	private final static Logger log = Logger.getLogger(AuthInterceptor.class); 
	private final static String FEED = "feedSession";
	private final static String LOGIN_PATH = "/users/login/";

	private LocalObtainer obtainer = LocalObtainer.getInstance();
	// 应用在启动时会生成这个秘钥
	private Key generatekey = obtainer.getKey();

	private AuthStrategy checkStrategy = new TimeAuthStrategy();

	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		if (shouldExclude(request))
			return true;
		String session = request.getHeader(FEED);
		if (session != null && !session.isEmpty()) {
			try {
				String userInfo = Jwts.parser().setSigningKey(generatekey)
						.parseClaimsJws(session).getBody().getSubject();
				UserInfoSession userInfoSession = JSON.parseObject(userInfo,
						UserInfoSession.class);
				if (checkStrategy.check(userInfoSession)) {
					obtainer.putSession(userInfoSession);
					return true;
				}
			} catch (SignatureException e) {
				log.warn(e.getMessage());
			} catch (MalformedJwtException e){
				log.warn(e.getMessage());
			}
		}
		// writer在容器实现层会被关闭
		response.getWriter().write(VOID_AUTHFAILED_JSON);
		return false;
	}

	@Override
	public void postHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {

	}

	@Override
	public void afterCompletion(HttpServletRequest request,
			HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		obtainer.clearSession();
	}

	private boolean shouldExclude(HttpServletRequest request) {
		String path = request.getServletPath();
		return path.startsWith(LOGIN_PATH);
	}

}
