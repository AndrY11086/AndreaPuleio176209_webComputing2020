package persistence.dao;

import java.util.LinkedList;
import java.util.List;

import model.Evento;

public interface EventoDao {

	public void save(Evento evento);  // Create
	public Evento findByPrimaryKey(int id);// Retrieve
	public List<Evento> findAll();       
	public void update(Evento evento); //Update
	void delete(Evento evento);//Delete
	public LinkedList<Evento> eventiPerData(java.sql.Date dataEvento);
	
}
