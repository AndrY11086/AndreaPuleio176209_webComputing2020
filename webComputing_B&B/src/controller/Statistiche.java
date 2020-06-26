package controller;

import java.io.IOException;
import java.util.LinkedList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONException;
import org.json.JSONObject;

import model.Escursione;
import model.Prenotazione;
import persistence.DAOFactory;
import persistence.dao.EscursioneDao;
import persistence.dao.PrenotazioneDao;

/**
 * Servlet implementation class Statistiche
 */
@WebServlet("/Statistiche")
public class Statistiche extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Statistiche() {
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
		LinkedList<Prenotazione> prenotazioni = (LinkedList<Prenotazione>) session.getAttribute("prenotazioni");
		if (prenotazioni == null) {
			PrenotazioneDao prnDao = DAOFactory.getDAOFactory(DAOFactory.POSTGRESQL).getPrenotazioneDAO();
			prenotazioni = prnDao.findAll();
			session.setAttribute("prenotazioni", prenotazioni);
		}
		LinkedList<Escursione> escursioni = (LinkedList<Escursione>) session.getAttribute("escursioni");
		if (escursioni == null) {
			EscursioneDao escDao = DAOFactory.getDAOFactory(DAOFactory.POSTGRESQL).getEscursioneDao();
			escursioni = escDao.findAll();
			session.setAttribute("escursioni", escursioni);
		}

		int guadagniEscursioni = 0;
		int numeroPersoneCoinvolte = 0;

		int lido = 0;
		int eolie = 0;
		int sub = 0;
		int trac = 0;

		for (Escursione escursione : escursioni) {
			guadagniEscursioni += escursione.getPrezzo();
			numeroPersoneCoinvolte += escursione.getnPersone();

			switch (escursione.getNome()) {
			case "Lido con 1 ombrellone e 2 lettini":
				lido++;
				break;
			case "Giornata alle isole Eolie":
				eolie++;
				break;
			case "Immersioni subacquee":
				sub++;
				break;
			case "Tracciolino di Palmi":
				trac++;
				break;
			default:
				break;

			}
		}

		float percEolie = ((float) eolie / (float) escursioni.size()) * 100;
		float percLido = ((float) lido / (float) escursioni.size()) * 100;
		float percSub = ((float) sub / (float) escursioni.size()) * 100;
		float percTrac = ((float) trac / (float) escursioni.size()) * 100;

		int guadagniPrenotazioni = 0;
		int personeOspitate = 0;

		int pagatiConCarta = 0;
		int pagatiContanti = 0;
		int numeroNottiTotali = 0;
		int periodoPiuLungo = 0;

		int incassoContanti = 0;
		int incassoCC = 0;

		int[] mesi = new int[12];
		

		for (Prenotazione prenotazione : prenotazioni) {
			guadagniPrenotazioni += prenotazione.getPrezzo();
			personeOspitate += prenotazione.getNumeroPersone();
			numeroNottiTotali += prenotazione.getnNotti();

			if (prenotazione.getnNotti() > periodoPiuLungo)
				periodoPiuLungo = prenotazione.getnNotti();

			if (prenotazione.getPagamento().equals("Presso la Struttura")) {
				pagatiContanti++;
				incassoContanti += prenotazione.getPrezzo();
			} else {
				pagatiConCarta++;
				incassoCC += prenotazione.getPrezzo();
			}

			int month = prenotazione.getArrivo().getMonth();
			mesi[month]++;

		}

		float prezzoMedioSpeso = ((float) guadagniPrenotazioni) / ((float) prenotazioni.size());
		
		JSONObject jsonStat = (JSONObject) session.getAttribute("jsonStat");
		if(jsonStat == null)
			jsonStat = new JSONObject();
		
