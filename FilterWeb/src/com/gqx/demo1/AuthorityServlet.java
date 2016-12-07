package com.gqx.demo1;

import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AuthorityServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String methodName=request.getParameter("method");
		//为了让一个servlet响应多个请求，这里可以使用反射
		try {
			Method method=getClass().getMethod(methodName, HttpServletRequest.class,HttpServletResponse.class);
			method.invoke(this, request,response); 
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doGet(request, response);
	}
	
	private UserDao userDao=new UserDao();
	//获取用户所有的信息
	public void getAuthorities(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String userName=request.getParameter("username");
		User user =userDao.get(userName);
		request.setAttribute("user", user);
		request.setAttribute("authorities", userDao.getAuthorities());
		request.getRequestDispatcher("/authority-manager.jsp").forward(request, response);
	}
							
	public void updateAuthority(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String username = request.getParameter("username");
	
			String [] authorities = request.getParameterValues("authority");
			List<Authority> authorityList = userDao.getAuthorities(authorities);
			
			userDao.update(username, authorityList);
			response.sendRedirect(request.getContextPath() + "/authority-manager.jsp");
		
	}
	
}
