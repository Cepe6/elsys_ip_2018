package org.elsys.ip.servlet.controller;

import org.elsys.ip.servlet.model.User;
import org.elsys.ip.servlet.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class LoginServlet extends HttpServlet {
    private final UserService userService = new UserService();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String userName = request.getParameter("name");
        if(userName.equals("admin")) {
            response.sendRedirect("/admin");
        } else {
            User user = userService.getByName(userName);
            response.sendRedirect("/user?id=" + user.getId());
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }
}
