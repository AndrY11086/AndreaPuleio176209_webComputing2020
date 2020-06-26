package controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONException;
import org.json.JSONObject;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import model.Cliente;
import persistence.DAOFactory;
import persistence.dao.ClienteDao;

/**
 * Servlet implementation class ModificaProfilo
 */
@WebServlet("/ModificaProfilo")
public class ModificaProfilo extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ModificaProfilo() {
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
		HttpSession session = request.getSession();

		//String tipo = request.getParameter("tipo");
		String email = request.getParameter("email");// (String) session.getAttribute("email");
		String nome = request.getParameter("nome");
		String cognome = request.getParameter("cognome");
		String nTelefono = request.getParameter("telefono");
		String citta = request.getParameter("city");
		String password = request.getParameter("password");

		String button = request.getParameter("button");
		//System.out.println("button: " + button);
		//boolean aggiorna = false;

		ClienteDao clienteDao = DAOFactory.getDAOFactory(DAOFactory.POSTGRESQL).getClienteDAO();

		Cliente cliente = (Cliente) session.getAttribute("cliente");

		if (cliente != null) {
			
			/*if (!cliente.getNome().equals(nome) || !cliente.getCognome().equals(cognome)
					|| !cliente.getIndirizzoEmail().equals(email) || !cliente.getNumeroTelefono().equals(nTelefono)
					|| !cliente.getCity().equals(citta)) {
				System.out.println("equals: " + nome + " " + cognome + " " + citta + " " + nTelefono + " " + email);
				aggiorna = true;
			}*/

			if (button.equals("remove")) {//tipo.equals("rimuovi") && tipo != null) {
				clienteDao.delete(cliente);
				Cookie cookieName = new Cookie("email", "");
				Cookie cookiePass = new Cookie("password", "");
				cookieName.setMaxAge(0);
				cookiePass.setMaxAge(0);
				response.addCookie(cookieName);
				response.addCookie(cookiePass);
				session.setAttribute("loggato", false);
				session.invalidate();
				
				RequestDispatcher dispatcher = request.getRequestDispatcher("Home.jsp");
				dispatcher.forward(request, response);

			} else /*if (tipo.equals("update"))*/ {
				//System.out.println("chiamo update: " + nome + " " + cognome + " " + citta + " " + nTelefono + " " + password);
				cliente.setNome(nome);
				cliente.setCognome(cognome);
				cliente.setCity(citta);
				cliente.setNumeroTelefono(nTelefono);
				cliente.setPassword(password);
				
				//System.out.println("cliente: " + cliente.getNome() + " " + cliente.getCity() + " " + cliente.getCognome() + " " + cliente.getPassword());
				
				clienteDao.update(cliente);
				session.setAttribute("cliente", cliente);
				
				RequestDispatcher dispatcher = request.getRequestDispatcher("AreaRiservata.jsp");
				dispatcher.forward(request, response);
			}
			// response.getWriter().print(jsonCliente);
		} else {
			cliente = clienteDao.findByPrimaryKey(email);
			session.setAttribute("cliente", cliente);
		}
	}

}
