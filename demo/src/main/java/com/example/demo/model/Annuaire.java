package com.example.demo.model;
import javax.persistence.*;
@Entity
@Table(name = "annuaires")
public class Annuaire {
	@Id
	@GeneratedValue(strategy= GenerationType.AUTO)
	private long id;
	@Column(name = "nom")
	private String nom;
	@Column(name = "prenom")
	private String prenom;
	@Column(name = "login")
	private String login;
	@Column(name= "password")
	private String password;
	@Column(name="date_arrive")
	private String date_arrive;
	@Column(name= "adressemail")
	private String adressemail;
	public Annuaire(){
		
	}
	public Annuaire(long id, String nom, String prenom, String login, String password, String date_arrive,
			String adressemail) {
		super();
		this.id = id;
		this.nom = nom;
		this.prenom = prenom;
		this.login = login;
		this.password = password;
		this.date_arrive = date_arrive;
		this.adressemail = adressemail;
	}
	public long getId() {
		return id;
	}
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public String getPrenom() {
		return prenom;
	}
	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}
	public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		this.login = login;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getDate_arrive() {
		return date_arrive;
	}
	public void setDate_arrive(String date_arrive) {
		this.date_arrive = date_arrive;
	}
	public String getAdressemail() {
		return adressemail;
	}
	public void setAdressemail(String adressemail) {
		this.adressemail = adressemail;
	}
}
