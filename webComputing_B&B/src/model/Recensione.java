package model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Recensione {

	private String commento;
	private int voto;
	private int id;
	private String cliente;
	private String titolo;
	private String data;
	private String sesso;
	
	public Recensione() {
		
	}
	
	public Recensione(String comment, int vote, String client, String title, String gender) {
		this.commento = comment;
		this.voto = vote;
		this.cliente = client;
		this.titolo = title;
		this.sesso = gender;
		
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss"); 
		LocalDateTime today = LocalDateTime.now();
		System.out.println("today: " + dtf.format(today));
		
		this.data = dtf.format(today);
		
	}

	public String getCommento() {
		return commento;
	}

	public void setCommento(String commento) {
		this.commento = commento;
	}

	public int getVoto() {
		return voto;
	}

	public void setVoto(int voto) {
		this.voto = voto;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCliente() {
		return cliente;
	}

	public void setCliente(String cliente) {
		this.cliente = cliente;
	}

	public String getTitolo() {
		return titolo;
	}

	public void setTitolo(String titolo) {
		this.titolo = titolo;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public String getSesso() {
		return sesso;
	}

	public void setSesso(String sesso) {
		this.sesso = sesso;
	}
	
}
