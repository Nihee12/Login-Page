package com.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.DAO.UserDAO;
import com.DB.DBConnect;
import com.entities.User;
@WebServlet("/login")
public class Login extends HttpServlet{
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String email = req.getParameter("email");
		String password = req.getParameter("password");
		
		UserDAO dao = new UserDAO(DBConnect.getConnection());
		User user = dao.getLogin(email, password);
		
		if (user != null) {
			HttpSession session = req.getSession();
			session.setAttribute("user-ob", user);
			resp.sendRedirect("home.jsp");
			
		}else {
			HttpSession session = req.getSession();
			session.setAttribute("error-msg", "Invalid email and Password");
			resp.sendRedirect("home.jsp");
		}	
	}
	
}
