package edu.poly.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.InvocationTargetException;
import java.util.Properties;
import java.util.Random;

import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.beanutils.BeanUtils;
import org.json.JSONObject;

import edu.poly.DAO.UserDAO;
import edu.poly.entity.BeanLogin;
import edu.poly.entity.User;
import edu.poly.helpdb.CookieUtils;

/**
 * Servlet implementation class UserServlet
 */
@WebServlet({ "/User", "/login", "/register", "/sendMail", "/changePassword", "/changeInfo", "/logout" })
public class UserServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String uri = request.getRequestURI();

		if (uri.contains("login")) {
//			String id = CookieUtils.get("userID", request);
//
//			if(id == null) {
//				request.getRequestDispatcher("/views/login.jsp").forward(request, response);
//				return;
//			}else {
//				response.sendRedirect("Home");
//			}
			request.getRequestDispatcher("/views/login.jsp").forward(request, response);
		} else if (uri.contains("register")) {
			request.getRequestDispatcher("/views/register.jsp").forward(request, response);
		} else if (uri.contains("changePassword")) {
			request.getRequestDispatcher("/views/changePassword.jsp").forward(request, response);
		} else if (uri.contains("logout")) {
			logout(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String uri = request.getRequestURI();

		if (uri.contains("register")) {
			try {
				getregister(request, response);
			} catch (IllegalAccessException | InvocationTargetException | ServletException | IOException e) {
				e.printStackTrace();
			}
		} else if (uri.contains("login")) {
			try {
				login(request, response);
			} catch (IllegalAccessException | InvocationTargetException | ServletException | IOException e) {
				e.printStackTrace();
			}
		} else if (uri.contains("changePassword")) {
			System.out.println("iabsuci");
			changePassword(request, response);
		} else if (uri.contains("sendMail")) {
			try {
				creatCheck(request, response);
			} catch (IllegalAccessException | InvocationTargetException | ServletException | IOException e) {
				e.printStackTrace();
			}
		} else if (uri.contains("changeInfo")) {
			changeInfo(request, response);
			request.getRequestDispatcher("/views/editFrofile.jsp").forward(request, response);
		}
	}

	protected void creatCheck(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, IllegalAccessException, InvocationTargetException {
		HttpSession httpSession = request.getSession();
		User user = new User();
		Random random = new Random();

		int randomNumber = random.nextInt(10000);
		String formattedNumber = String.format("%04d", randomNumber);
		httpSession.setAttribute("numberRand", formattedNumber);

		// Lấy dữ liệu từ request
		String toEmail = request.getParameter("email");
		String subject = "MÃ XÁC NHẬN";
		String messageText = String.valueOf(formattedNumber);

		// Cấu hình thông tin kết nối đến máy chủ SMTP
		Properties properties = new Properties();
		properties.put("mail.smtp.auth", "true");
		properties.put("mail.smtp.starttls.enable", "true");
		properties.put("mail.smtp.host", "smtp.gmail.com");
		properties.put("mail.smtp.port", "587");

		// Khởi tạo đối tượng Session để kết nối đến máy chủ SMTP
		Session session = Session.getInstance(properties, new javax.mail.Authenticator() {
			protected javax.mail.PasswordAuthentication getPasswordAuthentication() {
				return new javax.mail.PasswordAuthentication("vunvpd06347@fpt.edu.vn", "lugdteohawpkdtmm");
			}
		});

		// Tạo đối tượng MimeMessage để chứa nội dung email
		MimeMessage message = new MimeMessage(session);
		try {
			message.setFrom(new InternetAddress("vunvpd06347@fpt.edu.vn"));
			message.addRecipient(Message.RecipientType.TO, new InternetAddress(toEmail));
			message.setSubject(subject);
			message.setText("Mã xác nhận của bạn là: " + messageText);

			// Gửi email
			Transport.send(message);
			request.setAttribute("message", "Vui lòng check  mail");
			BeanUtils.populate(user, request.getParameterMap());
			request.setAttribute("user", user);
			request.setAttribute("rePasss", request.getParameter("rePassword"));

			request.getRequestDispatcher("/views/register.jsp").forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	protected void getregister(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, IllegalAccessException, InvocationTargetException {
		User user = new User();
		UserDAO userDao = new UserDAO();
		HttpSession session = request.getSession();

		BeanUtils.populate(user, request.getParameterMap());

		request.setAttribute("user", user);
		request.setAttribute("rePasss", request.getParameter("rePassword"));

		String checkMail = request.getParameter("check");
		String numberCheck = (String) session.getAttribute("numberRand");

		if (checkMail.equals("")) {
			request.setAttribute("error", "Vui lòng nhập mã xác nhận");
			request.getRequestDispatcher("/views/register.jsp").forward(request, response);
		}
		if (Integer.parseInt(checkMail) == Integer.parseInt(numberCheck)) {
			userDao.insert(user);
			request.getRequestDispatcher("/views/login.jsp").forward(request, response);
		} else {
			request.setAttribute("error", "Mã xác nhận không đúng!!");
		}

	}

	protected void login(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, IllegalAccessException, InvocationTargetException {
		BeanLogin userBean = new BeanLogin();

		BeanUtils.populate(userBean, request.getParameterMap());

		UserDAO userDao = new UserDAO();

		User user = userDao.findUserByID(userBean.getUserID());

		request.setAttribute("idUser", userBean.getUserID());
		request.setAttribute("pass", userBean.getPassword());
		if (user != null && user.getPassword().equals(userBean.getPassword())) {
			HttpSession session = request.getSession();

			session.setAttribute("user", user);
			request.setAttribute("userID", user.getUserID());

			if (userBean.isRemember()) {
				CookieUtils.set("userID", userBean.getUserID(), 1, response);
				CookieUtils.set("password", userBean.getPassword(), 1, response);
			} else {
				CookieUtils.set("userID", userBean.getUserID(), 0, response);
			}
			if (user.getRole() == false) {
				response.sendRedirect("Admin");
				return;
			} else {
				response.sendRedirect("Home");
			}
		} else if (user == null) {
			request.setAttribute("errorID", "Tài khoản không chính xác!!");

			request.getRequestDispatcher("/views/login.jsp").forward(request, response);
		} else if (userBean.getPassword() != user.getPassword()) {
			request.setAttribute("errorPass", "Mật khẩu không đúng!!");

			request.getRequestDispatcher("/views/login.jsp").forward(request, response);
		}
	}

	protected void changePassword(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setCharacterEncoding("utf-8");


		String id = request.getParameter("username");
		String password = request.getParameter("password");
		String newPass = request.getParameter("newPass");
		String confirmPassword = request.getParameter("confirmPassword");

		
		HttpSession session = request.getSession();
		JSONObject jsonObj = new JSONObject();

		User user = (User) session.getAttribute("user");

		request.setAttribute("usID", id);
		request.setAttribute("pass", password);
		request.setAttribute("confirmPass", confirmPassword);
		request.setAttribute("newPass", newPass);

		PrintWriter out = response.getWriter();
		if (!(id.equals(user.getUserID()))) {
			jsonObj.put("errorID", "Tài khoản không đúng!!");
			out.print(jsonObj.toString());
			out.flush();
			return;
		} else if (!password.equals(user.getPassword())) {
			jsonObj.put("errorPass", "Mật khẩu không đúng!!");
			out.print(jsonObj.toString());
			out.flush();
			return;
		} else if (!confirmPassword.equals(newPass)) {
			jsonObj.put("errorConfirm", "Mật khẩu không hợp lệ!!");
			out.print(jsonObj.toString());
			out.flush();
			return;
		}

		UserDAO userDao = new UserDAO();

		user.setPassword(newPass);

		
		if (userDao.updatePass(user)) {
			jsonObj.put("message", "Cập nhật thành công.");
		} else {
			out.print("Cập nhật thất bại!!!");
		}
		
		out.print(jsonObj.toString());
		out.flush();
	}

	protected void changeInfo(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");

		String fullname = request.getParameter("fullname");
		String email = request.getParameter("email");

		UserDAO userDao = new UserDAO();
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");

		user.setFullname(fullname);
		user.setEmail(email);

		userDao.update(user);

		request.setAttribute("message", "Cập nhật thành công");
	}

	protected void logout(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();

		session.removeAttribute("user");

		response.sendRedirect("Home");
	}
}
