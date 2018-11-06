package com.gqx.demo1;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Enumeration;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ReadListener;
import javax.servlet.ServletException;
import javax.servlet.ServletInputStream;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

import org.apache.commons.io.IOUtils;

public class XSSFilter implements Filter {
	private static final String[] xssPattern=new String[] {
			"<script"
			,"<iframe"
			,"<noscript"
			,"<noiframe"
			,"alert("
			};

	@Override
	public void destroy() {

	}
    private boolean isForbid(String value) {
    	value=value.toLowerCase();
    	value=value.replaceAll("\\s*", "");
    	for(String xss:xssPattern) {
    		if(value.indexOf(xss)>=0) {
    			return true;
    		}
    	}
    	return false;
    }
	
	private boolean checkXSS(ServletRequest req, ServletResponse resp) {
		try {
			for (Enumeration<String> paramNames = req.getParameterNames(); paramNames.hasMoreElements();) {
				String paramName = paramNames.nextElement();
				String[] values = req.getParameterValues(paramName);
				if(values!=null&&values.length>0) {
					for(String value:values) {
						if(value!=null&&value.length()>0) {
							if(isForbid(value)) {
								System.out.println("参数:"+paramName+",值:"+value+" 不合法！");
								resp.getWriter().write("怀疑收到xss攻击，请联系管理员！");
								return false;
							}
						}
					}
				}

			}
		} catch (Exception e) {
			return false;
		}
		return true;
	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain)
			throws IOException, ServletException {

		ResettableStreamRequestWrapper wrappedRequest = new ResettableStreamRequestWrapper((HttpServletRequest) req);
		boolean result=this.checkXSS(wrappedRequest, resp);
		//如果异常，不继续往后执行
		if(!result) {
			return ;
		}
		wrappedRequest.resetInputStream();
		chain.doFilter(wrappedRequest, resp);
	}

	@Override
	public void init(FilterConfig config) throws ServletException {

	}

	private static class ResettableStreamRequestWrapper extends HttpServletRequestWrapper {

		private static final String DEFAULT_CHARACTER_ENCODING = "UTF-8";

		private byte[] bodyData;
		private HttpServletRequest request;
		private ResettableServletInputStream resettableServletInputStream;

		public ResettableStreamRequestWrapper(HttpServletRequest request) {
			super(request);
			this.request = request;
			this.resettableServletInputStream = new ResettableServletInputStream();
		}

		public void resetInputStream() {
			if (this.bodyData != null) {
				this.resettableServletInputStream.is = new ByteArrayInputStream(this.bodyData);
			}
		}

		@Override
		public ServletInputStream getInputStream() throws IOException {
			if (this.bodyData == null) {
				resolveBodyData();
			}
			return this.resettableServletInputStream;
		}

		@Override
		public BufferedReader getReader() throws IOException {
			if (this.bodyData == null) {
				resolveBodyData();
			}
			return new BufferedReader(new InputStreamReader(this.resettableServletInputStream));
		}

		private void resolveBodyData() throws IOException {
			this.bodyData = IOUtils.toByteArray(this.request.getReader(), getCharacterEncoding());
			this.resettableServletInputStream.is = new ByteArrayInputStream(this.bodyData);
		}

		public String getCharacterEncoding() {
			return super.getCharacterEncoding() != null ? super.getCharacterEncoding() : DEFAULT_CHARACTER_ENCODING;
		}

		private class ResettableServletInputStream extends ServletInputStream {

			private InputStream is;

			@Override
			public int read() throws IOException {
				return this.is.read();
			}

			@Override
			public boolean isFinished() {
				return false;
			}

			@Override
			public boolean isReady() {
				return false;
			}

			@Override
			public void setReadListener(ReadListener arg0) {

			}
		}
	}
}
