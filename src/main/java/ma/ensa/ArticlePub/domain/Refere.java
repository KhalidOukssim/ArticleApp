package ma.ensa.ArticlePub.domain;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Refere {

	@Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
	private long id;
	
	
	@OneToOne(fetch=FetchType.LAZY,cascade=CascadeType.ALL)
	@JoinColumn(name="refere",nullable=false)
	@JsonIgnore
	private User refere;
	
	@ManyToOne(fetch=FetchType.EAGER,cascade=CascadeType.REFRESH)
	@JoinColumn(name="controle",updatable=false,nullable=false)
	@JsonIgnore
	private Controle controle;
	
	@OneToMany(cascade=CascadeType.ALL,fetch=FetchType.LAZY,mappedBy="refere")
	private List<Evaluation> evaluation;
	
	public Refere() {
	}
	public Refere(User user) {
		this.refere=user;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}


	public Controle getControle() {
		return controle;
	}

	public void setControle(Controle controle) {
		this.controle = controle;
	}
	public User getRefere() {
		return refere;
	}
	public void setRefere(User refere) {
		this.refere = refere;
	}
	
	
	
	
	
	
	

}
