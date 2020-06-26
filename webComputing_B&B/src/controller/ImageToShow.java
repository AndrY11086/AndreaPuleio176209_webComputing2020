package controller;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

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
 * Servlet implementation class ImageToShow
 */
@WebServlet("/ImageToShow")
public class ImageToShow extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ImageToShow() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		response.setContentType("image/jpg");
		ServletOutputStream out = response.getOutputStream();
		
		HttpSession session = request.getSession();
		int id = 0;

		if (request.getParameter("switch") != null) {
			if (request.getParameter("switch").equals("next")) {
				id = nextPhoto(session);
			} else {
				id = prevPhoto(session);
			}
		}else {
			id = nextPhoto(session);
		}

		List<Fotografia> photo = (List<Fotografia>) session.getAttribute("photos");
		

		File file = new File(photo.get(id).getPath());
		FileInputStream fin = new FileInputStream(file);

		BufferedInputStream bin = new BufferedInputStream(fin);
		BufferedOutputStream bout = new BufferedOutputStream(out);

		int ch = 0;
		;
		while ((ch = bin.read()) != -1) {
			bout.write(ch);
		}
		bin.close();
		fin.close();
		bout.close();
		out.close();
	}

	public int nextPhoto(HttpSession session) {
		if ((int) session.getAttribute("id") + 1 < (int)session.getAttribute("nFoto"))
			session.setAttribute("id", (int) session.getAttribute("id") + 1);
		else {
			session.setAttribute("id", 1);
		}

		return (int) session.getAttribute("id");
	}

	public int prevPhoto(HttpSession session) {

		if ((int) session.getAttribute("id") - 1 > 0)
			session.setAttribute("id", (int) session.getAttribute("id") - 1);
		else {
			session.setAttribute("id", (int)session.getAttribute("nFoto")-1);
		}

		return (int) session.getAttribute("id");
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
