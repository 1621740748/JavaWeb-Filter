package com.gqx.demo1;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import com.sun.xml.internal.bind.v2.runtime.Name;

public class XSSFilter implements Filter{

	@Override
	public void destroy() {
		
	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse resp, FilterChain filter)
			throws IOException, ServletException {
		
			String s=req.getParameter("name");
			if(s!=null&&s.contains("<")) {
				resp.setCharacterEncoding("GBK");
				
				resp.getWriter().write("name 参数不合法！");
				return ;
			}
			else {
				filter.doFilter(req, resp);
			}
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		
	}

}
