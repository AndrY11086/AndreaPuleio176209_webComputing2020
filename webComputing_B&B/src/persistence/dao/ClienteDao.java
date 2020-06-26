package persistence.dao;

import java.util.List;

import model.Cliente;

public interface ClienteDao {

	public void save(Cliente cliente);  // Create
	public Cliente findByPrimaryKey(String email);// Retrieve
	public List<Cliente> findAll();       
	public void update(Cliente cliente); //Update
	public void delete(Cliente cliente); //Delete
	public void setPassword(Cliente cliente);//setPassword
}
