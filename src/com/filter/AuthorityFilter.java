package com.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class AuthorityFilter implements Filter {

	private FilterConfig config;

	@Override
	public void destroy() {
		this.config = null;
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		String loginPage = config.getInitParameter("loginPage");
		String login = config.getInitParameter("login");
		HttpServletRequest req = (HttpServletRequest) request;
		HttpSession session = req.getSession(true);

		String requestPath = req.getServletPath();
		if (session.getAttribute("currentUser") == null
				&& !requestPath.endsWith(loginPage)
				&& !requestPath.endsWith(login)) {
			HttpServletResponse resp = (HttpServletResponse) response;
			resp.sendRedirect("../../login.jsp");
		} else {
			chain.doFilter(request, response);
		}

	}

	@Override
	public void init(FilterConfig config) throws ServletException {
		this.config = config;
	}

}
