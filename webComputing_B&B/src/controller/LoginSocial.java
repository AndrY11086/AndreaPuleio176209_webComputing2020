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

import model.Cliente;
import persistence.DAOFactory;
import persistence.DatabaseManager;
import persistence.dao.ClienteDao;

/**
 * Servlet implementation class LoginSocial
 */
@WebServlet("/LoginSocial")
public class LoginSocial extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();

		// log-out
		session.removeAttribute("email");
		session.removeAttribute("nome");
		session.removeAttribute("cognome");
		session.removeAttribute("cliente");
		session.removeAttribute("loggato");
		session.removeAttribute("password");
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		/*
		 * ClientID
		 * 810905248828-gfrqvbsqntsk2cjlqk7j1n3ouvciibdu.apps.googleusercontent.com
		 * 
		 * ClientSecret Om72JpfzoIAvsOw2vBmkDn8C
		 * 
		 */
		System.out.println("LoginSocial è stata chiamata");

		HttpSession session = request.getSession();

		String email = request.getParameter("email");
		String nome = request.getParameter("nome");
		String cognome = request.getParameter("cognome");
		String password = "123";
		String idToken = request.getParameter("idToken");
		
		if(idToken != null)
			session.setAttribute("token", idToken);
		
		//System.out.println("TOKEN: " + idToken);
		ClienteDao clienteDao = DatabaseManager.getInstance().getDaoFactory().getClienteDAO();

		Cliente cliente = clienteDao.findByPrimaryKey(email);

		if (cliente == null) {
			
			cliente = new Cliente(nome, cognome, null, null, null, email, password);
			clienteDao.save(cliente);
			clienteDao.setPassword(cliente);
			//System.out.println("ho salvato il cliente perchè era null");
			
		} /*else {
			System.out.println("LoginSocial -> else -> cliente: " + cliente.getIndirizzoEmail());
		}*/
		
		Cookie cookieName = new Cookie("email", cliente.getIndirizzoEmail());
		Cookie cookiePass = new Cookie("password", cliente.getPassword());
		
		cookieName.setMaxAge(60);
		cookiePass.setMaxAge(60);
		
		response.addCookie(cookieName);
		response.addCookie(cookiePass);
		
		session.setAttribute("cliente", cliente);
		session.setAttribute("email", email);
		session.setAttribute("nome", nome);
		session.setAttribute("cognome", cognome);
		session.setAttribute("password", password);
		session.setAttribute("loggato", true);

		response.sendRedirect("AreaRiservata.jsp");

	}

}
