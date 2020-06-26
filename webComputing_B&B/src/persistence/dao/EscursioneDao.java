package persistence.dao;

import java.util.LinkedList;

import model.Escursione;
import model.Evento;

public interface EscursioneDao {

	public void save(Escursione escursione);  // Create
	public Escursione findByPrimaryKey(int id);// Retrieve
	public LinkedList<Escursione> findAll();
}
