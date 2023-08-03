package edu.poly.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.List;
import java.util.Properties;

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

import edu.poly.DAO.FavouriteDAO;
import edu.poly.DAO.ShareDao;
import edu.poly.DAO.VideoDAO;
import edu.poly.entity.Favorite;
import edu.poly.entity.Share;
import edu.poly.entity.User;
import edu.poly.entity.Video;

@WebServlet({ "/VideoDetail", "/likeVideo", "/shareVideo"})
public class VideoDetailServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		updateView(request, response);
		fillVideoByID(request, response);
		suggestedVideos(request, response);

		request.getRequestDispatcher("/views/video-detail.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		String uri = request.getRequestURI();

		if (uri.contains("likeVideo")) {
			likeVideo(request, response);
		}else if(uri.contains("shareVideo")) {
			shareVideo(request, response);
		}
	}

	protected void fillVideoByID(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		VideoDAO vdDao = new VideoDAO();
		FavouriteDAO favouriteDao = new FavouriteDAO();
		Favorite favourite = new Favorite();
		User user = (User) session.getAttribute("user");

		int id = Integer.parseInt(request.getParameter("id"));
		session.setAttribute("id", id);

		//tìm video theo id
		Video video = vdDao.findtVideoByID(id);
		
		//tìm xem video này có đang được like hay không?
		if (user != null) {
			Favorite vdFavorite = favouriteDao.findFavourite(user.getUserID(), id);
			request.setAttribute("vdFavourite", vdFavorite);
		}
		String requestUrl = request.getRequestURL().toString(); //Lấy đường dẫn tuyệt đối : http://localhost:8080/AssignmentJava4/VideoDetail
        String queryString = request.getQueryString(); //Lấy id: id=13
        String absoluteUrl = requestUrl + "?" + queryString; //Gộp lại để được địa chỉ mình muốn: http://localhost:8080/AssignmentJava4/VideoDetail?id=13
        session.setAttribute("linkURL", absoluteUrl);
        
		request.setAttribute("video", video);

	}

	protected void updateView(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		VideoDAO vdDao = new VideoDAO();
		HttpSession session = request.getSession();

		int id = Integer.parseInt(request.getParameter("id"));
		
		Video video = vdDao.findtVideoByID(id);
		int view = video.getViews();

		User user = (User) session.getAttribute("user");

		if (user != null) {
			view++;
			video.setViews(view);
			vdDao.updateView(video);
		}
	}

	protected void suggestedVideos(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		VideoDAO vdDao = new VideoDAO();

		List<Video> suggestedVideo = vdDao.videoRandom();

		request.setAttribute("suggestedVideo", suggestedVideo);

	}

	protected void likeVideo(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		HttpSession session = request.getSession();
		response.setCharacterEncoding("utf-8");
		response.setContentType("application/json");
		HttpSession session1 = request.getSession();

		boolean like =Boolean.parseBoolean( request.getParameter("like"));

		User user = (User) session.getAttribute("user");
		int idVideo = (int) session1.getAttribute("id");

		if (user == null) {
			out.print("{\"message\":\"Vui lòng đăng nhập!\"}");
			out.flush();
		} else {
			if (like) {
				FavouriteDAO favouriteDao = new FavouriteDAO();
				Favorite favourite = new Favorite();
				VideoDAO vdDao = new VideoDAO();

				

				Video video = vdDao.findtVideoByID(idVideo);
				
				favourite.setUser(user);
				favourite.setVideo(video);
				favourite.setLikeDate(new Date());
				
				favouriteDao.insertLike(favourite);
				
				like = false;
			} else {
				FavouriteDAO favouriteDao = new FavouriteDAO();
				Favorite favourite = new Favorite();
				VideoDAO vdDao = new VideoDAO();
				
				Favorite vdFavorite = favouriteDao.findFavourite(user.getUserID(), idVideo);

				favouriteDao.delete(vdFavorite);
				
				like= true;
			}
		}
	}
	
	protected void shareVideo(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		PrintWriter out = response.getWriter();
        HttpSession session1 = request.getSession();
        
     // Lấy dữ liệu từ request
        String toEmail = "vanvu19102003@gmail.com";
        String subject = "CHIA SẺ VIDEO CÙNG BẠN";
        String content = (String) session1.getAttribute("linkURL");
        
        // Cấu hình thông tin kết nối đến máy chủ SMTP
        Properties properties = new Properties();
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.port", "587");

        // Khởi tạo đối tượng Session để kết nối đến máy chủ SMTP
        Session session = Session.getInstance(properties, new javax.mail.Authenticator() {
          protected javax.mail.PasswordAuthentication getPasswordAuthentication() {
            return new javax.mail.PasswordAuthentication("vunvpd06347@fpt.edu.vn", "oxijjuftrwqcktpc");
          }
        });

        // Tạo đối tượng MimeMessage để chứa nội dung email
        MimeMessage message = new MimeMessage(session);
        try {
        	message.setFrom(new InternetAddress("vunvpd06347@fpt.edu.vn"));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(toEmail));
            message.setSubject(subject);
            message.setText("Link video: " +content);

            // Gửi email
            Transport.send(message);
            
            //Lưu thông tin share vào db
            ShareDao shareDao = new ShareDao();
            VideoDAO vdDao = new VideoDAO();
            Share share = new Share();
            
            User user = (User) session1.getAttribute("user");
            int id = (int) session1.getAttribute("id");
            Video video = vdDao.findtVideoByID(id);
            
            share.setUser(user);
            share.setVideo(video);
            share.setEmails(toEmail);
            share.setShareDate(new Date());
            
            shareDao.insertShare(share);
            
            out.print("Đã chia sẻ thành công");
            out.flush();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
