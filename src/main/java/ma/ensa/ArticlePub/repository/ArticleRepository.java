package ma.ensa.ArticlePub.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import ma.ensa.ArticlePub.domain.Article;

@Repository
public interface ArticleRepository extends JpaRepository<Article, Long> {
	
	@Query("FROM Article a JOIN a.etatArticle et WHERE et.etat='En Attente'")
	public List<Article> getArticlesEnAttente();
	
	@Query("FROM Article a JOIN a.etatArticle et WHERE et.etat='Accepté'")
	public List<Article> getArticlesAccepte();
	
	@Query("FROM Article a JOIN a.etatArticle et WHERE et.etat='publié'")
	public List<Article> getArticlesPublie();
	
	@Query("FROM Article a JOIN a.auteur auth WHERE auth.username=?1")
	public List<Article> getArticlesByAuthor(String name);
	
	@Query("FROM Article a JOIN a.motcles mc WHERE lower(mc.name) like %:keyword%")
	public List<Article> getArticlesByMotCle(@Param("keyword") String keyword);
	

}
