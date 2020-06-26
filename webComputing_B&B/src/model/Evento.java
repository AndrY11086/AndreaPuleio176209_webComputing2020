package model;

import java.util.Date;

public class Evento {
	
	private int id;
	private String nome;
	private String tipo;
	private String durata;
	private String descrizione;
	private java.sql.Date dataEvento;	
	
	private Cliente cliente;
	
	public Evento() {
		
	}
	
	public Evento(String name, String type, String duration, String description, java.sql.Date eventDate) {
		this.nome = name;
		this.tipo = type;
		this.durata = duration;
		this.descrizione = description;
		this.dataEvento = eventDate;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public String getDurata() {
		return durata;
	}

	public void setDurata(String durata) {
		this.durata = durata;
	}

	public String getDescrizione() {
		return descrizione;
	}

	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}

	public java.sql.Date getDataEvento() {
		return dataEvento;
	}

	public void setDataEvento(java.sql.Date dataEvento) {
		this.dataEvento = dataEvento;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
	
	
}
