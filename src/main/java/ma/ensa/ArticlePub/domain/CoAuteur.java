package ma.ensa.ArticlePub.domain;


import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class CoAuteur {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long id;

	@OneToOne(fetch=FetchType.LAZY,cascade=CascadeType.ALL)
	@JoinColumn(name="coAuteur",nullable=false)
	@JsonIgnore
	private User coAuteur;
	
	public CoAuteur() {
		
	}
	public CoAuteur(User coAuteur) {
		this.coAuteur=coAuteur;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}
	public User getCoAuteur() {
		return coAuteur;
	}
	public void setCoAuteur(User coAuteur) {
		this.coAuteur = coAuteur;
	}

   
	

}
