package controller;

import java.io.IOException;
import java.util.LinkedList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONArray;
import org.json.JSONObject;

import model.Cliente;
import model.Escursione;

/**
 * Servlet implementation class Escursioni
 */
@WebServlet("/Escursioni")
public class Escursioni extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession();
		LinkedList<Escursione> carrello = (LinkedList<Escursione>) session.getAttribute("carrello");

		if (carrello == null) {
			carrello = new LinkedList<>();
			session.setAttribute("carrello", carrello);
		}
		
		int prezzo = Integer.parseInt(request.getParameter("prezzo"));
		String nome = request.getParameter("nome");
		int nPersone = Integer.parseInt(request.getParameter("nPax"));
		Cliente cliente = (Cliente) session.getAttribute("cliente");
		String periodo = request.getParameter("data");

		if (periodo != null) {
			session.setAttribute("periodo", periodo);
			System.out.println(periodo);
		}

		if (cliente != null) {
			Escursione esc = new Escursione(nome, prezzo, cliente, nPersone);
			carrello.add(esc);
		}

		//String txt = "";
		JSONArray array = new JSONArray();
		try {
			for (Escursione escursione : carrello) {
				//txt += escursione.getnPersone() + "x " + escursione.getNome();

				//if (carrello.size() > 1) {
				//	txt += ", ";
				//}
				JSONObject json = new JSONObject();
				json.put("nome", escursione.getNome());
				json.put("nPersone", escursione.getnPersone());
				array.put(json);
			}

		} catch (Exception e) {
			// TODO: handle exception
		}
		
		response.getWriter().print(array);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	}

}
