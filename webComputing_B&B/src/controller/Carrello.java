package controller;

import java.io.IOException;
import java.util.LinkedList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.google.gson.JsonObject;

import model.Escursione;

/**
 * Servlet implementation class Carrello
 */
@WebServlet("/Carrello")
public class Carrello extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Carrello() {
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
		LinkedList<Escursione> carrello = (LinkedList<Escursione>) session.getAttribute("carrello");
		
		if(carrello == null) {
			carrello = new LinkedList<>();
			session.setAttribute("carrello", carrello);
		}
		
		session.setAttribute("servlet","carrello");

		//String txt = "<hr>";
		int total = 0;
		
		JSONArray array = new JSONArray();
		
		try {
			for (Escursione escursione : carrello) {
				JSONObject json = new JSONObject();
				json.put("nomeEscursione", escursione.getNome());
				json.put("nPersone", escursione.getnPersone());
				json.put("idEscursione", escursione.getId());
				json.put("prezzo", escursione.getPrezzo());
				json.put("clienteEscursione", escursione.getCliente());
				json.put("emailClienteEscursione", escursione.getEmailCliente());
				json.put("telefonoCliente", escursione.getTelefonoCliente());
				
				array.put(json);
				/*txt += "<div align='justify' class='alert alert-light alert-dismissible fade show' role='alert'>"
					+ escursione.getnPersone() + "x " + escursione.getNome() + " " + escursione.getPrezzo() + "&euro;"
					+ "<button id='" + escursione.getNome()
					+ "' type='button' class='close' data-dismiss='alert' aria-label='Close' onclick='remove(this.id)'>"
					+ "<span aria-hidden='true'>&times;</span>" + "  </button></div>";*/
				total += escursione.getPrezzo();
			}
			JSONObject json = new JSONObject();
			json.put("total", total);
			array.put(json);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//txt += "<hr><p align='justify' id='total'> Totale: " + total + "</p>";
		
		String prezzo = Integer.toString(total);
		session.setAttribute("prezzo", prezzo);
		
		response.getWriter().print(array);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		String toRemove = request.getParameter("toRemove");
		LinkedList<Escursione> carrello = (LinkedList<Escursione>) session.getAttribute("carrello");
		int total = Integer.parseInt((String) session.getAttribute("prezzo"));
		if (toRemove != null) {
			for (Escursione escursione : carrello) {
				if (toRemove.equals(escursione.getNome())) {
					total -= escursione.getPrezzo();
					String prezzo = Integer.toString(total);
					session.setAttribute("prezzo", prezzo);
					carrello.remove(escursione);
					break;
				}
			}
		}
	}
	
	

}