		try {
			jsonStat.put("prenotazioniTotali", prenotazioni.size());
			
			jsonStat.put("guadagniEscursioni", guadagniEscursioni);
			jsonStat.put("numeroPersoneCoinvolte", numeroPersoneCoinvolte);
			jsonStat.put("lido", lido);
			jsonStat.put("eolie", eolie);
			jsonStat.put("sub", sub);
			jsonStat.put("trac", trac);
			jsonStat.put("percEolie", percEolie);
			jsonStat.put("percLido", percLido);
			jsonStat.put("percSub", percSub);
			jsonStat.put("percTrac", percTrac);
			
			jsonStat.put("guadagniPrenotazioni", guadagniPrenotazioni);
			jsonStat.put("personeOspitate", personeOspitate);
			jsonStat.put("pagatiConCarta", pagatiConCarta);
			jsonStat.put("pagatiContanti", pagatiContanti);
			jsonStat.put("numeroNottiTotali", numeroNottiTotali);
			jsonStat.put("periodoPiuLungo", periodoPiuLungo);
			jsonStat.put("incassoContanti", incassoContanti);
			jsonStat.put("incassoCC", incassoCC);
			
			jsonStat.put("mesi", mesi);
			
			jsonStat.put("prezzoMedioSpeso", prezzoMedioSpeso);
			
			session.setAttribute("jsonStat", jsonStat);
			
			//System.out.println("jsonStat " + jsonStat.getString("guadagniPrenotazioni"));
			
			response.getWriter().print(jsonStat);
			
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

	/*	String txt = "<div class='row'><div class='col-sm-12'>";
		txt = "<div class='col'><h3>Statistiche Prenotazioni</h3>" + "<hr>" + "<p>Prenotazioni Totali: "
				+ prenotazioni.size() + "</p>" + "<p>Persone ospitate: " + personeOspitate + "</p>"
				+ "<p>Numero Notti totali: " + numeroNottiTotali + "</p>" + "<p>Guadagno Totale: "
				+ guadagniPrenotazioni + "&euro;</p>" + "<p>--->: " + incassoContanti
				+ "&euro; Pagati presso la struttura</p>" + "<p>--->: " + incassoCC
				+ "&euro; pagati tramite carta di credito</p>" + "<p>Pagamenti con c/c: " + pagatiConCarta
				+ ", --- Pagati presso la struttura: " + pagatiContanti + "</p>" + "<p>Prezzo medio speso: "
				+ prezzoMedioSpeso + "&euro; </p><hr>" + "</div>" + "<div class'col'><h3>Statistiche Escursioni</h3>"
				+ "<hr>" + "<p>Numero persone coinvolte: " + numeroPersoneCoinvolte + "</p>" + "<p>Guadagni: "
				+ guadagniEscursioni + "&euro;</p>" + "<p>Lido preferito dal: " + percLido + "% degli utenti</p>"
				+ "<p>Gita alle isole Eolie preferito dal: " + percEolie + "% degli utenti</p>"
				+ "<p>Immersioni subacquee preferite dal: " + percSub + "% degli utenti</p>"
				+ "<p>Tracciolino di Palmi preferito dal: " + percTrac + "% degli utenti</p>" + "<p></p>" + "<hr></div>"
				+ "</div></div>"
				+ "<div class='col-sm-12' style='width: 100%; min-height: 450px;' id='chart_div1'></div><hr>"
				+ "<div class='col-sm-12' style='width: 100%; min-height: 450px;' id='chart_div2'></div>"
				+ "<script> google.load(\"visualization\", \"1\", {packages:[\"corechart\"]});\r\n"
				+ "google.setOnLoadCallback(drawChart1);\r\n" + "function drawChart1() {\r\n"
				+ "  var data = google.visualization.arrayToDataTable([\r\n" + "    ['Escursione', 'Preferenze'],\r\n"
				+ "    ['Eolie',  " + eolie + "],\r\n" + "    ['Tracciolino',  " + trac + "],\r\n" + "    ['Sub',  "
				+ sub + "],\r\n" + "    ['Lido',  " + lido + "]\r\n" + "  ]);\r\n" + "\r\n" + "  var options = {\r\n"
				+ "    title: 'Escursioni',\r\n"
				+ "    hAxis: {title: 'Escursioni', titleTextStyle: {color: 'red'}}\r\n" + " };\r\n" + "\r\n"
				+ "var chart = new google.visualization.PieChart(document.getElementById('chart_div1'));\r\n"
				+ "  chart.draw(data, options);}\r\n" 
				/////////////////////////////////////////////
				+"google.load('visualization', '1', {packages:['corechart']});"
				
				+ "google.setOnLoadCallback(drawChart2);"
				
				+ "function drawChart2() {\r\n" + 
				"  var data = google.visualization.arrayToDataTable([\r\n" + 
				"    ['Mese', 'Prenotazioni'],\r\n" + 
				"    ['Gennaio',  "+mesi[0]+"],\r\n" + 
				"    ['Febbraio',  "+mesi[1]+"],\r\n" + 
				"    ['Marzo',  "+mesi[2]+"],\r\n" + 
				"    ['Aprile',  "+mesi[3]+"],\r\n" + 
				"    ['Maggio',  "+mesi[4]+"],\r\n" + 
				"    ['Giugno',  "+mesi[5]+"],\r\n" + 
				"    ['Luglio',  "+mesi[6]+"],\r\n" + 
				"    ['Agosto',  "+mesi[7]+"],\r\n" + 
				"    ['Settembre',  "+mesi[8]+"],\r\n" + 
				"    ['Ottobre',  "+mesi[9]+"],\r\n" + 
				"    ['Novembre',  "+mesi[10]+"],\r\n" + 
				"    ['Dicembre',  "+mesi[11]+"]\r\n" + 
				"  ]);"
				
				+ "var options = {\r\n" + 
				"    title: 'Prenotazioni',\r\n" + 
				"    hAxis: {title: 'Prenotazioni',  titleTextStyle: {color: '#333'}},\r\n" + 
				"    vAxis: {minValue: 0}\r\n" + 
				"  };"
				
				+ "var chart = new google.visualization.PieChart(document.getElementById('chart_div2'));"
				
				+ "chart.draw(data, options);}"
				/////////////////////////////////////////////
				+"$(window).resize(function(){\r\n" + 
				"  drawChart2();\r\n" + 
				"  drawChart1();\r\n" + 
				"});"
				+"</script>";

		response.getWriter().print(txt);*/

	}

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
