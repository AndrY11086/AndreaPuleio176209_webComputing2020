package model;

import java.util.Date;

public class Cliente {

	private String nome;
	private String cognome;
	private String city;
	private String numeroTelefono;
	private String sesso;
	private String indirizzoEmail;
	private String password;
	
	public Cliente() {
		
	}
	
	public Cliente(String name, String surname, String nTelefono, String gender, String city, String email, String pass) {
		
		this.nome = name;
		this.cognome = surname;
		this.city = city;
		this.numeroTelefono = nTelefono;
		this.sesso = gender;
		this.indirizzoEmail = email;
		this.password = pass;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCognome() {
		return cognome;
	}

	public void setCognome(String cognome) {
		this.cognome = cognome;
	}

	public String getNumeroTelefono() {
		return numeroTelefono;
	}

	public void setNumeroTelefono(String numeroTelefono) {
		this.numeroTelefono = numeroTelefono;
	}

	public String getSesso() {
		return sesso;
	}

	public void setSesso(String sesso) {
		this.sesso = sesso;
	}

	public String getIndirizzoEmail() {
		return indirizzoEmail;
	}

	public void setIndirizzoEmail(String indirizzoEmail) {
		this.indirizzoEmail = indirizzoEmail;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}
}
