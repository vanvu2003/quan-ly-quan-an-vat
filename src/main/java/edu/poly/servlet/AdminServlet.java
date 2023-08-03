package edu.poly.servlet;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import edu.poly.DAO.FavouriteDAO;
import edu.poly.DAO.UserDAO;
import edu.poly.DAO.VideoDAO;
import edu.poly.entity.User;
import edu.poly.entity.Video;

@MultipartConfig
@WebServlet({ "/Admin", "/Admin/insert", "/Admin/edit","/Admin/update","/Admin/delete","/Admin/like"})
public class AdminServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String uri = request.getRequestURL().toString();
		
		if(uri.contains("edit")) {
			edit(request, response);
		}
		fillAllVideo(request, response);
		countVideo(request, response);
		request.getRequestDispatcher("/views/admin/index.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String uri = request.getRequestURL().toString();
		request.setCharacterEncoding("utf-8");

		if (uri.contains("insert")) {
			insertVideo(request, response);
		} else if (uri.contains("edit")) {
			edit(request, response);
		}else if (uri.contains("update")) {
			updateVideo(request, response);
		}else if (uri.contains("delete")) {
			deleteVideo(request, response);
		}
		
		doGet(request, response);
	}

	protected void insertVideo(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Video video = new Video();
		VideoDAO videoDao = new VideoDAO();
		
		String description = request.getParameter("description");
		String tiltle = request.getParameter("tiltle");

		String uploadFolder = request.getServletContext().getRealPath("/uploads");
		Path uploadPath = Path.of(uploadFolder);

		if (!Files.exists(uploadPath)) {
			Files.createDirectory(uploadPath);
		}
		Part linkFart = request.getPart("link");
		Part fosterFart = request.getPart("foster");

		String linkFilename = Path.of(linkFart.getSubmittedFileName()).toString();
		String fosterFilename = Path.of(fosterFart.getSubmittedFileName()).toString();

		linkFart.write(uploadFolder + "/" + linkFilename);
		fosterFart.write(uploadFolder + "/" + fosterFilename);

		video.setTiltle(tiltle);
		video.setLink(linkFilename);
		video.setPoster(fosterFilename);
		video.setDescription(description);

		videoDao.insert(video);
	}

	protected void updateVideo(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		VideoDAO vdDao = new VideoDAO();
		String link = request.getParameter("link");
		String poster = request.getParameter("foster");
		
		String idVideo = request.getParameter("idVideo");
		System.out.println(idVideo);
		Video video = vdDao.findtVideoByID(Integer.parseInt(idVideo));
		
		video.setTiltle(request.getParameter("tiltle"));
		video.setDescription(request.getParameter("description"));
		if (!(link == null)) {
			Part linkFart = request.getPart("link");
			String linkFilename = Path.of(linkFart.getSubmittedFileName()).toString();
			video.setLink(linkFilename);
		}
		if (!(poster == null)) {
			Part fosterFart = request.getPart("foster");
			String fosterFilename = Path.of(fosterFart.getSubmittedFileName()).toString();
			video.setPoster(fosterFilename);
		}
		
		vdDao.update(video);
	}
	
	protected void deleteVideo(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("idVideo"));
		
		VideoDAO vdDao = new VideoDAO();
		
		Video video = vdDao.findtVideoByID(id);
		if (video != null) {
			vdDao.delete(video);
		}
	}
	protected void edit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			int idVideo = Integer.parseInt(request.getParameter("id"));
			VideoDAO vdDao = new VideoDAO();
			Video video = vdDao.findtVideoByID(idVideo);
			
			request.setAttribute("video", video);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	protected void fillAllVideo(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		VideoDAO vdDao = new VideoDAO();

		List<Video> listVideo = vdDao.fillAll();

		request.setAttribute("listVideo", listVideo);
	}

	protected void countVideo(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		VideoDAO vdDao = new VideoDAO();
		
		long countVideo =  vdDao.countVideo();
		

		request.setAttribute("countVideo", countVideo);
	}
	
}
