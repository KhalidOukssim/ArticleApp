package ma.ensa.ArticlePub.service;

import java.util.List;



import ma.ensa.ArticlePub.domain.Article;

public interface ArticleService {
	
	Article save(Article article);
    List<Article> findAll();
    public List<Article> getArticlesEnAttente();
    Article findById(Long id);
    public List<Article> getArticlesAccepte();
    public List<Article> getArticlesPublie();
	void supprimerArticle(Long id);
	public List<Article> getArticlesByMotCle(String keyword);
	public List<Article> getArticlesByAuthor(String name);


}
