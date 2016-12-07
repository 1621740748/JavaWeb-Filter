package com.gqx.filter;

import java.io.IOException;
import java.util.Enumeration;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

public class FilterApi implements Filter {
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		// TODO Auto-generated method stub
		System.out.println("获取过滤器在web.xml中配置的初始化参数！");
		String encoding=filterConfig.getInitParameter("encoding");
		System.out.println(encoding);
		//filterConfig.getFilterName() 获取单个
		Enumeration<String> enums=filterConfig.getInitParameterNames();
		while (enums.hasMoreElements()) {
			//获取对应的参数名称
			String name = (String) enums.nextElement();
			//获取对应名称的值
			String value=filterConfig.getInitParameter(name);
			System.out.println(name+"\t"+value);
		}
	}
	@Override
	public void destroy() {
		// TODO Auto-generated method stub
	}
	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		// TODO Auto-generated method stub	
	}
}
