package model;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import javafx.scene.image.Image;

public class Fotografia {

	/*
	 * Amazon S3:
	 * 			email: 
	 * 			password: 
	 * 
	 */
	
	private int idFoto;
	private File fotografia;

	private FileInputStream inputStreamFoto;
	private byte[] bytea;
	private ByteArrayOutputStream baos;

	private String title;
	private String description;
	private String path;

	public Fotografia() {

	}

	public Fotografia(String foto, String titolo, String descrizione) {
		this.path = foto;
		this.fotografia = new File(path);

		this.baos = new ByteArrayOutputStream();
		byte[] buffer = new byte[1024];

		try {
			this.inputStreamFoto = new FileInputStream(fotografia);

			for (int readNum; (readNum = inputStreamFoto.read(buffer)) != -1;) {
				baos.write(buffer, 0, readNum);
			}
		} catch (FileNotFoundException e) {
			System.out.println("Errore nel costruttore di Fotografia");
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		this.bytea = baos.toByteArray();

		this.description = descrizione;
		this.title = titolo;

	}

	public int getIdFoto() {
		return idFoto;
	}

	public void setIdFoto(int idFoto) {
		this.idFoto = idFoto;
	}

	public File getFotografia() {
		return fotografia;
	}

	public void setFotografia(File fotografia) {
		this.fotografia = fotografia;
	}

	public FileInputStream getInputStreamFoto() {
		return inputStreamFoto;
	}

	public void setInputStreamFoto(FileInputStream inputStreamFoto) {
		this.inputStreamFoto = inputStreamFoto;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public byte[] getBytea() {
		return bytea;
	}

	public void setBytea(byte[] bytea) {
		this.bytea = bytea;
	}

	public ByteArrayOutputStream getBaos() {
		return baos;
	}

	public void setBaos(ByteArrayOutputStream baos) {
		this.baos = baos;
	}

}
