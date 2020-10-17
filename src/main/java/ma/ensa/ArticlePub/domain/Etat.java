package ma.ensa.ArticlePub.domain;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Etat {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long id;
	
	private String etat;
	
	@OneToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="article",nullable=false)
	@JsonIgnore
	private Article article;
	
   public Etat() {
	   
   }
	public Etat(String etat, Article article) {
		this.article=article;
		this.etat=etat;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getEtat() {
		return etat;
	}

	public void setEtat(String etat) {
		this.etat = etat;
	}

	public Article getArticle() {
		return article;
	}

	public void setArticle(Article article) {
		this.article = article;
	}
	
	
	
	
	

}
