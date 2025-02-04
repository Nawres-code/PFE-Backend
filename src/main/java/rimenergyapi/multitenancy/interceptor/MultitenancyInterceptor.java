package rimenergyapi.multitenancy.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import rimenergyapi.multitenancy.util.CustomRequestAttributes;
import rimenergyapi.security.JwtTokenProvider;

public class MultitenancyInterceptor extends HandlerInterceptorAdapter {

	@Autowired
	private JwtTokenProvider jwtTokenProvider;

	@Value("${token.header}")
	private String tokenHeader;

	Logger logger = LoggerFactory.getLogger(MultitenancyInterceptor.class);

	@Override
	public boolean preHandle(HttpServletRequest req, HttpServletResponse res, Object handler) throws Exception {

		String token = req.getHeader(tokenHeader);
		if (token != null && !token.equals("")) {

			Integer userId = jwtTokenProvider.getUserIdFromToken(jwtTokenProvider.resolveToken(token));
			Integer subuserId = jwtTokenProvider.getSubUserIdFromToken(jwtTokenProvider.resolveToken(token));
			logger.info("----------------------------------------------------");
			logger.info("process tenant : " + userId);
			logger.info("----------------------------------------------------");

			req.setAttribute(CustomRequestAttributes.CURRENT_TENANT_IDENTIFIER, userId + "");
		}

		return true;
	}

}
