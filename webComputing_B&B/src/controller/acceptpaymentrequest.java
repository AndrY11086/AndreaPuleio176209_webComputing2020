package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.stripe.Stripe;
import com.stripe.model.Charge;

import model.Cliente;
import model.Escursione;
import model.Prenotazione;
import persistence.DAOFactory;
import persistence.dao.ClienteDao;
import persistence.dao.EscursioneDao;
import persistence.dao.PrenotazioneDao;

/**
 * Servlet implementation class acceptpaymentrequest
 */
@WebServlet("/acceptpaymentrequest")
public class acceptpaymentrequest extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void processRequest(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		response.setContentType("text/html");// Set response content type
		String title = "Pagamento B&B Chiara";
		String docType = "<!DOCTYPE html>\n";

		HttpSession session = request.getSession();
		String price = (String) session.getAttribute("prezzo");
		

		String servlet = (String) session.getAttribute("servlet");

		try {

			Stripe.apiKey = "sk_test_OjftzccxEUmvfutBcnadvDxk00ECzrIgbb";
			Map<String, String[]> httpParameters = (Map<String, String[]>) request.getParameterMap();

			// esctract request parameters
			Map<String, Object> userBillingData = new HashMap<>();
			String userEmail = httpParameters.get("stripeEmail")[0];
			userBillingData.put("email", userEmail);
			userBillingData.put("stripeToken", httpParameters.get("stripeToken")[0]);

			Map<String, Object> params = new HashMap<>();
			params.put("amount", price + "00"); // or get from request
			params.put("currency", "eur"); // or get from request
			params.put("source", userBillingData.get("stripeToken"));// or get from request
			params.put("receipt_email", userEmail);

			Charge charge;

			charge = Charge.create(params);

			String chargeID = charge.getId();

			if (servlet.equals("nuovaPrenotazione")) {

				Prenotazione prn = (Prenotazione) session.getAttribute("prenotazione");
				PrenotazioneDao prnzDao = DAOFactory.getDAOFactory(DAOFactory.POSTGRESQL).getPrenotazioneDAO();
				prnzDao.save(prn);
				response.sendRedirect("Voucher");
			}
			else {
				LinkedList<Escursione> esc = (LinkedList<Escursione>) session.getAttribute("carrello");
				EscursioneDao escDao = DAOFactory.getDAOFactory(DAOFactory.POSTGRESQL).getEscursioneDao();
				for (Escursione escursione : esc) {
					escDao.save(escursione);
				}
				response.sendRedirect("VoucherEscursioni");
			}


		} catch (Exception ex) {
			out.println(docType + "<html>\n" + "<head><title>" + title + "</title></head>\n"
					+ "<body bgcolor = \"#f0f0f0\">\n" + "<h1 align = \"center\">" + title + "</h1>\n"
					+ "<p>- ERROR - TRANSAZIONE FALLITA - RITORNA ALL'AREA RISERVATA</p>"
					+ "<button onclick=location.replace('AreaRiservata.jsp')> Area Riservata </button>"
					+ "</body></html>");
			out.close();

			System.out.println("catch: " + ex.getMessage() + "<-----");
		}
	}

	// <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the
	// + sign on the left to edit the code.">
	/**
	 * Handles the HTTP <code>GET</code> method.
	 *
	 * @param request  servlet request
	 * @param response servlet response
	 * @throws ServletException if a servlet-specific error occurs
	 * @throws IOException      if an I/O error occurs
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		processRequest(request, response);
	}

	/**
	 * Handles the HTTP <code>POST</code> method.
	 *
	 * @param request  servlet request
	 * @param response servlet response
	 * @throws ServletException if a servlet-specific error occurs
	 * @throws IOException      if an I/O error occurs
	 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		processRequest(request, response);
	}

	/**
	 * Returns a short description of the servlet.
	 *
	 * @return a String containing servlet description
	 */
	@Override
	public String getServletInfo() {
		return "Short description";
	}// </editor-fold>

}
