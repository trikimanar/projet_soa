package com.example.model;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Cours implements Serializable {
	private int id;
	private String titre;
	private String enseignant;

	public Cours() {
	}

	public Cours(int id, String titre, String enseignant) {
		this.id = id;
		this.titre = titre;
		this.enseignant = enseignant;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitre() {
		return titre;
	}

	public void setTitre(String titre) {
		this.titre = titre;
	}

	public String getEnseignant() {
		return enseignant;
	}

	public void setEnseignant(String enseignant) {
		this.enseignant = enseignant;
	}
}
