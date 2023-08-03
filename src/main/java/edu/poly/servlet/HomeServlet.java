package edu.poly.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import edu.poly.DAO.VideoDAO;
import edu.poly.entity.User;
import edu.poly.entity.Video;
import edu.poly.helpdb.CookieUtils;

@WebServlet({ "/Home" })
public class HomeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");

		fillAllVideo(request, response);
		if (user == null || user.getRole()) {
			request.getRequestDispatcher("/views/index.jsp").forward(request, response);
		} else {
			response.sendRedirect("Admin");
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

	protected void fillAllVideo(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		VideoDAO vdDao = new VideoDAO();

		String index = request.getParameter("index");
		if (index == null) {
			index = "1";
		}
		int indexPage = Integer.parseInt(index);

		int countVideo = (int) vdDao.countVideo();

		int pageCount = countVideo / 8;
		if (countVideo % 8 != 0) {
			pageCount++;
		}

		List<Video> listVideo = vdDao.pagingVideo(indexPage);

		request.setAttribute("pagecount", pageCount);
		request.setAttribute("index", indexPage);
		request.setAttribute("listVideo", listVideo);
	}
}
