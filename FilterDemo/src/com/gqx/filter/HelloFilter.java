package com.gqx.filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
/**
 * ���ԵĹ�����
 * @author Administrator
 *
 */
public class HelloFilter implements Filter {

	//����ʵ��
	public HelloFilter() {
	// TODO Auto-generated constructor stub
		System.out.println("1��������������ʼ�����ˡ�");
	}
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		
		// TODO Auto-generated method stub
		System.out.println("2��ִ�й�������ʼ��������");
	}
	
	//������ҵ�������������󵽴�servlet֮ǰ����˷��������õ�ҵ���߼�����
	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		// TODO Auto-generated method stub
		System.out.println("3����һ����������ִ�й�������ҵ��������");
		//���У�ȥ��Servlet��
		chain.doFilter(request, response);
	
		System.out.println("5����һ����������Servlet������ɣ��ֻص�Filter��!");
	}

	
	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		System.out.println("6��ͬʱ�������������ˣ�");
	}



	
}
