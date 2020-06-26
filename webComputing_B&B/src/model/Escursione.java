package model;

public class Escursione {

	private String nome;
	private int prezzo;
	private String cliente;
	private String telefonoCliente;
	private String emailCliente;
	private int nPersone;
	private int id;
	
	public Escursione() {

	}
	
	public Escursione(String name, int price, Cliente client,int nPax) {
		
		this.nome = name;
		this.prezzo = price;
		this.cliente = client.getCognome() + " " +client.getNome();
		this.telefonoCliente = client.getNumeroTelefono();
		this.emailCliente = client.getIndirizzoEmail();
		this.nPersone = nPax;
	}

	public String getTelefonoCliente() {
		return telefonoCliente;
	}

	public void setTelefonoCliente(String telefonoCliente) {
		this.telefonoCliente = telefonoCliente;
	}

	public String getEmailCliente() {
		return emailCliente;
	}

	public void setEmailCliente(String emailCliente) {
		this.emailCliente = emailCliente;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public int getPrezzo() {
		return prezzo;
	}

	public void setPrezzo(int prezzo) {
		this.prezzo = prezzo;
	}


	public String getCliente() {
		return cliente;
	}

	public void setCliente(String cliente) {
		this.cliente = cliente;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getnPersone() {
		return nPersone;
	}

	public void setnPersone(int nPersone) {
		this.nPersone = nPersone;
	}
	
	
}
