package model;

import java.util.Date;
import java.util.LinkedList;

public class Prenotazione {

	private int idPrenotazione;
	private Cliente ospite;
	private int numeroPersone;
	private java.sql.Date arrivo;
	private java.sql.Date partenza;
	private float prezzo;
	private String pagamento;
	private int nNotti;
	private LinkedList<Stanza> stanzeOccupate;

	private String nome;
	private String email;

	public Prenotazione() {

	}

	public Prenotazione(Cliente client, int nPax, java.sql.Date firstDay, java.sql.Date lastDay, float price,
			String state, int notti) {

		this.ospite = client;
		this.numeroPersone = nPax;
		this.arrivo = firstDay;
		this.partenza = lastDay;
		this.prezzo = price;
		this.pagamento = state;
		this.nNotti = notti;
	}

	public int getIdPrenotazione() {
		return idPrenotazione;
	}

	public void setIdPrenotazione(int idPrenotazione) {
		this.idPrenotazione = idPrenotazione;
	}

	public Cliente getOspite() {
		return ospite;
	}

	public void setOspite(Cliente ospite) {
		this.ospite = ospite;
	}

	public int getNumeroPersone() {
		return numeroPersone;
	}

	public void setNumeroPersone(int numeroPersone) {
		this.numeroPersone = numeroPersone;
	}

	public java.sql.Date getArrivo() {
		return arrivo;
	}

	public void setArrivo(java.sql.Date arrivo) {
		this.arrivo = arrivo;
	}

	public java.sql.Date getPartenza() {
		return partenza;
	}

	public void setPartenza(java.sql.Date partenza) {
		this.partenza = partenza;
	}

	public float getPrezzo() {
		return prezzo;
	}

	public void setPrezzo(float prezzo) {
		this.prezzo = prezzo;
	}

	public String getPagamento() {
		return pagamento;
	}

	public void setPagamento(String pagamento) {
		this.pagamento = pagamento;
	}

	public LinkedList<Stanza> getStanzeOccupate() {
		return stanzeOccupate;
	}

	public void setStanzeOccupate(LinkedList<Stanza> stanzeOccupate) {
		this.stanzeOccupate = stanzeOccupate;
	}

	public int getnNotti() {
		return nNotti;
	}

	public void setnNotti(int nNotti) {
		this.nNotti = nNotti;
	}

	public void setNomeOspite(String nome) {
		this.nome = nome;
	}

	public String getNomeOspite() {
		return nome;
	}

	public void setEmailOspite(String email) {
		this.email = email;
	}

	public String getEmailOspite() {
		return email;
	}
	
	@Override
	public String toString() {
		return idPrenotazione + " " + nome;
	}
}
