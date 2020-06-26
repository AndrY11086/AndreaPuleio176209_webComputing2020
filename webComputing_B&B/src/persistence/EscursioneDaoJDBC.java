package persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;

import model.Escursione;
import persistence.dao.EscursioneDao;

public class EscursioneDaoJDBC implements EscursioneDao{

	private DataSource dataSource;
	
	public EscursioneDaoJDBC(DataSource dataSource) {
		this.dataSource = dataSource;
	}
	
	@Override
	public void save(Escursione escursione) {

		Connection connection = dataSource.getConnection();
		try {
			String insert ="insert into escursione(id, cliente, emailCliente, telefonoCliente,nomeEscursione, prezzo, numeroPersone) values (?,?,?,?,?,?,?)";
			PreparedStatement statement = connection.prepareStatement(insert);
			
			int id = IDBroker.getId(connection);
			escursione.setId(id);
			
			statement.setInt(1, escursione.getId());
			statement.setString(2, escursione.getCliente());
			statement.setString(3, escursione.getEmailCliente());
			statement.setString(4, escursione.getTelefonoCliente());
			statement.setString(5, escursione.getNome());
			statement.setInt(6, escursione.getPrezzo());
			statement.setInt(7, escursione.getnPersone());
			
			statement.executeUpdate();
			
			System.out.println("Escursione Stored");
			
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

	@Override
	public Escursione findByPrimaryKey(int id) {
		
		Connection connection = dataSource.getConnection();
		Escursione esc = null;
		try {
			String query = "select * from escursione where id = ?";
			PreparedStatement statement = connection.prepareStatement(query);
			
			statement.setInt(1, id);
			ResultSet result = statement.executeQuery();
			
			while(result.next()) {
				esc = new Escursione();
				esc.setId(result.getInt("id"));
				esc.setCliente(result.getString("cliente"));
				esc.setNome(result.getString("nomeEscursione"));
				esc.setPrezzo(result.getInt("prezzo"));
				esc.setTelefonoCliente(result.getString("telefonoCliente"));
				esc.setEmailCliente(result.getString("emailCliente"));
				esc.setnPersone(result.getInt("numeroPersone"));
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
		
		return esc;
	}

	@Override
	public LinkedList<Escursione> findAll() {
		Connection connection = dataSource.getConnection();
		LinkedList<Escursione> escursioni = new LinkedList<>();
		
		try {
			String query = "select * from escursione";
			Escursione escursione;
			PreparedStatement statement = connection.prepareStatement(query);
			ResultSet result = statement.executeQuery();
			
			while(result.next()) {
				escursione = findByPrimaryKey(result.getInt("id"));
				escursioni.addFirst(escursione);
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
		
		return escursioni;
	}

}
