package persistence;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;

import model.Cliente;
import model.Prenotazione;
import persistence.dao.PrenotazioneDao;

public class PrenotazioneDaoJDBC implements PrenotazioneDao {

	private DataSource dataSource;

	public PrenotazioneDaoJDBC(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	@Override
	public void save(Prenotazione prenotazione) {

		Connection connection = this.dataSource.getConnection();

		try {
			String insert = "insert into prenotazione(idPrenotazione, email, nomeCliente, numeroPersone, dataArrivo, dataPartenza, prezzo, pagamento, numeroNotti) values(?,?,?,?,?,?,?,?,?)";
			//PreparedStatement statement = connection.prepareStatement(insert, Statement.RETURN_GENERATED_KEYS);
			PreparedStatement statement = connection.prepareStatement(insert);
			
			int id = IDBroker.getId(connection);
			prenotazione.setIdPrenotazione(id);
			
			statement.setInt(1, prenotazione.getIdPrenotazione());
			statement.setString(2, prenotazione.getOspite().getIndirizzoEmail());
			statement.setString(3, prenotazione.getOspite().getCognome() + " " + prenotazione.getOspite().getNome());
			statement.setInt(4, prenotazione.getNumeroPersone());
			statement.setDate(5, prenotazione.getArrivo());
			statement.setDate(6, prenotazione.getPartenza());
			statement.setFloat(7, prenotazione.getPrezzo());
			statement.setString(8, prenotazione.getPagamento());
			statement.setInt(9, prenotazione.getnNotti());

			
			statement.executeUpdate();
			
			/*ResultSet rs = statement.getGeneratedKeys();
			while(rs.next()) {
				int id = rs.getInt(1);
				
				prenotazione.setIdPrenotazione(id);
			}*/
			

		} catch (Exception e) {
			System.out.println("CATCH SAVE: " + e.getMessage() + "   -----");
			if (connection != null) {
				try {
					connection.rollback();
				} catch (Exception e2) {
					throw new PersistenceException(e2.getMessage());
				}
			}
			
			throw new PersistenceException(e.getMessage());
			
		} finally {
			try {
				connection.close();
			} catch (Exception e2) {
				throw new PersistenceException(e2.getMessage());
			}
		}
	}

	@Override
	public Prenotazione findByPrimaryKey(int id) {
		Connection connection = this.dataSource.getConnection();
		Prenotazione prenotazione = null;
		
		try {
			String query = "select * from prenotazione where idPrenotazione = ?";
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setInt(1, id);
			ResultSet result = statement.executeQuery();
			if (result.next()) {
				prenotazione = new Prenotazione();
				prenotazione.setIdPrenotazione(result.getInt("idPrenotazione"));
				prenotazione.setNomeOspite(result.getString("nomeCliente"));
				prenotazione.setEmailOspite(result.getString("email"));
				prenotazione.setArrivo(result.getDate("dataArrivo"));
				prenotazione.setPartenza(result.getDate("dataPartenza"));
				prenotazione.setPrezzo(result.getFloat("prezzo"));
				prenotazione.setPagamento(result.getString("pagamento"));
				prenotazione.setNumeroPersone(result.getInt("numeroPersone"));
				prenotazione.setnNotti(result.getInt("numeroNotti"));
			}

		} catch (SQLException e) {
			throw new PersistenceException(e.getMessage());
		} finally {
			try {
				connection.close();
			} catch (Exception e2) {
				throw new PersistenceException(e2.getMessage());
			}
		}

		return prenotazione;
	}

	@Override
	public LinkedList<Prenotazione> findByEmail(String email) {
		Connection connection = this.dataSource.getConnection();
		LinkedList<Prenotazione> prenotazioni = new LinkedList<>();

		try {
			Prenotazione prenotazione;
			String query = "select * from prenotazione where email = ?";
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setString(1, email);
			ResultSet result = statement.executeQuery();

			while (result.next()) {
				prenotazione = findByPrimaryKey(result.getInt("idPrenotazione"));
				prenotazioni.add(prenotazione);
			}

		} catch (SQLException e) {
			System.out.println("findAll: " + e.getMessage());
			throw new PersistenceException(e.getMessage());
		} finally {
			try {
				connection.close();
			} catch (SQLException e) {
				throw new PersistenceException(e.getMessage());
			}
		}

		return prenotazioni;
	}
	
	
	@Override
	public LinkedList<Prenotazione> findAll() {
		Connection connection = this.dataSource.getConnection();
		LinkedList<Prenotazione> prenotazioni = new LinkedList<>();

		try {
			Prenotazione prenotazione;
			String query = "select * from prenotazione";
			PreparedStatement statement = connection.prepareStatement(query);
			ResultSet result = statement.executeQuery();

			while (result.next()) {
				prenotazione = findByPrimaryKey(result.getInt("idPrenotazione"));
				prenotazioni.addFirst(prenotazione);
			}

		} catch (SQLException e) {
			System.out.println("findAll: " + e.getMessage());
			throw new PersistenceException(e.getMessage());
		} finally {
			try {
				connection.close();
			} catch (SQLException e) {
				throw new PersistenceException(e.getMessage());
			}
		}

		return prenotazioni;
	}

	@Override
	public void update(Prenotazione prenotazione) {
		Connection connection = this.dataSource.getConnection();

		try {
			String update = "update prenotazione SET cliente = ?, numeroPersone = ?, dataArrivo = ?, dataPartenza = ?, prezzo = ?, pagamento = ?, numeroNotti = ? WHERE idPrenotazione = ?";
			
			PreparedStatement statement = connection.prepareStatement(update);
			statement.setString(1, prenotazione.getOspite().getCognome() + " " + prenotazione.getOspite().getNome());
			statement.setInt(2, prenotazione.getNumeroPersone());
			statement.setDate(3, prenotazione.getArrivo());
			statement.setDate(4, prenotazione.getPartenza());
			statement.setFloat(5, prenotazione.getPrezzo());
			statement.setString(6, prenotazione.getPagamento());
			statement.setInt(7, prenotazione.getIdPrenotazione());
			statement.setInt(8, prenotazione.getnNotti());
			
			statement.executeUpdate();
			
			
		} catch (Exception e) {
			System.out.println("CATCH: " + e.getMessage());
			if (connection != null) {
				try {
					connection.rollback();
				} catch(SQLException e1) {
					throw new PersistenceException(e1.getMessage());
				}
			}
			throw new PersistenceException(e.getMessage());
		} finally {
			try {
				connection.close();
			} catch (SQLException e) {
				throw new PersistenceException(e.getMessage());
			}
		}
	}

	@Override
	public void delete(Prenotazione prenotazione) {
		Connection connection = this.dataSource.getConnection();
		
		try {
			String delete = "delete FROM prenotazione WHERE idPrenotazione = ?";
			PreparedStatement statement = connection.prepareStatement(delete);
			statement.setInt(1, prenotazione.getIdPrenotazione());
			
			connection.setAutoCommit(false);
			connection.setTransactionIsolation(connection.TRANSACTION_SERIALIZABLE);
			
			statement.executeUpdate();
			connection.commit();
			
		} catch (Exception e) {
			throw new PersistenceException(e.getMessage());
		}finally {
			try {
				connection.close();
			} catch (SQLException e) {
				throw new PersistenceException(e.getMessage());
			}
		}
	}

}
