package persistence;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;

import model.Evento;
import model.Prenotazione;
import persistence.dao.EventoDao;

public class EventoDaoJDBC implements EventoDao {

	private DataSource dataSource;

	public EventoDaoJDBC(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	@Override
	public void save(Evento evento) {
		Connection connection = this.dataSource.getConnection();
		try {

			String insert = "insert into evento(id, nomeEvento, tipo, durata, descrizione, dataEvento) values (?,?,?,?,?,?)";
			//PreparedStatement statement = connection.prepareStatement(insert, Statement.RETURN_GENERATED_KEYS);
			
			PreparedStatement statement = connection.prepareStatement(insert);
			
			int id = IDBroker.getId(connection);
			evento.setId(id);
			
			statement.setInt(1, evento.getId());
			statement.setString(2, evento.getNome());
			statement.setString(3, evento.getTipo());
			statement.setString(4, evento.getDurata());
			statement.setString(5, evento.getDescrizione());
			statement.setDate(6, evento.getDataEvento());
			statement.executeUpdate();

			/*ResultSet rs = statement.getGeneratedKeys();
			while (rs.next()) {
				int id = rs.getInt(1);

				evento.setId(id);
				System.out.println("mentre id è = " + id);
			}*/

		} catch (SQLException e) {
			
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
			} catch (SQLException e) {
				throw new PersistenceException(e.getMessage());
			}
		}

	}

	@Override
	public Evento findByPrimaryKey(int id) {

		Connection connection = dataSource.getConnection();
		Evento evento = null;
		try {
			PreparedStatement statement;
			String query = "select * from evento where id = ?";
			statement = connection.prepareStatement(query);
			statement.setInt(1, id);
			ResultSet result = statement.executeQuery();
			if (result.next()) {
				evento = new Evento();
				evento.setId(result.getInt("id"));
				evento.setNome(result.getString("nomeEvento"));
				evento.setTipo(result.getString("tipo"));
				evento.setDurata(result.getString("durata"));
				evento.setDescrizione(result.getString("descrizione"));
				evento.setDataEvento(result.getDate("dataEvento"));
			}

		} catch (Exception e) {
			throw new PersistenceException(e.getMessage());
		} finally {
			try {
				connection.close();
			} catch (SQLException e) {
				throw new PersistenceException(e.getMessage());
			}
		}

		return evento;
	}

	@Override
	public List<Evento> findAll() {

		Connection connection = this.dataSource.getConnection();
		List<Evento> eventi = new LinkedList();
		try {
			Evento evento;
			String query = "select * from evento";

			PreparedStatement statement = connection.prepareStatement(query);
			ResultSet result = statement.executeQuery();
			while (result.next()) {
				evento = findByPrimaryKey(result.getInt("id"));
				eventi.add(evento);
			}
		} catch (SQLException e) {
			throw new PersistenceException(e.getMessage());
		} finally {
			try {
				connection.close();
			} catch (SQLException e) {
				throw new PersistenceException(e.getMessage());
			}
		}

		return eventi;
	}

	@Override
	public void update(Evento evento) {

		Connection connection = this.dataSource.getConnection();
		try {
			String update = "update evento SET nomeEvento = ? tipo = ? durata = ? descrizione = ? dataEvento = ?  WHERE id = ?";
			PreparedStatement statement = connection.prepareStatement(update);
			statement.setString(1, evento.getNome());
			statement.setString(2, evento.getTipo());
			statement.setString(3, evento.getDurata());
			statement.setString(4, evento.getDescrizione());
			statement.setDate(5, evento.getDataEvento());
			statement.setInt(6, evento.getId());

			statement.executeUpdate();
		} catch (SQLException e) {

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
			} catch (SQLException e) {
				throw new PersistenceException(e.getMessage());
			}
		}

	}

	@Override
	public void delete(Evento evento) {
		Connection connection = this.dataSource.getConnection();
		try {

			String delete = "delete FROM evento WHERE id = ? ";
			PreparedStatement statement = connection.prepareStatement(delete);
			statement.setInt(1, evento.getId());
			connection.setAutoCommit(false);
			connection.setTransactionIsolation(Connection.TRANSACTION_SERIALIZABLE);
			statement.executeUpdate();
			connection.commit();

			System.out.println("delete " + evento);

		} catch (SQLException e) {
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
	public LinkedList<Evento> eventiPerData(Date dataEvento) {
		Connection connection = this.dataSource.getConnection();
		LinkedList<Evento> eventi = new LinkedList<>();
		Evento evento = null;
		
		try {
			String query = "SELECT * from evento WHERE dataEvento = ?";
			PreparedStatement statement = connection.prepareStatement(query);
			
			statement.setDate(1, dataEvento);
			
			ResultSet result = statement.executeQuery();
			while(result.next()) {
				evento = new Evento();
				evento.setDataEvento(result.getDate("dataEvento"));
				evento.setDescrizione(result.getString("descrizione"));
				evento.setDurata(result.getString("durata"));
				evento.setId(result.getInt("id"));
				evento.setNome(result.getString("nomeEvento"));
				evento.setTipo(result.getString("tipo"));
				
				eventi.add(evento);
			}
			
		} catch (Exception e) {
			throw new PersistenceException(e.getMessage());
		}finally {
			try {
				connection.close();
			} catch (SQLException e) {
				throw new PersistenceException(e.getMessage());
			}
		}
		
		return eventi;
	}

}
