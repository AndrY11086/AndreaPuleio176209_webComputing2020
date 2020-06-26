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
import persistence.DatabaseManager;
import persistence.dao.ClienteDao;

/**
 * Servlet implementation class EffettuaLogin
 */
@WebServlet("/EffettuaLogin")
public class EffettuaLogin extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public EffettuaLogin() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	}
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		HttpSession session = request.getSession();
		boolean login;
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		
		String check = request.getParameter("checkbox");
		boolean rememberMe = Boolean.parseBoolean(check);

		ClienteDao clienteDao = DatabaseManager.getInstance().getDaoFactory().getClienteDAO();
		Cliente cliente = clienteDao.findByPrimaryKey(email);
		
		//session.setAttribute("loggato", null);
		//session.setAttribute("credenzialiErrate", null);
		
		if(cliente !=null) {
			session.setAttribute("loggato", true);
			if(cliente.getIndirizzoEmail().equals("admin@admin.it")) {
				session.setAttribute("admin", true);
		}
		
			
		}

		//System.out.println("cliente: " + cliente.getCognome() + " " + cliente.getIndirizzoEmail() + " " + cliente.getPassword());
		
		if (cliente != null && cliente.getPassword().equals(password)) {
			
			session.setAttribute("cliente", cliente);

			session.setAttribute("email", email);
			session.setAttribute("password", password);

			session.setAttribute("loggato", true);
			session.setAttribute("credenzialiErrate", false);
			
			Cookie cookieName = new Cookie("email", cliente.getIndirizzoEmail());
			Cookie cookiePass = new Cookie("password", cliente.getPassword());
			
			if (rememberMe) {
				cookieName.setMaxAge(60*60*24);
				cookiePass.setMaxAge(60*60*24);
			}
			else {
				cookieName.setMaxAge(60*10);
				cookiePass.setMaxAge(60*10);
			}
			response.addCookie(cookieName);
			response.addCookie(cookiePass);
			
			login = (boolean) session.getAttribute("loggato");
			System.out.println("login: " + login);
			
			RequestDispatcher dispatcher = request.getRequestDispatcher("GestioneProfilo.jsp");
			dispatcher.forward(request, response);
			//response.getWriter().print(login);
		} else {
			System.out.println("credenziali errate");
			session.setAttribute("credenzialiErrate", true);
			session.setAttribute("loggato", false);
			
			login = (boolean) session.getAttribute("loggato");
			//response.getWriter().print(login);
			RequestDispatcher dispatcher = request.getRequestDispatcher("AreaRiservata.jsp");
			dispatcher.forward(request, response);
		}
	}
}
