package persistence;

import persistence.dao.ClienteDao;
import persistence.dao.EscursioneDao;
import persistence.dao.EventoDao;
import persistence.dao.FotografiaDao;
import persistence.dao.PrenotazioneDao;
import persistence.dao.RecensioniDao;

public abstract class DAOFactory {

	// --- List of supported DAO types ---

	
	/**
	 * Numeric constant '1' corresponds to explicit Hsqldb choice
	 */
	public static final int HSQLDB = 1;
	
	/**
	 * Numeric constant '2' corresponds to explicit Postgres choice
	 */
	public static final int POSTGRESQL = 2;
	
	
	// --- Actual factory method ---
	
	/**
	 * Depending on the input parameter
	 * this method returns one out of several possible 
	 * implementations of this factory spec 
	 */
	public static DAOFactory getDAOFactory(int whichFactory) {
		switch ( whichFactory ) {
		
		case HSQLDB:
			return null;//new HsqldbDAOFactory();
		case POSTGRESQL:
			return new PostgresDAOFactory();
		default:
			return null;
		}
	}
	
	
	
	// --- Factory specification: concrete factories implementing this spec must provide this methods! ---

	public abstract PrenotazioneDao getPrenotazioneDAO();
	public abstract ClienteDao getClienteDAO();
	public abstract EventoDao getEventoDAO();
	public abstract FotografiaDao getFotografiaDAO();
	public abstract RecensioniDao getRecensioneDao();
	public abstract EscursioneDao getEscursioneDao();
	public abstract persistence.UtilDao getUtilDAO();

}