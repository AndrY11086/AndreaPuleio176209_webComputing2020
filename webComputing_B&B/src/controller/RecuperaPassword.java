package controller;

import java.io.IOException;
import java.util.Random;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Cliente;
import model.EmailSender;
import persistence.DAOFactory;
import persistence.dao.ClienteDao;

/**
 * Servlet implementation class RecuperaPassword
 */
@WebServlet("/RecuperaPassword")
public class RecuperaPassword extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public RecuperaPassword() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String button = request.getParameter("button");
		
		if (button.equals("recover")) {

			String email = request.getParameter("email");

			ClienteDao clienteDao = DAOFactory.getDAOFactory(DAOFactory.POSTGRESQL).getClienteDAO();
			Cliente cliente = clienteDao.findByPrimaryKey(email);

			try {
				if (cliente != null) {

					String casual = generateString();
					cliente.setPassword(casual);
					clienteDao.setPassword(cliente);

					String destinatario = email;
					String oggetto = "Nuova Password";
					String testoMail = "Ciao " + cliente.getNome() + ",\nla tua nuova password è: " + casual
							+ "\n Ti consigliamo di sostituirla subito dopo l'accesso con una password tua.\n Saluti da B&B Chiara, Polistena";

					EmailSender es = new EmailSender();
					es.sendEmail(destinatario, oggetto, testoMail);
				} else {
					String destinatario = email;
					String oggetto = "Errore nel recupero della password";
					String testo = "L'indirizzo email che ci hai fornito non è presente nei nostri database. Ti consigliamo di controllare l'indirizzo corretto oppure effettuare la registrazione.";

					EmailSender es = new EmailSender();
					es.sendEmail(destinatario, oggetto, testo);
				}
				
				RequestDispatcher dispatcher = request.getRequestDispatcher("AreaRiservata.jsp");
				dispatcher.forward(request, response);
				

			} catch (AddressException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (MessagingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else {
			RequestDispatcher dispatcher = request.getRequestDispatcher("AreaRiservata.jsp");
			dispatcher.forward(request, response);
		}
	}

	private String generateString() {

		String alfa = "abcdefghilmnopqrstuvzxyjkwABCDEFGHILMNOPQRSTUVZXYJKW1234567890";
		Random random = new Random();
		String toReturn = "";

		for (int i = 0; i < 16; i++) {
			toReturn += alfa.charAt(random.nextInt(alfa.length()));
		}

		System.out.println("to return: " + toReturn);

		return toReturn;
	}
}
