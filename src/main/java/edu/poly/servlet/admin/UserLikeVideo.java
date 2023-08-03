package edu.poly.servlet.admin;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.poly.DAO.FavouriteDAO;
import edu.poly.DAO.VideoDAO;
import edu.poly.entity.User;
import edu.poly.entity.Video;

@WebServlet("/Admin/UserLikeVideo")
public class UserLikeVideo extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		likeVideo(request, response);
		request.getRequestDispatcher("/views/admin/userLikeVideo.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	
	protected void likeVideo(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String idVideo = request.getParameter("id");
		
		FavouriteDAO favouriteDao = new FavouriteDAO();
		
		List<Object[]> listUserLike = favouriteDao.selectUserLikeVideo(Integer.parseInt(idVideo));
		
		request.setAttribute("listUserLike", listUserLike);
	}

}
