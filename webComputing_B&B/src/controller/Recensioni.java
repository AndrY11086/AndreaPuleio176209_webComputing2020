package controller;

import java.io.IOException;
import java.text.DecimalFormat;
import java.util.LinkedList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONArray;
import org.json.JSONObject;

import model.Cliente;
import model.Recensione;
import persistence.DAOFactory;
import persistence.dao.RecensioniDao;

/**
 * Servlet implementation class Recensioni
 */
@WebServlet("/Recensioni")
public class Recensioni extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Recensioni() {
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
		Cliente client = (Cliente) session.getAttribute("cliente");
		
		RecensioniDao recDao = DAOFactory.getDAOFactory(DAOFactory.POSTGRESQL).getRecensioneDao();
		LinkedList<Recensione> recensioni = (LinkedList<Recensione>) recDao.findAll();

		int[] voti = new int[5];
		voti = getVoti(voti, recensioni);
		int[] percentuali = new int[5];
		percentuali = getPercentuali(percentuali, voti, recensioni.size());
		double media = getMedia(recensioni.size(), voti);
		
		session.setAttribute("media", media);
		
		JSONArray array = new JSONArray();
		JSONObject json = new JSONObject();
		try {
			
			for (Recensione recensione : recensioni) {
				json = new JSONObject();
				json.put("voto", recensione.getVoto());
				json.put("cliente", recensione.getCliente());
				json.put("id", recensione.getId());
				json.put("commento", recensione.getCommento());
				json.put("data", recensione.getData());
				json.put("titolo", recensione.getTitolo());
				json.put("sesso", recensione.getSesso());
				
				
				array.put(json);
			}
			
			json = new JSONObject();
			if(client != null)
				json.put("clienteCheck", client.getIndirizzoEmail());
			else
				json.put("clienteCheck", "vuoto");
			
			json.put("size", recensioni.size());
			array.put(json);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		//System.out.println("array: " + array);
		response.getWriter().print(array);

		
		
		/*String function = "";
		if(client != null)
			function = "newRating(this.id)";
		else
			System.out.println("cliente null"); //oppure funzione che mi rimanda al login
		
		String txt = "<div class='row'>" + "<div class='col-sm-3'>" + "<div class='rating-block'>"
				+ "<h4>Voto medio degli utenti</h4>" + "<h2 class='bold padding-bottom-7'>" + media
				+ " <small>/ 5</small></h2>";

		for (int i = 0; i < 5; i++) {
			String css = "";
			if (i < (int)media)
				css = "btn-warning";
			else
				css = "btn-default btn-grey";
			
			int id= i+1;
			
			txt += "<button id="+id+" type='button' class='btn " + css + " btn-sm' aria-label='Left Align' onclick='"+function+"'>"
					+ "<span class='fa fa-star checked'></span>" + "</button>";
		}

		txt += "</div></div>";
		txt += "<div id='newRat'></div>";
		txt += "<div class='col-sm-3'>" + "<h3>Votazioni</h3>";

		for (int i = 0; i < 5; i++) {
			int number = 5 - i;
			String color = getColor(number);

			txt += "<div class='pull-left'>" + "<div class='pull-left' style='width:35px; line-height:1;'>"
					+ "<div style='height:9px; margin:5px 0; color:white'>" + number
					+ "<span id='star' class='fa fa-star checked'></span></div>" + "</div>"
					+ "<div class='pull-left' style='width:180px;'>"
					+ "<div class='progress' style='height:9px; margin:8px 0;'>" + "<div class='progress-bar bg-"
					+ color + "' role='progressbar' aria-valuenow=" + number
					+ " aria-valuemin='0' aria-valuemax='5' style='width: " + percentuali[number - 1] + "%'>"
					+ "<span class='sr-only'>80% Complete (danger)</span>" + "</div></div></div>"
					+ "<div class='pull-right' style='margin-left:10px; color:white'>" + voti[number - 1] + "</div>"
					+ "</div>";
		}

		txt += "</div></div></div>";

		txt += "<div class='row'>" + "<div class='col-sm-7'>" + "<hr/><div class='review-block'>";
		for (Recensione recensione : recensioni) {
			txt += "<div class='row'>" + "<div class='col-sm-3'>"
					+ "<img src='images/Avatar/man.png' class='mr-3 mt-3 rounded-circle'>"
					+ "<div class='review-block-name'>" + recensione.getCliente() + "</div>"
					+ "<div class='review-block-date'>" + recensione.getData() + "</div>" + "</div>"
					+ "<div class='col-sm-9'>" + "<div class='review-block-rate'>";
			for (int i = 0; i < 5; i++) {
				String css = "";

				if (i < recensione.getVoto()) {
					css = "btn-warning";
				} else {
					css = "btn-default btn-grey";
				}

				txt += "<button type='button' class='btn " + css + " btn-xs' aria-label='Left Align'>"
						+ "<span class='fa fa-star' aria-hidden='true'></span>" + "</button>";
			}

			txt += "</div><div class='review-block-title'>" + recensione.getTitolo() + "</div>"
					+ "<div class='review-block-description'>" + recensione.getCommento() + "</div>" + "</div>"
					+ "</div>";
		}*/

		
	}
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		Cliente cliente = (Cliente) session.getAttribute("cliente");
		String voto = request.getParameter("voto");
		String commento = request.getParameter("commento");
		String titolo = request.getParameter("titolo");
		
		String nomeCliente = cliente.getCognome() + " " + cliente.getNome();
		String[] toSplit = voto.split(" ");
		int valoreVoto = Integer.parseInt(toSplit[1]);
		
		System.out.println(valoreVoto +", " + commento + ", " + titolo +", " + nomeCliente);
		
		RecensioniDao recDao = DAOFactory.getDAOFactory(DAOFactory.POSTGRESQL).getRecensioneDao();
		Recensione rec = new Recensione(commento, valoreVoto, nomeCliente, titolo, cliente.getSesso());
		
		recDao.save(rec);
	}

	private double getMedia(int tot, int[] voti) {
		double media = 0;

		for (int i = 0; i < voti.length; i++) {
			media += voti[i] * (i + 1);
		}
		double toReturn = media/tot;
		
		toReturn = Math.round(toReturn * 100.0) / 100.0;
		
		return toReturn;
	}

	private int[] getPercentuali(int[] percentuali, int[] voti, int recensioniSize) {

		if (recensioniSize > 0) {

			for (int i = 0; i < voti.length; i++) {
				float div = ((float)voti[i]) / recensioniSize;
				percentuali[i] = (int) (div * 100);
			}
		}

		return percentuali;
	}

	private int[] getVoti(int[] voti, LinkedList<Recensione> recensioni) {

		for (Recensione recensione : recensioni) {
			voti[recensione.getVoto() - 1]++;
		}
		return voti;
	}

	/*private String getColor(int n) {
		switch (n) {
		case (5):
			return "success";
		case (4):
			return "primary";
		case (3):
			return "info";
		case (2):
			return "warning";
		case (1):
			return "danger";
		default:
			return "";

		}
	}*/


}
