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
		//ת�ͣ�Ϊ�˵õ�����request�ķ���������contextPath������
		final HttpServletRequest request=(HttpServletRequest)req;
		HttpServletResponse response=(HttpServletResponse)res;
		/**
		 * ������ҵ�񣬱�������
		 */
		
		//���Post��������
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		
		//���Get��ʽ�ύ���������⣨���ȥ�̳�HttpServletRequest��д������̫�࣬�����㣩�����������
		HttpServletRequest proxy=(HttpServletRequest)Proxy.newProxyInstance(request.getClass().getClassLoader(),		//ָ����ǰʹ�õ��ۼ�����
				new Class[]{HttpServletRequest.class},							//ָ��Ŀ��ʵ����Ľӿ�����
				new InvocationHandler() {				//���ﴦ����
					@Override
					public Object invoke(Object proxy, Method method, Object[] args)
							throws Throwable {
						// TODO Auto-generated method stub
						
						//���巽������ֵ
						Object returnValue=null;
						//��ȡ����
						String methodName=method.getName();
						//�жϣ���getParameter����Get�ύ�������Ĵ���
						if ("getParameter".equals(methodName)) {
							//args�Ƿ����Ĳ���,����request.getParameter(name)�е�name
							String value=request.getParameter(args[0].toString());		//��ȡ���������ֵ�����ڽ���ת��
							/*
							 * �ж��ǲ���GET�ύ
							 */
							//��ȡ����ʽ
							String reqMthod=request.getMethod();
							//��GET���󣬿�ʼ����
							if ("GET".equals(reqMthod) && value !=null && !"".equals(value)) {
								value=new String(value.getBytes("iso-8859-1"),"utf-8");
							}
							return value;
						}else {
							//����request.getParameter()�ķ�����ֱ��ִ��
							returnValue=method.invoke(request, args);
						}
						return returnValue;
					}
				});
		
		//����
		chain.doFilter(proxy, response);
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub
		
	}

}
