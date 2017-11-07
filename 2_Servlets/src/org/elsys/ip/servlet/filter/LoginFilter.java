package org.elsys.ip.servlet.filter;

import org.elsys.ip.servlet.model.User;
import org.elsys.ip.servlet.service.UserService;
import org.jasypt.util.password.StrongPasswordEncryptor;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet Filter implementation class LoginFilter
 */
public class LoginFilter implements Filter {

	private final UserService userService = new UserService();

	/**
	 * Default constructor.
	 */
	public LoginFilter() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {


		HttpServletRequest httpReq = (HttpServletRequest) request;
		boolean authorized = false;
		boolean cookieFound = false;
		String username;
		String password;

		username = request.getParameter("name");
		password = request.getParameter("password");


		//Check for cookie from request
		Cookie[] cookies = httpReq.getCookies();
		if (cookies != null)
			for (Cookie cookie : cookies) {
				if (cookie.getName().equals("user")) {
					authorized = true;
					cookieFound = true;
				}
			}

		//If cookie not found check for user and password
		if (!cookieFound) {
			StrongPasswordEncryptor passwordEncryptor = new StrongPasswordEncryptor();
			for (User user : userService.getUsers()) {
				if (passwordEncryptor.checkPassword(password, user.getPassword())) {
						authorized = true;
				}
			}
		}

		if (authorized) {
			//If cookie not found create new and add to response
			if (!cookieFound) {
				Cookie userCookie = new Cookie("user", username);
				HttpServletResponse httpResp = (HttpServletResponse) response;
				httpResp.addCookie(userCookie);
			}

			chain.doFilter(request, response);
		} else {
			request.setAttribute("error", "Wrong username or password!");
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/login.jsp");
			dispatcher.forward(request, response);
		}
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
	}

}
