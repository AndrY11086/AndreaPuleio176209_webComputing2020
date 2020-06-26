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
import com.itextpdf.layout.element.List;

import model.Evento;
import persistence.DAOFactory;
import persistence.dao.EventoDao;

/**
 * Servlet implementation class EventiPage
 */
@WebServlet("/EventiPage")
public class EventiPage extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public EventiPage() {
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

		String foto = "images/eventi/logobb.jpg";
		session.setAttribute("fotoEvento",foto);

		EventoDao eventoDao = DAOFactory.getDAOFactory(DAOFactory.POSTGRESQL).getEventoDAO();
		LinkedList<Evento> eventi = eventi = (LinkedList<Evento>) eventoDao.findAll();//(LinkedList<Evento>) session.getAttribute("eventi");
		session.setAttribute("eventi", eventi);

		if (eventi == null) {
			
		}

		String identify = request.getParameter("id");

		if (identify == null) {
			JSONArray array = new JSONArray();

			try {
				for (Evento evento : eventi) {
					JSONObject json = new JSONObject();
					json.put("nomeEvento", evento.getNome());
					array.put(json);
				}
			} catch (JSONException e) {

				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			//System.out.println("array: " + array);
			response.getWriter().print(array);

		} else

		{
			int id = Integer.parseInt(request.getParameter("id"));

			Evento evento = eventi.get(id);
			String type = switchType(evento.getTipo());

			session.setAttribute("evento", evento);
			session.setAttribute("fotoEvento", type);
			
			// int id = (int)session.getAttribute("currentEvent");
			/*
			 * String txt = "";
			 * 
			 * txt += "<div class='centered'>"; txt +=
			 * "<img src='"+type+"' alt='Concerto'>"; txt += "<div class='container'>"; txt
			 * += "<h3>"+evento.getNome()+"</h3>"; txt +=
			 * "<table class='table table-striped'>";
			 * 
			 * txt += "<tbody>"; txt += "<tr>"; txt += "<td>Data:</td>"; txt += "<td>" +
			 * evento.getDataEvento() + "</td></tr>"; txt += "<tr><td>Tipo:</td><td>" +
			 * evento.getTipo() + "</td></tr>"; txt += "<tr><td>Durata:</td><td>" +
			 * evento.getDurata() + "</td></tr>"; txt += "<tr> <td>Descrizione:</td><td>" +
			 * evento.getDescrizione() + "</td> </tr>"; txt +=
			 * "</tbody></table></div></div>";
			 */

			// response.getWriter().print(txt);
		}

		/*
		 * String txt = ""; int id = 0; for (Evento evento : eventi) { txt +=
		 * "<button id='" + id //evento.getId() +
		 * "' class='btn btn-info btn-block btn-sm' onclick='mostraEvento(this.id)'>" +
		 * evento.getNome() + "</button>";
		 * 
		 * id++; }
		 * 
		 * if (session.getAttribute("loggato") != null && (boolean)
		 * session.getAttribute("loggato")) { txt +=
		 * "<button id='addEvent' class='btn btn-info btn-block btn-sm' onclick='aggiungi()'> + Aggiungi Evento </button>"
		 * ; }
		 * 
		 * session.setAttribute("currentEvent", eventId(session));
		 */

		// response.getWriter().print(txt);

	}

	private String switchType(String tipo) {

		switch (tipo) {
		case "Concerto":
			return "images/eventi/concerto.jpg";
		case "Festa di Paese":
			return "images/eventi/festadipaese.jpg";
		case "Sagra":
			return "images/eventi/sagra.jpg";
		default:
			return "images/eventi/logobb.jpg";
		}

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	}

	/*private int eventId(HttpSession session) {

		if (session.getAttribute("currentEvent") == null)
			session.setAttribute("currentEvent", 0);
		else
			session.setAttribute("currentEvent", (int) session.getAttribute("currentEvent"));

		return (int) session.getAttribute("currentEvent");
	}*/

}
