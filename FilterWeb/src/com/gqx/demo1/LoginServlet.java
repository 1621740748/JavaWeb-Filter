package com.gqx.demo1;

import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Method;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginServlet extends HttpServlet {

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
	public void login(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//1、获取用户的username
		String name=request.getParameter("name");
		//2、调用userDao获取信息，把用户信息放入到session中，
		User user=userDao.get(name);
		request.getSession().setAttribute("user", user);
		//3、重定向到article.jsp
		response.sendRedirect(request.getContextPath()+"/articles.jsp");
	}
	
	public void logout(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
			//1. 获取 HttpSession
		
				//2. 使 HttpSession 失效
				request.getSession().invalidate();
				
				//3. 重定向到 /loign.jsp
				response.sendRedirect(request.getContextPath() + "/login.jsp");
	}

}
