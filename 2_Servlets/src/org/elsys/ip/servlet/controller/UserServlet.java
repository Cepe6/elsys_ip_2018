package org.elsys.ip.servlet.controller;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.elsys.ip.servlet.model.User;
import org.elsys.ip.servlet.service.UserService;
import org.jasypt.util.password.StrongPasswordEncryptor;

/**
 * Servlet implementation class UserServlet
 */
public class UserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private final UserService userService = new UserService();

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public UserServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String path = new URL(request.getRequestURL().toString()).getPath();

		//For getting add user page
		if(path.startsWith("/user/add")) {
			getServletContext().getRequestDispatcher("/WEB-INF/user-add.jsp")
					.forward(request, response);
		}

		//For getting change user page
		else if (path.startsWith("/user/change")) {
			int id = Integer.parseInt(request.getParameter("id"));
			request.setAttribute("user", userService.getById(id));
			getServletContext().getRequestDispatcher("/WEB-INF/user-change.jsp")
					.forward(request, response);
		}

		//Delete user
		//QUESTION HERE: How can I trigger onDelete() when typing localhost:8080/user/delete in browser
		else if (path.startsWith("/user/delete")) {
			doDelete(request, response);
		}


		//Search users
		else if(path.startsWith("/user/search")) {
			String searchAttribute = request.getParameter("search-attribute");
			String searchValue = request.getParameter("search-value");

			List<User> searchResult = userService.getUsers().stream().filter(user -> (searchAttribute.equals("name") ? user.getName() : user.getEmail()).equals(searchValue)).collect(Collectors.toList());

			request.setAttribute("users", searchResult);
			getServletContext().getRequestDispatcher("/WEB-INF/admin.jsp")
					.forward(request, response);
		}

		//Get user
		else {
			int id = Integer.parseInt(request.getParameter("id"));
			User user = userService.getById(id);
			request.setAttribute("user", user);

			getServletContext().getRequestDispatcher("/WEB-INF/user.jsp")
					.forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub

		//For editing user
		//QUESTION HERE:How can I trigger PUT request from jsp <form>
		if(request.getParameter("_method") != null) {
			if (request.getParameter("_method").equals("PUT"))
				doPut(request, response);
		}

		//For adding user
		else {
			String name = request.getParameter("name");
			String email = request.getParameter("email");
			String password = new StrongPasswordEncryptor().encryptPassword(request.getParameter("password"));

			userService.addUser(new User(userService.getLastIndex() + 1, name, email, password));
			response.sendRedirect("/admin");
		}

	}

	@Override
	protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int id = Integer.parseInt(req.getParameter("id"));
		User user = userService.getById(id);

		String name = req.getParameter("changed_name");
		String email = req.getParameter("changed_email");
		String password = req.getParameter("changed_password");

		if (!name.equals("")) {
			user.setName(name);
		}

		if (!email.equals("")) {
			user.setEmail(email);
		}

		if (!password.equals("")) {
			user.setPassword(new StrongPasswordEncryptor().encryptPassword(password));
		}


		resp.sendRedirect("/user/?id=" + user.getId());
	}

	@Override
	protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int id = Integer.parseInt(req.getParameter("id"));
		User user = userService.getById(id);
		userService.removeUser(user);
		resp.sendRedirect("/admin");
	}
}
