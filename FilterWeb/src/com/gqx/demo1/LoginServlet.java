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
		//Ϊ����һ��servlet��Ӧ��������������ʹ�÷���
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
		//1����ȡ�û���username
		String name=request.getParameter("name");
		//2������userDao��ȡ��Ϣ�����û���Ϣ���뵽session�У�
		User user=userDao.get(name);
		request.getSession().setAttribute("user", user);
		//3���ض���article.jsp
		response.sendRedirect(request.getContextPath()+"/articles.jsp");
	}
	
	public void logout(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
			//1. ��ȡ HttpSession
		
				//2. ʹ HttpSession ʧЧ
				request.getSession().invalidate();
				
				//3. �ض��� /loign.jsp
				response.sendRedirect(request.getContextPath() + "/login.jsp");
	}

}
