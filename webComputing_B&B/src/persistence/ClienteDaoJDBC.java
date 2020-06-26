package persistence;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import model.Cliente;
import persistence.dao.ClienteDao;

public class ClienteDaoJDBC implements ClienteDao {

	private DataSource dataSource;

	public ClienteDaoJDBC(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	@Override
	public void save(Cliente cliente) {
		Connection connection = this.dataSource.getConnection();
		try {

			String insert = "insert into cliente(email, nome, cognome, city, numeroTelefono, sesso) values (?,?,?,?,?,?)";
			PreparedStatement statement = connection.prepareStatement(insert);
			statement.setString(1, cliente.getIndirizzoEmail());
			statement.setString(2, cliente.getNome());
			statement.setString(3, cliente.getCognome());
			statement.setString(4, cliente.getCity());
			statement.setString(5, cliente.getNumeroTelefono());
			statement.setString(6, cliente.getSesso());

			statement.executeUpdate();
		} catch (SQLException e) {
			System.out.println("save: " + e.getMessage());
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
	public Cliente findByPrimaryKey(String email) {
		Connection connection = this.dataSource.getConnection();
		Cliente cliente = null;
		try {
			PreparedStatement statement;
			String query = "select * from cliente where email = ?";
			statement = connection.prepareStatement(query);
			statement.setString(1, email);
			ResultSet result = statement.executeQuery();

			if (result.next()) {
				cliente = new Cliente();
				cliente.setIndirizzoEmail(result.getString("email"));
				cliente.setNome(result.getString("nome"));
				cliente.setCognome(result.getString("cognome"));
				cliente.setCity(result.getString("city"));
				cliente.setNumeroTelefono(result.getString("numeroTelefono"));
				cliente.setSesso(result.getString("sesso"));
				cliente.setPassword(result.getString("password"));
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
		return cliente;
	}

	@Override
	public List<Cliente> findAll() {
		Connection connection = this.dataSource.getConnection();
		List<Cliente> clienti = new LinkedList();
		try {
			Cliente cliente;
			PreparedStatement statement;
			String query = "select * from cliente";
			statement = connection.prepareStatement(query);
			ResultSet result = statement.executeQuery();
			while (result.next()) {
				cliente = findByPrimaryKey(result.getString("email"));
				clienti.add(cliente);
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
		return clienti;
	}

	@Override
	public void update(Cliente cliente) {
		Connection connection = this.dataSource.getConnection();
		try {
			String update = "update cliente SET nome = ?, cognome = ?, city = ?, numeroTelefono = ?, password = ? WHERE email = ?";
			PreparedStatement statement = connection.prepareStatement(update);
			statement.setString(1, cliente.getNome());
			statement.setString(2, cliente.getCognome());
			statement.setString(3, cliente.getCity());
			statement.setString(4, cliente.getNumeroTelefono());
			statement.setString(5, cliente.getPassword());
			statement.setString(6, cliente.getIndirizzoEmail());

			statement.executeUpdate();
			
			System.out.println("update eseguita");
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
	public void delete(Cliente cliente) {
		Connection connection = this.dataSource.getConnection();
		try {
			String delete = "delete FROM cliente WHERE email = ? ";
			PreparedStatement statement = connection.prepareStatement(delete);
			statement.setString(1, cliente.getIndirizzoEmail());

			connection.setAutoCommit(false);
			connection.setTransactionIsolation(Connection.TRANSACTION_SERIALIZABLE);
			statement.executeUpdate();
			connection.commit();
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

	public void setPassword(Cliente cliente) {
		Connection connection = this.dataSource.getConnection();

		
		try {
			String update = "update cliente SET password = ? WHERE email = ?";
			PreparedStatement statement = connection.prepareStatement(update);
			statement.setString(1, cliente.getPassword());
			statement.setString(2, cliente.getIndirizzoEmail());
			statement.executeUpdate();

		} catch (Exception e) {
			
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

}
