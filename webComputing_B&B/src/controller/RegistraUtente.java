package controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.sun.corba.se.impl.protocol.RequestDispatcherRegistryImpl;

import model.Cliente;
import persistence.DAOFactory;
import persistence.dao.ClienteDao;

/**
 * Servlet implementation class RegistraUtente
 */
@WebServlet("/RegistraUtente")
public class RegistraUtente extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public RegistraUtente() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		//System.out.println("servlet RegistrUtente invocata");

		HttpSession session = request.getSession();

		session.setAttribute("registrato", true);

		String nome = request.getParameter("nome");
		String cognome = request.getParameter("cognome");
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		String city = request.getParameter("city");
		String numeroTelefono = request.getParameter("numeroTelefono");
		String sessoM = request.getParameter("optradio");
		String sesso;
		if (sessoM.equals("maschio")) {
			sesso = "M";

		} else {
			sesso = "F";
		}

		DAOFactory factory = DAOFactory.getDAOFactory(DAOFactory.POSTGRESQL);
		ClienteDao clienteDao = factory.getClienteDAO();

		Cliente verifica = clienteDao.findByPrimaryKey(email);
		Cliente cliente = new Cliente(nome, cognome, numeroTelefono, sesso, city, email, password);

		if (verifica == null) {
			clienteDao.save(cliente);
			clienteDao.setPassword(cliente);
		} else {
			clienteDao.setPassword(cliente);
			clienteDao.update(cliente);
		}

		RequestDispatcher dispatcher = request.getRequestDispatcher("Home.jsp");
		dispatcher.forward(request, response);
	}
}
