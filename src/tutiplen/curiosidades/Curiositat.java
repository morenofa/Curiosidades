package tutiplen.curiosidades;

import java.io.Serializable;

public class Curiositat implements Serializable {

	private static final long serialVersionUID = 1L;
	
	//Atributs
	private int id;
	private String titol;
	private String text;
	
	//Contructors
	public Curiositat(int id, String titol, String text) {
		super();
		this.id = id;
		this.titol = titol;
		this.text = text;
	}

	//Getters && Setters
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitol() {
		return titol;
	}

	public void setTitol(String titol) {
		this.titol = titol;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	//To String
	@Override
	public String toString() {
		return "Curiosidad [id=" + id + ", titol=" + titol + ", text=" + text
				+ "]";
	}

}
