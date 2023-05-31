package kh.spring.interceptors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;

public class Profiler implements HandlerInterceptor {
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		request.getSession().setAttribute("startTime", System.currentTimeMillis());
		return true;
	}
	
	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		System.out.println(request.getRequestURI() + "요청은 " + (System.currentTimeMillis() - (long)request.getSession().getAttribute("startTime"))+"ms 걸렸음.");
		request.getSession().removeAttribute("startTime");
	}
}
