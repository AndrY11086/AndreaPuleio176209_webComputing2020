package controller;

import java.io.IOException;
import java.util.LinkedList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.swing.plaf.synth.SynthSpinnerUI;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.google.gson.JsonArray;

import model.Prenotazione;
import persistence.DAOFactory;
import persistence.dao.PrenotazioneDao;

/**
 * Servlet implementation class GestionePrenotazione
 * 
 * @param <T>
 */
@WebServlet("/GestionePrenotazione")
public class GestionePrenotazione<T> extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public GestionePrenotazione() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession();
		String primaryKey = (String) session.getAttribute("email");

		String id = request.getParameter("id");

		PrenotazioneDao prnDao = DAOFactory.getDAOFactory(DAOFactory.POSTGRESQL).getPrenotazioneDAO();
		LinkedList<Prenotazione> prn = (LinkedList<Prenotazione>) session.getAttribute("prenotazioni");

		if (prn == null) {
			prn = new LinkedList<>();

			if (primaryKey.equals("admin@admin.it")) {
				prn = (LinkedList<Prenotazione>) prnDao.findAll();

			} else {
				prn = prnDao.findByEmail(primaryKey);
			}

			session.setAttribute("prenotazioni", prn);
			
		}
		// prn = sort(prn, prnDao, primaryKey, id);

		/*String txt = "<h3 align='center'><em> Lista Prenotazioni: </em></h3>";

		// txt += "<button class='btn btn-info' id='crescente'
		// onclick='visualizzaPrenotazioni(this.id);'> Ordine Crescente</button>";
		// txt += "<button class='btn btn-info' id='decrescente'
		// onclick='visualizzaPrenotazioni(this.id);'> Ordine Decrescente</button>";

		txt += "<div id='accordion'>";

		for (Prenotazione prenotazione : prn) {
			txt += "<div align='center' class='card col-sm-8 container' style='background-color: #942536'>"
					+ "<div class='card-header' >"
					+ "<a style='color:yellow' class='card-link' data-toggle='collapse' href='#collapse"
					+ prenotazione.getIdPrenotazione() + "'>" + "numero prenotazione: "
					+ prenotazione.getIdPrenotazione() + "  arrivo: " + prenotazione.getArrivo() + " partenza: "
					+ prenotazione.getPartenza() + "</a></div>" + "<div id='collapse" + prenotazione.getIdPrenotazione()
					+ "' class='collapse' data-parent='#accordion'>" + "<div class='card-body'>"
					+ "<p style='color:yellow' id='idPrn'>id: " + prenotazione.getIdPrenotazione()
					+ "<p style='color:yellow'> Ospite: " + prenotazione.getNomeOspite() + "</p>"
					+ "<p style='color:yellow'> Data di arrivo: " + prenotazione.getArrivo() + " Data di partenza: "
					+ prenotazione.getPartenza() + "</p>" + "<p style='color:yellow'> numero di Ospiti: "
					+ prenotazione.getNumeroPersone() + " numero notti: " + prenotazione.getnNotti() + "</p>"
					+ "<p style='color:yellow'> prezzo: " + prenotazione.getPrezzo() + "0 &euro;" + " pagamento "
					+ prenotazione.getPagamento() + "</p>"
					+ "<button class='btn btn-danger' onclick='rimuoviPrenotazione()'>Rimuovi Prenotazione</button>"
					+ "</div></div></div></div></div><hr>";
		}

		txt += "</div>";*/
		
		JSONArray array = new JSONArray();
			try {
				for (Prenotazione prenotazione : prn) {
					JSONObject jsonPrenotazioni = new JSONObject();
//					jsonPrenotazioni = new JSONObject();
					jsonPrenotazioni.put("id", prenotazione.getIdPrenotazione());
					jsonPrenotazioni.put("dataArrivo", prenotazione.getArrivo());
					jsonPrenotazioni.put("dataPartenza", prenotazione.getPartenza());
					jsonPrenotazioni.put("nomeOspite", prenotazione.getNomeOspite());
					jsonPrenotazioni.put("nPersone", prenotazione.getNumeroPersone());
					jsonPrenotazioni.put("nNotti", prenotazione.getnNotti());
					jsonPrenotazioni.put("pagamento",prenotazione.getPagamento());
					jsonPrenotazioni.put("prezzo", prenotazione.getPrezzo());
					
					array.put(jsonPrenotazioni);
					
				}
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		response.getWriter().print(array);

	}

	/*
	 * private LinkedList<Prenotazione> sort(LinkedList<Prenotazione> prn,
	 * PrenotazioneDao prnDao, String primaryKey, String id) {
	 * 
	 * if (primaryKey.equals("capuleio@virgilio.it")) { prn =
	 * (LinkedList<Prenotazione>) prnDao.findAll();
	 * 
	 * } else { prn = prnDao.findByEmail(primaryKey); }
	 * 
	 * LinkedList<Prenotazione> toReturn = new LinkedList<>();
	 * 
	 * for (Prenotazione prenotazione : prn) {
	 * 
	 * if (id.equals("crescente")) { toReturn.add(prenotazione); } else if
	 * (id.equals("decrescente")) { toReturn.addFirst(prenotazione); } }
	 * 
	 * return toReturn;
	 * 
	 * }
	 */

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
