package com.example.test.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

@WebFilter(urlPatterns = { "/*" })
public class AuthenticationFilter implements Filter {

	private static Logger logger = LoggerFactory.getLogger(AuthenticationFilter.class);

	@Override
	public void destroy() {
		// TODO Auto-generated method stub

	}

	@Override
	public void doFilter(ServletRequest arg0, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {

		logger.info("checking client id in filter");
		HttpServletRequest request = (HttpServletRequest) arg0;
		String clientId = request.getHeader("Authorization");
		logger.info("client id is :"+ clientId);
		if (!StringUtils.isEmpty(clientId)) {
			chain.doFilter(request, response);
		} else {
			logger.error("client id missing.");
		}
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub

	}

}
