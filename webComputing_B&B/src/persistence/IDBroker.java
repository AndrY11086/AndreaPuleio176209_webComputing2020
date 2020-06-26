package persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class IDBroker {
	private static final String query = "SELECT nextval('sequenza_id') AS id";

	public static int getId(Connection connection) {
		int id = 0;
		try {
			PreparedStatement statement = connection.prepareStatement(query);
			ResultSet result = statement.executeQuery();
			result.next();
			id = result.getInt("id");

			System.out.println("ID: " + id);

		} catch (SQLException e) {
			throw new PersistenceException(e.getMessage());
		}
		return id;
	}
}
