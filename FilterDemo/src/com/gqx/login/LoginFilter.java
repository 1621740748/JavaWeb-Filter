package com.gqx.login;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.jms.Session;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginFilter implements Filter {
	
	private String userSession;
	private String rediretPage;
	private String uncheckedUrl;
	
	@Override
	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub
		ServletContext servletContext=arg0.getServletContext();
		
		userSession=servletContext.getInitParameter("userSession");
		rediretPage=servletContext.getInitParameter("rediretPage");
		uncheckedUrl=servletContext.getInitParameter("uncheckedUrl");
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void doFilter(ServletRequest arg0, ServletResponse arg1,
			FilterChain arg2) throws IOException, ServletException {
		// TODO Auto-generated method stub
		HttpServletRequest request=(HttpServletRequest)arg0;
		HttpServletResponse response=(HttpServletResponse)arg1;
		
		//1、获取来的请求的URL
		String requestUrl=request.getRequestURL().toString();	// http://localhost:8080/FilterDemo/login/login.jsp
		String requestUri=request.getRequestURI().toString();// /FilterDemo/login/login.jsp
		String  servletPath=request.getServletPath();// /login/login.jsp

		//2、检查1获取的servletPath是否为不需要检查的URL中的而一个
		List<String> urls=Arrays.asList(uncheckedUrl.split(","));
		if (urls.contains(servletPath)) {
			arg2.doFilter(request, response);
			return;
		}
		
		//3、从session中获取userSession，判断值是否存在
		Object user=request.getSession().getAttribute("user");
		
		if (user==null) {
			response.sendRedirect(request.getContextPath()+rediretPage);
			return;
		}
		
		//4、存在，就允许访问
		arg2.doFilter(request, response);
	}

	

}
