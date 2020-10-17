package ma.ensa.ArticlePub.domain;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Evaluation {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long id;
	
	private String genre;
	
	private String[] modification;
	
	private String typeModification;
	
	private Date dateDEvalautionn;
	
    private int note;
	
	private String commentaire;
	
	private boolean accepte;
	
	@ManyToOne(fetch=FetchType.LAZY,cascade=CascadeType.REFRESH)
	@JoinColumn(name="refere",updatable=false,nullable=false)
	@JsonIgnore
	private Refere refere;

	@ManyToOne(fetch=FetchType.LAZY,cascade=CascadeType.REFRESH)
	@JoinColumn(name="controle",updatable=false,nullable=false)
	@JsonIgnore
	private Controle controle;
	
	Evaluation(){
		
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getGenre() {
		return genre;
	}

	public void setGenre(String genre) {
		this.genre = genre;
	}

	public Refere getRefere() {
		return refere;
	}

	public void setRefere(Refere refere) {
		this.refere = refere;
	}

	public Controle getControle() {
		return controle;
	}

	public void setControle(Controle controle) {
		this.controle = controle;
	}

	public Date getDateDEvalautionn() {
		return dateDEvalautionn;
	}

	public void setDateDEvalautionn(Date dateDEvalautionn) {
		this.dateDEvalautionn = dateDEvalautionn;
	}

	public int getNote() {
		return note;
	}

	public void setNote(int note) {
		this.note = note;
	}

	public String getCommentaire() {
		return commentaire;
	}

	public void setCommentaire(String commentaire) {
		this.commentaire = commentaire;
	}

	public boolean isAccepte() {
		return accepte;
	}

	public void setAccepte(boolean accepte) {
		this.accepte = accepte;
	}

	public String[] getModification() {
		return modification;
	}

	public void setModification(String[] modification) {
		this.modification = modification;
	}

	public String getTypeModification() {
		return typeModification;
	}

	public void setTypeModification(String typeModification) {
		this.typeModification = typeModification;
	}
	
	 
	
	

}
