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
import edu.poly.DAO.VideoDAO;
import edu.poly.entity.User;
import edu.poly.entity.Video;


@WebServlet("/Favourite")
public class FavouriteServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		fillVideoFavourite(request, response);
		request.getRequestDispatcher("/views/videos-favourite.jsp").forward(request, response);
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}

	protected void fillVideoFavourite(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		FavouriteDAO favouriteDao = new FavouriteDAO();
		VideoDAO vdDao = new VideoDAO();
		HttpSession session = request.getSession();
		
		User user = (User) session.getAttribute("user");
		
		List<Video> video = favouriteDao.findFavouriteByIdUser(user.getUserID());
		
		request.setAttribute("videos", video);
	}

}
