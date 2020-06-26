package persistence;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;

import javafx.scene.image.Image;
import model.Fotografia;
import persistence.dao.FotografiaDao;

public class FotografiaDaoJDBC implements FotografiaDao {

	private DataSource dataSource;
	
	public FotografiaDaoJDBC(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	@Override
	public void save(Fotografia foto) {
		
		Connection connection = this.dataSource.getConnection();
		
		try {
			String insert = "insert into fotografia(id, titolo, image, descrizione, path) values(?,?,?,?,?)";
			//PreparedStatement statement = connection.prepareStatement(insert, Statement.RETURN_GENERATED_KEYS);
			PreparedStatement statement = connection.prepareStatement(insert);
			
			int id = IDBroker.getId(connection);
			foto.setIdFoto(id);
			
			statement.setInt(1, foto.getIdFoto());
			statement.setString(2, foto.getTitle());
			statement.setBytes(3, foto.getBytea());
			statement.setString(4, foto.getDescription());
			statement.setString(5, foto.getPath());
			statement.executeUpdate();
			
			/*ResultSet rs = statement.getGeneratedKeys();
			while(rs.next()) {
				int id = rs.getInt(1);
				foto.setIdFoto(id);
			}*/
			
			System.out.println("Image Stored");
			
		} catch (Exception e) {
			System.out.println("save-fotografia " + e.getMessage());
			
			if (connection != null) {
				try {
					connection.rollback();
				} catch (Exception e2) {
					throw new PersistenceException(e2.getMessage());
				}
			}
			
		}finally {
			try {
				connection.close();
			} catch (Exception e) {
				throw new PersistenceException(e.getMessage());
			}
		}
		
	}

	@Override
	public Fotografia findByPrimaryKey(int id) {
		Connection connection = this.dataSource.getConnection();
		Fotografia foto = null;
		
		try {
			String query = "select * from fotografia where id= ?";
			PreparedStatement statement = connection.prepareStatement(query);
			
			
			statement.setInt(1, id);
			ResultSet result = statement.executeQuery();
			
			if(result.next()) {
				
				foto = new Fotografia();
				foto.setIdFoto(result.getInt("id"));
				
				//Blob image = result.getBlob(2);
				//InputStream binaryStream = image.getBinaryStream(1, image.length());
				byte[] image = result.getBytes("image");
				foto.setBytea(image);
				foto.setPath(result.getString("path"));
				
				foto.setTitle(result.getString("titolo"));
				foto.setDescription(result.getString("descrizione"));
			}
			
		} catch (Exception e) {
			System.out.println(e.getMessage() + " findyPrimaryKey(questo)");
			throw new PersistenceException(e.getMessage());
		}finally {
			try {
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return foto;
	}

	@Override
	public void update(Fotografia foto) {
		Connection connection = this.dataSource.getConnection();
		
		try {
			String update = "update fotografia SET image = ?, titolo = ?, descrizione = ?, path = ? WHERE id = ?";
			PreparedStatement statement = connection.prepareStatement(update);
			statement.setBinaryStream(1, foto.getInputStreamFoto());
			statement.setInt(2, foto.getIdFoto()); //probabilmente non restituisce niente AGGIUSTARE
			statement.setString(3, foto.getTitle());
			statement.setString(4, foto.getDescription());
			statement.setString(5, foto.getPath());
			
			statement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			
			if (connection != null) {
				try {
					connection.rollback();
				} catch (Exception e2) {
					throw new PersistenceException(e2.getMessage());
				}
			}
			
		}finally {
			try {
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	
	@Override
	public void delete(Fotografia foto) {
		Connection connection = this.dataSource.getConnection();
		
		try {
			String delete = "delete FROM fotografia WHERE id = ?";
			PreparedStatement statement = connection.prepareStatement(delete);
			
			statement.setInt(1,foto.getIdFoto());
			connection.setAutoCommit(false);
			connection.setTransactionIsolation(Connection.TRANSACTION_SERIALIZABLE);
			statement.executeUpdate();
			connection.commit();
			
			System.out.println("Image deleted");
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}finally{
			try {
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	@Override
	public List<Fotografia> findAll() {
		Connection connection = this.dataSource.getConnection();
		List<Fotografia> photos = new LinkedList<>();
		
		try {
			Fotografia foto;
			String query = "select * from fotografia";
			PreparedStatement statement = connection.prepareStatement(query);
			
			ResultSet result = statement.executeQuery();
			
			while(result.next()) {
				foto = findByPrimaryKey(result.getInt("id"));
				photos.add(foto);
			}
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}finally {
			try {
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return photos;
	}

}
