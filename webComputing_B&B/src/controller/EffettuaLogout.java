package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/EffettuaLogout")
public class EffettuaLogout extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		//System.out.println("Servlet logout");
		
		HttpSession session = req.getSession();
		session.setAttribute("outGoogle", true);
		
		//System.out.println("EffettuaLOGOUT -> outGoogle -> " + session.getAttribute("outGoogle"));
		
		Cookie cookieName = new Cookie("email", "");
		Cookie cookiePass = new Cookie("password", "");
		cookieName.setMaxAge(0);
		cookiePass.setMaxAge(0);
		resp.addCookie(cookieName);
		resp.addCookie(cookiePass);
		session.setAttribute("loggato", false);
		session.invalidate();
		
		RequestDispatcher dispatcher = req.getRequestDispatcher("Home.jsp");
		dispatcher.forward(req, resp);
	}
}
