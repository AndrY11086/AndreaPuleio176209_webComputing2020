package persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import model.Cliente;
import model.Recensione;
import persistence.dao.RecensioniDao;

public class RecensioniDaoJDBC implements RecensioniDao {

	private DataSource dataSource;
	
	public RecensioniDaoJDBC(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	@Override
	public void save(Recensione recensione) {
		Connection connection = dataSource.getConnection();
		
		try {
			int id = IDBroker.getId(connection);
			recensione.setId(id);
			
			String insert = "insert into Recensione(commento,cliente,voto,id, titolo, data,sesso) values(?,?,?,?,?,?,?)";
			PreparedStatement statement = connection.prepareStatement(insert);
			statement.setString(1, recensione.getCommento());
			statement.setString(2, recensione.getCliente());
			statement.setInt(3, recensione.getVoto());
			statement.setInt(4, recensione.getId());
			statement.setString(5, recensione.getTitolo());
			statement.setString(6, recensione.getData());
			statement.setString(7, recensione.getSesso());
			
			statement.executeUpdate();
		} catch (Exception e) {
			System.out.println("catch: " + e.getMessage());
			if(connection != null) {
				try {
					connection.rollback();
				} catch (Exception e2) {
					throw new PersistenceException(e2.getMessage());
				}
			}
			throw new PersistenceException(e.getMessage());
		}finally {
			try {
				connection.close();
			} catch (SQLException e) {
				throw new PersistenceException(e.getMessage());
			}
		}
	}

	@Override
	public Recensione findByPrimaryKey(int id) {
		Connection connection = dataSource.getConnection();
		Recensione rec = null;
		
		try {
			String query = "select * from recensione where id = ?";
			PreparedStatement statement = connection.prepareStatement(query);
			
			statement.setInt(1, id);
			ResultSet result = statement.executeQuery();
			if(result.next()) {
				rec = new Recensione();
				rec.setId(result.getInt("id"));
				rec.setCommento(result.getString("commento"));
				rec.setSesso(result.getString("sesso"));
				rec.setCliente(result.getString("cliente"));
				rec.setVoto(result.getInt("voto"));
				rec.setTitolo(result.getString("titolo"));
				rec.setData(result.getString("data"));
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
		
		return rec;
	}

	@Override
	public List<Recensione> findAll() {
		Connection connection = dataSource.getConnection();
		LinkedList<Recensione> recensioni = new LinkedList<>();
		try {
			Recensione rec;
			String query = "select * from recensione";
			PreparedStatement statement = connection.prepareStatement(query);
			
			ResultSet result = statement.executeQuery();
			while(result.next()) {
				rec = findByPrimaryKey(result.getInt("id"));
				recensioni.addFirst(rec);
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
		return recensioni;
	}

	@Override
	public void delete(Recensione recensione) {
		Connection connection = dataSource.getConnection();
		try {
			String delete = "delete from recensione where id = ?";
			PreparedStatement statement = connection.prepareStatement(delete);
			statement.setInt(1, recensione.getId());
			connection.setAutoCommit(false);
			connection.setTransactionIsolation(Connection.TRANSACTION_SERIALIZABLE);
			statement.executeUpdate();
			connection.commit();
			
			System.out.println("delete " + recensione);
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
