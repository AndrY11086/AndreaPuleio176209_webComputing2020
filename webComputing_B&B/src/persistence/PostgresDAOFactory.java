package persistence;

import com.mysql.cj.jdbc.MysqlDataSource;

import model.Prenotazione;
import persistence.dao.ClienteDao;
import persistence.dao.EscursioneDao;
import persistence.dao.EventoDao;
import persistence.dao.FotografiaDao;
import persistence.dao.PrenotazioneDao;
import persistence.dao.RecensioniDao;

class PostgresDAOFactory extends DAOFactory {

	private static DataSource dataSource;

	static {
		/* account ElephantSQL:
		 * 		email = andreapuleio@yahoo.it
		 * 		password = webComputing2020.  il "." fa parte della password
		 */

		try {
			Class.forName("org.postgresql.Driver").newInstance();

			String url = "jdbc:postgresql://kandula.db.elephantsql.com:5432/nykkpwrw";
			String user = "nykkpwrw";
			String password = "K6GAy7otC56SryrncwatO8XK65gggfSJ";

			// dataSource = new DataSource("jdbc:postgresql://localhost:56680/bbchiara","postgres","admin");

			dataSource = new DataSource(url, user, password);

			//Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
			//dataSource = new DataSource("jdbc:mysql://localhost:3306/webComputing_BB?user=root&password=root&serverTimezone=Europe/Rome","root","root");
		} catch (Exception e) {
			System.err.println("MySqlDaoFactory.class: failed to load MySQL JDBC driver\n" + e);
		}
	}
	////////////////////////////////////////////////////////////////////////////////////

	// override dei metodi di DAOFactory sugli elementi del database
	@Override
	public UtilDao getUtilDAO() {

		return new UtilDao(dataSource);
	}

	@Override
	public PrenotazioneDao getPrenotazioneDAO() {

		return new PrenotazioneDaoJDBC(dataSource);
	}

	@Override
	public ClienteDao getClienteDAO() {

		return new ClienteDaoJDBC(dataSource);
	}

	@Override
	public EventoDao getEventoDAO() {

		return new EventoDaoJDBC(dataSource);
	}

	@Override
	public FotografiaDao getFotografiaDAO() {

		return new FotografiaDaoJDBC(dataSource);
	}

	@Override
	public RecensioniDao getRecensioneDao() {

		return new RecensioniDaoJDBC(dataSource);
	}
	
	public EscursioneDao getEscursioneDao() {
		return new EscursioneDaoJDBC(dataSource);
	}

}
