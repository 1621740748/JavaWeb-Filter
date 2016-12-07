package com.gqx.filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
/**
 * 测试的过滤器
 * @author Administrator
 *
 */
public class HelloFilter implements Filter {

	//创建实例
	public HelloFilter() {
	// TODO Auto-generated constructor stub
		System.out.println("1、过滤器用例开始创建了。");
	}
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		
		// TODO Auto-generated method stub
		System.out.println("2、执行过滤器初始化方法！");
	}
	
	//过滤器业务处理方法，在请求到达servlet之前进入此方法处理公用的业务逻辑操作
	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		// TODO Auto-generated method stub
		System.out.println("3、第一个过滤器：执行过滤器的业务处理方法！");
		//放行（去到Servlet）
		chain.doFilter(request, response);
	
		System.out.println("5、第一个过滤器：Servlet处理完成，又回到Filter了!");
	}

	
	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		System.out.println("6、同时过滤器被销毁了！");
	}



	
}
