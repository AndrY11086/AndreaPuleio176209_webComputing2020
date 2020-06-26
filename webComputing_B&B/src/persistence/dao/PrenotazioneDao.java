package persistence.dao;

import java.util.LinkedList;
import java.util.List;

import model.Prenotazione;

public interface PrenotazioneDao {

	public void save(Prenotazione prenotazione);  // Create
	public Prenotazione findByPrimaryKey(int id);     // Retrieve
	public LinkedList<Prenotazione> findAll();       
	public void update(Prenotazione prenotazione); //Update
	public void delete(Prenotazione prenotazione); //Delete	
	public LinkedList<Prenotazione> findByEmail(String email);
}
