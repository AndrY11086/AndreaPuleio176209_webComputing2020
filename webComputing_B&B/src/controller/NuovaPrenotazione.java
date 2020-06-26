package controller;

import java.io.IOException;
import java.sql.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONException;
import org.json.JSONObject;

import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;

import model.Cliente;
import model.Prenotazione;
import persistence.DAOFactory;
import persistence.dao.ClienteDao;
import persistence.dao.PrenotazioneDao;

/**
 * Servlet implementation class NuovaPrenotazione
 */
@WebServlet("/NuovaPrenotazione")
public class NuovaPrenotazione extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession();
		String mail = (String) session.getAttribute("email");

		setParameters(request, session);

		String numeroPersone = request.getParameter("numeroPersone");
		String prezzo = request.getParameter("prezzo");
		String dataArrivo = request.getParameter("dataArrivo");
		String dataPartenza = request.getParameter("dataPartenza");
		String pagamento = request.getParameter("pagamento");
		String numeroNotti = request.getParameter("notti");

		String[] toSplit = prezzo.split(" ");
		int price = Integer.parseInt(toSplit[1]);
		int nPax = Integer.parseInt(numeroPersone);
		int nNotti = Integer.parseInt(numeroNotti);

		java.sql.Date arrivo = correctFormat(dataArrivo);
		java.sql.Date partenza = correctFormat(dataPartenza);

		ClienteDao clienteDao = DAOFactory.getDAOFactory(DAOFactory.POSTGRESQL).getClienteDAO();
		Cliente cliente = clienteDao.findByPrimaryKey(mail);
		session.setAttribute("cliente", cliente);
		//System.out.println("cliente: " + cliente.getIndirizzoEmail() + " <<<<<<<<<<<<<<<<<<<<");

		Prenotazione prnz = new Prenotazione(cliente, nPax, arrivo, partenza, price, pagamento, nNotti);
		PrenotazioneDao prnzDao = DAOFactory.getDAOFactory(DAOFactory.POSTGRESQL).getPrenotazioneDAO();

		if (pagamento.equals("Presso la Struttura")) {
			prnzDao.save(prnz);
		}
			
		session.setAttribute("prenotazione", prnz);

	}

	private void setParameters(HttpServletRequest request, HttpSession session) {

		boolean lavatrice = Boolean.parseBoolean(request.getParameter("lavatrice"));
		boolean air = Boolean.parseBoolean(request.getParameter("a/c"));
		boolean allIn = Boolean.parseBoolean(request.getParameter("miniBar"));

		boolean loco = Boolean.parseBoolean(request.getParameter("loco"));
		boolean bar = Boolean.parseBoolean(request.getParameter("bar"));
		boolean barCamera = Boolean.parseBoolean(request.getParameter("barCamera"));

		session.setAttribute("lavatrice", lavatrice);
		session.setAttribute("air", air);
		session.setAttribute("allIn", allIn);

		session.setAttribute("loco", loco);
		session.setAttribute("bar", bar);
		session.setAttribute("barCamera", barCamera);
	}

	@SuppressWarnings("deprecation")
	private Date correctFormat(String data) {

		String[] toSplit = data.split("-");
		int year = Integer.parseInt(toSplit[0]) - 1900;
		int month = Integer.parseInt(toSplit[1]) - 1;
		int day = Integer.parseInt(toSplit[2]);

		return new Date(year, month, day);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		String prezzo = request.getParameter("prezzo");
		String[] toSplit = prezzo.split(" ", 2);
		String price = toSplit[1];
		
		HttpSession session = request.getSession();
		session.setAttribute("prezzo", price);
		session.setAttribute("servlet", "nuovaPrenotazione");
		
	}

}
