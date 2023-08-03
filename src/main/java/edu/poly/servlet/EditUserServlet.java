package edu.poly.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import edu.poly.DAO.FavouriteDAO;
import edu.poly.DAO.UserDAO;
import edu.poly.entity.User;

@WebServlet({"/Admin/EditUser","/Admin/EditUser/edit","/Admin/EditUser/update","/Admin/EditUser/delete"})
public class EditUserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String uri = request.getRequestURL().toString();
		
		if(uri.contains("edit")) {
			edit(request, response);
		}
		
		fillAllUser(request, response);
		
		countUser(request, response);
		request.getRequestDispatcher("/views/admin/user.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String uri = request.getRequestURI().toString();
		
		if(uri.contains("edit")) {
			edit(request, response);
		}else if(uri.contains("update")) {
			updateUser(request, response);
		}else if(uri.contains("delete")) {
			delete(request, response);
		}
		
		doGet(request, response);
	}

	protected void fillAllUser(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		UserDAO userDao = new UserDAO();
		
		List<User> list = userDao.fillAll();
		
		request.setAttribute("users", list);
	}
	
	protected void edit(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		
		String userID = request.getParameter("id");
		session.setAttribute("idUser", userID);
		
		UserDAO userDao = new UserDAO();
		
		User user = userDao.findUserByID(userID);
		
		request.setAttribute("userEdit", user);
	}
	
	protected void updateUser(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String userID = request.getParameter("userID");
		String fullname = request.getParameter("fullname");
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		
		User user = new User();
		UserDAO userDao = new UserDAO();
		
		user.setUserID(userID);
		user.setFullname(fullname);
		user.setEmail(email);
		user.setPassword(password);
		
		userDao.updatePass(user);
		
	}
	
	protected void delete(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		UserDAO usDao = new UserDAO();
		HttpSession session = request.getSession();
		
		String userID = (String) session.getAttribute("idUser");
		User user = usDao.findUserByID(userID);
		
		if (user != null) {
			usDao.delete(user);
		}
	}
	protected void countUser(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		UserDAO userDao = new UserDAO();
		
		Long countUser = userDao.countUser();
		
		request.setAttribute("countUser", countUser);
	}
	
	
}
