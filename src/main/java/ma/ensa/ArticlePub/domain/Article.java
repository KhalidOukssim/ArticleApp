package ma.ensa.ArticlePub.domain;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Article {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long id;
	
	/*
	 Titre  de l’article, coordonnées de l’auteur correspondant, Co-auteurs, Affiliations, 
       Résumé, Mots‐clés et le contenu sous forme de manuscrit de l’article à publier.
	*/
	
	private String titre;
	private String affiliation;
	@Lob
    @Column(length=1000000)
	private String resume;
	@Lob
    @Column(length=1000000)
	private String contenu;
	private Long coAuteursId[];
	
	@ManyToOne(fetch=FetchType.LAZY,cascade=CascadeType.ALL)
	@JoinColumn(name="auteur")
	@JsonIgnore
	private User auteur;
	
	@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "ARTICLE_COAUTEUR", joinColumns = {
            @JoinColumn(name = "ARTICLE_ID") }, inverseJoinColumns = {
            @JoinColumn(name = "COAUTEUR_ID") })
	private List<CoAuteur> coAuteur;
	
	@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "ARTICLE_MOTCLE", joinColumns = {
            @JoinColumn(name = "ARTICLE_ID") }, inverseJoinColumns = {
            @JoinColumn(name = "MOTCLE_ID") })
	private List<MotCle> motcles;
	
	@OneToOne(cascade=CascadeType.ALL,mappedBy="article")
	@JsonIgnore
	private Etat etatArticle;
	
	@OneToOne(fetch=FetchType.LAZY,cascade=CascadeType.ALL,mappedBy="article")
	@JsonIgnore
	private Controle controleArticle;
	
	@ManyToOne(fetch=FetchType.EAGER,cascade=CascadeType.REFRESH)
	@JoinColumn(name="revue",updatable=false,nullable=false)
	@JsonIgnore
	private Revue revue;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getTitre() {
		return titre;
	}

	public void setTitre(String titre) {
		this.titre = titre;
	}

	public String getAffiliation() {
		return affiliation;
	}

	public void setAffiliation(String affiliation) {
		this.affiliation = affiliation;
	}

	public String getResume() {
		return resume;
	}

	public void setResume(String resume) {
		this.resume = resume;
	}

	public String getContenu() {
		return contenu;
	}

	public void setContenu(String contenu) {
		this.contenu = contenu;
	}

	public Long[] getCoAuteursId() {
		return coAuteursId;
	}

	public void setCoAuteursId(Long[] coAuteursId) {
		this.coAuteursId = coAuteursId;
	}

	public User getAuteur() {
		return auteur;
	}

	public void setAuteur(User auteur) {
		this.auteur = auteur;
	}

	public List<CoAuteur> getCoAuteur() {
		return coAuteur;
	}

	public void setCoAuteur(List<CoAuteur> coAuteur) {
		this.coAuteur = coAuteur;
	}

	public List<MotCle> getMotcles() {
		return motcles;
	}

	public void setMotcles(List<MotCle> motcles) {
		this.motcles = motcles;
	}

	public Etat getEtatArticle() {
		return etatArticle;
	}

	public void setEtatArticle(Etat etatArticle) {
		this.etatArticle = etatArticle;
	}

	public Controle getControleArticle() {
		return controleArticle;
	}

	public void setControleArticle(Controle controleArticle) {
		this.controleArticle = controleArticle;
	}

	public Revue getRevue() {
		return revue;
	}

	public void setRevue(Revue revue) {
		this.revue = revue;
	}

	
	
	

	

}
