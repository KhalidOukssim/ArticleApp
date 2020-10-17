package ma.ensa.ArticlePub.domain;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
public class Controle {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long id;
	
	private float note;
	
	private int sommeVotes;
	
	private int sommeVoters;
	
	@OneToOne(fetch=FetchType.LAZY,cascade=CascadeType.ALL)
	@JoinColumn(name="article",nullable=false)
	@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"}) 
	private Article article;
	
	@OneToMany(cascade=CascadeType.ALL,fetch=FetchType.EAGER,mappedBy="controle")
	private List<Refere> referes;
	
	@OneToMany(cascade=CascadeType.ALL,fetch=FetchType.LAZY,mappedBy="controle")
	private List<Evaluation> evaluation;
	

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Article getArticle() {
		return article;
	}

	public void setArticle(Article article) {
		this.article = article;
	}

	public List<Refere> getReferes() {
		return referes;
	}

	public void setReferes(List<Refere> referes) {
		this.referes = referes;
	}

	public float getNote() {
		return note;
	}

	public void setNote(float note) {
		this.note = note;
	}

	public int getSommeVotes() {
		return sommeVotes;
	}

	public void setSommeVotes(int sommeVotes) {
		this.sommeVotes = sommeVotes;
	}

	public int getSommeVoters() {
		return sommeVoters;
	}

	public void setSommeVoters(int sommeVoters) {
		this.sommeVoters = sommeVoters;
	}

	public List<Evaluation> getEvaluation() {
		return evaluation;
	}

	public void setEvaluation(List<Evaluation> evaluation) {
		this.evaluation = evaluation;
	}
	
	
	

}
