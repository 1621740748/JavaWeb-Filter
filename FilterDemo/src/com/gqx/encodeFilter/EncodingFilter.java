package com.gqx.encodeFilter;

import java.io.IOException;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class EncodingFilter implements Filter {

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse res,
			FilterChain chain) throws IOException, ServletException {
		// TODO Auto-generated method stub
		//转型，为了得到更多request的方法，比如contextPath方法等
		final HttpServletRequest request=(HttpServletRequest)req;
		HttpServletResponse response=(HttpServletResponse)res;
		/**
		 * 处理公用业务，编码问题
		 */
		
		//解决Post乱码问题
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		
		//解决Get方式提交的乱码问题（如果去继承HttpServletRequest重写，方法太多，不方便），事物代理解决
		HttpServletRequest proxy=(HttpServletRequest)Proxy.newProxyInstance(request.getClass().getClassLoader(),		//指定当前使用的累加器类
				new Class[]{HttpServletRequest.class},							//指定目标实现类的接口类型
				new InvocationHandler() {				//事物处理器
					@Override
					public Object invoke(Object proxy, Method method, Object[] args)
							throws Throwable {
						// TODO Auto-generated method stub
						
						//定义方法返回值
						Object returnValue=null;
						//获取方法
						String methodName=method.getName();
						//判断，对getParameter方的Get提交进行中文处理
						if ("getParameter".equals(methodName)) {
							//args是方法的参数,形如request.getParameter(name)中的name
							String value=request.getParameter(args[0].toString());		//获取请求参数的值，便于将其转码
							/*
							 * 判断是不是GET提交
							 */
							//获取请求方式
							String reqMthod=request.getMethod();
							//是GET请求，开始处理
							if ("GET".equals(reqMthod) && value !=null && !"".equals(value)) {
								value=new String(value.getBytes("iso-8859-1"),"utf-8");
							}
							return value;
						}else {
							//不是request.getParameter()的方法，直接执行
							returnValue=method.invoke(request, args);
						}
						return returnValue;
					}
				});
		
		//放行
		chain.doFilter(proxy, response);
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub
		
	}

}
