package persistence.dao;

import java.util.List;

import model.Fotografia;

public interface FotografiaDao {

	public void save(Fotografia foto);  // Create
	public Fotografia findByPrimaryKey(int id);     // Retrieve
	public List<Fotografia> findAll();       
	public void update(Fotografia foto); //Update
	public void delete(Fotografia foto); //Delete	
	
}
