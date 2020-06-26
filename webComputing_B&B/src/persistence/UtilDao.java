package persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.mysql.cj.jdbc.MysqlDataSource;

public class UtilDao {

	
	private DataSource dataSource;
	
	public UtilDao(DataSource dataSource) {
		this.dataSource = dataSource;
	}
	
	public void dropDatabase() throws SQLException {
		
		Connection connection = dataSource.getConnection();
		
		try {
			String delete = "DROP table IF EXISTS cliente,prenotazione,fotografia,evento,recensione, escursione;" + "drop SEQUENCE if exists sequenza_id;";
			
			PreparedStatement statement = connection.prepareStatement(delete);
			statement.executeUpdate();

			System.out.println("Executed drop database");
			
		} catch (Exception e) {
			throw new PersistenceException("eccezione del DROP " + e.getMessage());
		}finally {
			try {
				connection.close();
				
				System.out.println("connessione chiusa");
			} catch (Exception e2) {
				throw new PersistenceException("eccezione finally drop" + e2.getMessage());
			}
		}
	}
	
	public void createDatabase() {
		
		Connection connection = dataSource.getConnection();
		
		try {
			
			/*String create = "create table cliente (email varchar(255) primary key, nome varchar(255), cognome varchar(255), city varchar(255), sesso varchar(1), numeroTelefono varchar(13), password varchar(50));";
			PreparedStatement statement = connection.prepareStatement(create);
			statement.executeUpdate();
			
			create = "create table prenotazione (idPrenotazione int primary key AUTO_INCREMENT,\n email varchar(255),\n nomeCliente varchar(255),\n numeroPersone varchar(1),\n dataArrivo DATE,\n dataPartenza DATE,\n prezzo FLOAT,\npagamento varchar(255), numeroNotti int);";
			statement = connection.prepareStatement(create);
			statement.executeUpdate();
			
			create = "create table evento (id int primary key AUTO_INCREMENT,\n nomeEvento varchar(255),\n tipo varchar(255),\n durata varchar(255),\n descrizione varchar(255),\n dataEvento DATE);";
			statement = connection.prepareStatement(create);
			statement.executeUpdate();
			
			create ="create table fotografia (id int primary key AUTO_INCREMENT,\n image LONGBLOB,\n titolo varchar(255),\n descrizione varchar(255)\n, path varchar(255));";
			statement = connection.prepareStatement(create);
			statement.executeUpdate();*/
			
			String create = "create SEQUENCE sequenza_id;" +
							"create table cliente (email varchar(255) primary key, nome varchar(255), cognome varchar(255), city varchar(255), sesso varchar(1), numeroTelefono varchar(13), password varchar(50));"+
							"create table prenotazione (idPrenotazione int primary key, email varchar(255), nomeCliente varchar(255), numeroPersone varchar(1), dataArrivo DATE, dataPartenza DATE, prezzo FLOAT,pagamento varchar(255), numeroNotti int);"+
							"create table evento (id int primary key, nomeEvento varchar(255), tipo varchar(255), durata varchar(255), descrizione varchar(255), dataEvento DATE);"+
							//"create table fotografia (id int primary key, image bytea, titolo varchar(255), descrizione varchar(255), path varchar(255));"+
							"create table recensione (id int primary key, commento varchar(255), voto int, cliente varchar(255), titolo varchar(255), data varchar(25), sesso varchar(1));"+
							"create table escursione (id int primary key, cliente varchar(255), emailCliente varchar(255), telefonoCliente varchar(255), nomeEscursione varchar(255), prezzo int, numeroPersone int);";
			
			PreparedStatement statement = connection.prepareStatement(create);
			statement = connection.prepareStatement(create);
			statement.executeUpdate();
			System.out.println("Executed create database");
			
		} catch (Exception e) {
			throw new PersistenceException("eccezione del CREATE " + e.getMessage());
		}finally {
			try {
				connection.close();
				System.out.println("chiusa connessione create");
			} catch (Exception e2) {
				throw new PersistenceException(e2.getMessage());
			}
		}
	}
	
	public void resetDatabase() {
		
		Connection connection = dataSource.getConnection();
		
		try {
			String delete = "delete FROM cliente";
			PreparedStatement statement = connection.prepareStatement(delete);
			statement.executeUpdate();
			
			delete = "delete FROM prenotazione";
			statement = connection.prepareStatement(delete);
			statement.executeUpdate();
			
			delete = "delete FROM evento";
			statement = connection.prepareStatement(delete);
			statement.executeUpdate();
			
			/*delete = "delete FROM fotografia";
			statement = connection.prepareStatement(delete);
			statement.executeUpdate();*/
			
			delete = "delete FROM escursione";
			statement = connection.prepareStatement(delete);
			statement.executeUpdate();
			
			delete = "delete FROM recensione";
			statement = connection.prepareStatement(delete);
			statement.executeUpdate();
			
		} catch (Exception e) {
			throw new PersistenceException("eccezione del reset " + e.getMessage());
		}finally {
			try {
				connection.close();
				System.out.println("chiusa connessione reset");
			} catch (Exception e2) {
				throw new PersistenceException(e2.getMessage());
			}
		}
	}
}
