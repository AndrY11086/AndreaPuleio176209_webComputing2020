package controller;

import java.io.IOException;
import java.util.LinkedList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.Prenotazione;
import persistence.DAOFactory;
import persistence.dao.PrenotazioneDao;

/**
 * Servlet implementation class RimuoviPrenotazione
 */
@WebServlet("/RimuoviPrenotazione")
public class RimuoviPrenotazione extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public RimuoviPrenotazione() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession();
		String parameter = request.getParameter("id");

		String[] toSplit = parameter.split(" ");
		int id = Integer.parseInt(toSplit[1]);

		System.out.println("id: " + id);

		PrenotazioneDao prnzDao = DAOFactory.getDAOFactory(DAOFactory.POSTGRESQL).getPrenotazioneDAO();
		Prenotazione prn = prnzDao.findByPrimaryKey(id);
		prnzDao.delete(prn);
		
		session.setAttribute("prenotazioni", null);
	}

}
