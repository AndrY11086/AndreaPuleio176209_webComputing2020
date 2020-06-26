package controller;

import java.io.IOException;
import java.util.Random;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.catalina.connector.Request;

import model.Cliente;
import model.EmailSender;
import persistence.DAOFactory;
import persistence.dao.ClienteDao;

/**
 * Servlet implementation class InviaMail
 */
@WebServlet("/InviaMail")
public class InviaMail extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public InviaMail() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
	}

	

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String nome = request.getParameter("nome");
		String cognome = request.getParameter("cognome");
		String telefono = request.getParameter("telefono");
		String email = request.getParameter("email");
		String testo = request.getParameter("testo");

		String destinatario = "andreapuleio@yahoo.it";
		String oggetto = "Contatto dal sito B&B Chiara";

		String testoMail = "Contattato da: " + nome + " " + cognome + "\n";
		testoMail += "numero di telefono: " + telefono + "\n indirizzo email: " + email + "\n";
		testoMail += "Testo del messaggio:\n" + testo;

		EmailSender es = new EmailSender();
		try {
			es.sendEmail(destinatario, oggetto, testoMail);
		} catch (AddressException e) {
			e.printStackTrace();
		} catch (MessagingException e) {
			e.printStackTrace();
		}
	}

}
