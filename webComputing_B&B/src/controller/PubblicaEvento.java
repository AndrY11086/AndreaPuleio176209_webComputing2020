package controller;

import java.io.IOException;
import java.sql.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Evento;
import persistence.DAOFactory;
import persistence.dao.EventoDao;

/**
 * Servlet implementation class PubblicaEvento
 */
@WebServlet("/PubblicaEvento")
public class PubblicaEvento extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PubblicaEvento() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String nome = request.getParameter("nome");
		String durata = request.getParameter("durata");
		String descrizione = request.getParameter("descrizione");
		String tipo = request.getParameter("tipo");
		String data = request.getParameter("data");
		
		String[] splitDate = data.split("-");
		int year = Integer.parseInt(splitDate[0]) - 1900;
		int month = Integer.parseInt(splitDate[1]) -1;
		int day = Integer.parseInt(splitDate[2]);
		java.sql.Date dataEvento = new Date(year, month, day);
		
		System.out.println(nome + " " + durata + " " +descrizione + " " +tipo + " " +dataEvento);
		
		EventoDao eventoDao = DAOFactory.getDAOFactory(DAOFactory.POSTGRESQL).getEventoDAO();
		Evento evento = new Evento(nome, tipo, durata,descrizione,dataEvento);
		
		eventoDao.save(evento);
	}

}
