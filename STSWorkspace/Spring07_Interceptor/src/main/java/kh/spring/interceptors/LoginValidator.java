package kh.spring.interceptors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;

public class LoginValidator implements HandlerInterceptor {
	
	@Autowired
	private HttpSession session;
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		String id = (String)session.getAttribute("loginID");
		if(id != null) {
			return true;
			//가던 길 계속 가세요.
		}
		response.sendRedirect("/error");
		return false;
		//안 돼. 돌아가.
	}
}
