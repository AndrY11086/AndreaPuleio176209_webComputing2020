package test.persistence;

import java.sql.Date;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.util.LinkedList;
import java.util.Locale;

import javafx.scene.image.Image;
import model.Cliente;
import model.Escursione;
import model.Evento;
import model.Fotografia;
import model.Prenotazione;
import model.Recensione;
import model.Stanza;
import persistence.ClienteDaoJDBC;
import persistence.DAOFactory;
import persistence.UtilDao;
import persistence.dao.ClienteDao;
import persistence.dao.EscursioneDao;
import persistence.dao.EventoDao;
import persistence.dao.FotografiaDao;
import persistence.dao.PrenotazioneDao;
import persistence.dao.RecensioniDao;

public class MainJDBC {

	public static void main(String[] args) throws SQLException {
		
		DAOFactory factory = DAOFactory.getDAOFactory(DAOFactory.POSTGRESQL);
		UtilDao util = factory.getUtilDAO();
		util.dropDatabase();
		
		util.createDatabase();
		
		ClienteDao clienteDao = factory.getClienteDAO();
		Cliente admin = new Cliente("Andrea", "Puleio", "3334445556", "M", "Polistena", "admin@admin.it", "12345");
		clienteDao.save(admin);
		clienteDao.setPassword(admin);
		
		Cliente cliente1 = new Cliente("Mario", "Rossi", "333555911", "M", "Roma", "mariorossi@email.it", "mario");
		Cliente cliente2 = new Cliente("Carla", "Bianchi", "333555112", "F", "Firenze", "carlabianchi@email.it", "carla");
		clienteDao.save(cliente1);
		clienteDao.setPassword(cliente1);
		clienteDao.save(cliente2);
		clienteDao.setPassword(cliente2);
		
		System.out.println("cliente1: " + cliente1.getPassword());
		System.out.println("cliente2: " + cliente2.getPassword());
		
		Prenotazione prnz = new Prenotazione(cliente1, 2, new java.sql.Date(120, 0, 18), new java.sql.Date(120, 12, 20), 100.00f,"Presso la Struttura",2);
		PrenotazioneDao prnzDao = factory.getPrenotazioneDAO();
		prnzDao.save(prnz);
		
		Prenotazione prnz1 = new Prenotazione(cliente2,4, new java.sql.Date(120,3,25), new java.sql.Date(120,3,30), 200.00f, "Presso la Struttura",5);
		prnzDao.save(prnz1);
		
		Evento ev = new Evento("Concerto ", "Concerto", "3 ore circa", "gruppo rock proveniente da Reggio Calabria", new java.sql.Date(120,3,20));
		EventoDao evDao = factory.getEventoDAO();
		evDao.save(ev);

		Evento ev1 = new Evento("Fiera", "Festa di Paese", "tutto il giorno", "mercanti", new java.sql.Date(120,4,15));
		evDao.save(ev1);
		
		RecensioniDao recDao = DAOFactory.getDAOFactory(DAOFactory.POSTGRESQL).getRecensioneDao();
		Recensione rec = new Recensione("ottima posiione e servizio", 5, cliente1.getCognome() + " " + cliente1.getNome(), "ottimo",cliente1.getSesso());
		recDao.save(rec);
		
		Recensione rec1 = new Recensione("Pessimo", 2, cliente2.getCognome() + " " + cliente2.getNome(), "MAI piu",cliente2.getSesso());
		recDao.save(rec1);
		
		EscursioneDao escDao = DAOFactory.getDAOFactory(DAOFactory.POSTGRESQL).getEscursioneDao();
		Escursione esc = new Escursione("Giornata alle isole Eolie", 45, cliente1, 2);
		escDao.save(esc);
		
		Escursione esc1 = new Escursione("Lido con 1 ombrellone e 2 lettini", 110, cliente2, 4);
		escDao.save(esc1);
		
		/*Fotografia f = new Fotografia("C:\\Users\\Andry\\Desktop\\Eclipse\\webComputing_B&B\\WebContent\\images\\LeCamere\\doppia.jpg","foto1", "prima foto da mostrare");
		FotografiaDao fDao = factory.getFotografiaDAO();
		fDao.save(f);
		
		Fotografia f2 = new Fotografia("C:\\Users\\Andry\\Desktop\\Eclipse\\webComputing_B&B\\WebContent\\images\\LeCamere\\matrimoniale.jpg","foto3", "terza foto da mostrare");
		fDao.save(f2);
		
		Fotografia f1 = new Fotografia("C:\\Users\\Andry\\Desktop\\Eclipse\\webComputing_B&B\\suiteChiara.jpg","foto2", "seconda foto da mostrare");
		fDao.save(f1);
		
		
		prnzDao.delete(prnz);
		
		fDao.delete(f1);
		
		Fotografia fotoProva = fDao.findByPrimaryKey(f.getIdFoto());
		
		System.out.println("prova " + fotoProva.getDescription() + " " + fotoProva.getIdFoto() + " " + fotoProva.getPath() + " " + fotoProva.getTitle());
		
		fDao.delete(fotoProva);
		*/
		
	}

}
