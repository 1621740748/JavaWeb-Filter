package com.gqx.login;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LogServlet extends HttpServlet {
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String name=request.getParameter("user");
		
		if (name!=null && name!="") {
			request.getSession().setAttribute("user", name);
			response.sendRedirect(request.getContextPath()+"/login/list.jsp");
		}else {
			response.sendRedirect(request.getContextPath()+"/login/login.jsp");
		}
		
	}

	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doGet(request, response);
	}

}
