package servlets;

import dataaccess.UserDB;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import models.Role;
import models.User;
import services.RoleService;
import services.UserService;

public class UserServlet extends HttpServlet {
	UserService userService = null;
	RoleService roleService = null;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException {
		userService = new UserService();
		roleService = new RoleService();
		List<User> users = null;
		List<Role> roles = null;

		try {
			users = userService.getAll();
			roles = roleService.getAll();
		} catch (SQLException ex) {
			Logger.getLogger(UserServlet.class.getName()).log(Level.SEVERE, null, ex);
		}

		request.setAttribute("users", users);
		request.setAttribute("roles", roles);

		getServletContext().getRequestDispatcher("/WEB-INF/users.jsp").forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException {

		String action = request.getParameter("action");

		if (action == null) {
			this.doGet(request, response);
			return;
		}

		switch(action) {
			case "add":
				System.out.println("Add request");
				String email = request.getParameter("email");
				boolean active = true;
				String firstName = request.getParameter("firstName");
				System.out.println(firstName);
				String lastName = request.getParameter("lastName");
				String password = request.getParameter("password");
				String roleName = request.getParameter("roleName");
				Role role = null;
				try {
					role = roleService.get(roleName);
					User user = new User(email, active, firstName, lastName, password, role);
					userService.insert(user);
				} catch (SQLException ex) {
					Logger.getLogger(UserServlet.class.getName()).log(Level.SEVERE, null, ex);
				}
				break;
			case "delete":
				email = request.getParameter("email");
				try {
					User user = userService.get(email);
					userService.delete(user);
				} catch (SQLException ex) {
					Logger.getLogger(UserServlet.class.getName()).log(Level.SEVERE, null, ex);
				}
				break;

			case "edit":
				email = request.getParameter("email");
				active = Boolean.parseBoolean(request.getParameter("active"));
				firstName = request.getParameter("firstName");
				lastName = request.getParameter("lastName");
				password = request.getParameter("password");
				roleName = request.getParameter("roleName");
				try {
					role = roleService.get(roleName);
					User user = new User(email, active, firstName, lastName, password, role);
					userService.update(user);
				} catch (SQLException ex) {
					Logger.getLogger(UserServlet.class.getName()).log(Level.SEVERE, null, ex);
				}
				break;
		}

		this.doGet(request, response);
	}

}
