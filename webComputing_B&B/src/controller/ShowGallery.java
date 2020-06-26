package controller;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.LinkedList;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.Fotografia;
import persistence.DAOFactory;
import persistence.dao.FotografiaDao;

/**
 * Servlet implementation class Gallery
 */
@WebServlet("/ShowGallery")
public class ShowGallery extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ShowGallery() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		session.setAttribute("id", -1);
		
		FotografiaDao fotoDao;
		LinkedList<Fotografia> photos = (LinkedList<Fotografia>) session.getAttribute("photos");;
		session.setAttribute("nFoto", 3);
		if(session.getAttribute("photos") == null) {
			fotoDao = DAOFactory.getDAOFactory(DAOFactory.POSTGRESQL).getFotografiaDAO();
			photos = (LinkedList<Fotografia>) fotoDao.findAll();
			
			session.setAttribute("photos", photos);
			session.setAttribute("nFoto", photos.size());
		}else {
			session.setAttribute("photos", photos);
			session.setAttribute("nFoto", photos.size());
		}
		
		
		String txt = "<ul class='carousel-indicators'>";
		
		for (int i = 0; i < photos.size(); i++) {
			if(i == 0) {
				txt +=  "<li data-target='#demo' data-slide-to='"+i+"' class='active'></li>";
			}
			else {
				txt +=  "<li data-target='#demo' data-slide-to='"+i+"'></li>";
			}
		}
		
		txt += "</ul>";
		txt += "<div class='carousel-inner'>";
		for (Fotografia fotografia : photos) {
			
			File file = new File(fotografia.getPath());
			String path = file.getName();
			
			if(fotografia.getIdFoto() == 1) {
				txt += "<div id='" + fotografia.getIdFoto() + "' class='carousel-item active' align='center'>";
			}
			else {
				txt += "<div id='" + fotografia.getIdFoto() + "' class='carousel-item' align='center'>";
			}
			txt += "<img src='ImageToShow' alt='" + fotografia.getTitle() + "' style='width: 30%'>";
			txt += "<div class='carousel-caption'>";
			txt += "<h3>" + fotografia.getTitle() + "</h3>";
			txt += "<p>" + fotografia.getDescription() + "</p>";
			txt += "</div></div>";
			
		}
		txt += "</div>";
		
		response.getWriter().print(txt);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
