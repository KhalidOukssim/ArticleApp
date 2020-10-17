package ma.ensa.ArticlePub.domain;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Revue {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long id;
	
	private String name;
	
	private String type;
	
	private int nmbreArticle;
	
	 @OneToMany(cascade=CascadeType.ALL,fetch=FetchType.EAGER,mappedBy="revue")
	 private List<Article> articles;
	 
	 
	 public long getId() {
			return id;
		}

		public void setId(long id) {
			this.id = id;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public String getType() {
			return type;
		}

		public void setType(String type) {
			this.type = type;
		}

		public int getNmbreArticle() {
			return nmbreArticle;
		}

		public void setNmbreArticle(int nmbreArticle) {
			this.nmbreArticle = nmbreArticle;
		}

		public List<Article> getArticles() {
			return articles;
		}

		public void setArticles(List<Article> articles) {
			this.articles = articles;
		}
	
	

}
