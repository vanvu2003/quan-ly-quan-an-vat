package edu.poly.servlet.admin;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.poly.DAO.FavouriteDAO;

@WebServlet("/FavouriteAdmin")
public class FavouriteAdminServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		fillFavourite(request, response);
		request.getRequestDispatcher("views/admin/favourite.jsp").forward(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}
	
	protected void fillFavourite(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		FavouriteDAO favourDao = new FavouriteDAO();
		
		List<Object[]> listFavourite = favourDao.selectCountVideo();
		
		request.setAttribute("countFavourite", listFavourite);
	}

}
