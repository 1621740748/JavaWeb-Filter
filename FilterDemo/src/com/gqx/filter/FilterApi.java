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
		System.out.println("��ȡ��������web.xml�����õĳ�ʼ��������");
		String encoding=filterConfig.getInitParameter("encoding");
		System.out.println(encoding);
		//filterConfig.getFilterName() ��ȡ����
		Enumeration<String> enums=filterConfig.getInitParameterNames();
		while (enums.hasMoreElements()) {
			//��ȡ��Ӧ�Ĳ�������
			String name = (String) enums.nextElement();
			//��ȡ��Ӧ���Ƶ�ֵ
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
