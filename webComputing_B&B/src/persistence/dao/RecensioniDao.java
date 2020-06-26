package persistence.dao;

import java.util.List;

import model.Recensione;

public interface RecensioniDao {
	public void save(Recensione recensione);  // Create
	public Recensione findByPrimaryKey(int id);// Retrieve
	public List<Recensione> findAll();
	public void delete(Recensione recensione); //Delete
}
