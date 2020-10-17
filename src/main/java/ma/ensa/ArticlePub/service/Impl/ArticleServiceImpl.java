package ma.ensa.ArticlePub.service.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ma.ensa.ArticlePub.domain.Article;
import ma.ensa.ArticlePub.repository.ArticleRepository;
import ma.ensa.ArticlePub.service.ArticleService;

@Service
public class ArticleServiceImpl implements ArticleService{
	
	@Autowired
	private ArticleRepository articleRepo;

	@Override
	public Article save(Article article) {
		// TODO Auto-generated method stub
		return articleRepo.save(article);
	}

	@Override
	public List<Article> findAll() {
		// TODO Auto-generated method stub
		return articleRepo.findAll();
	}

	@Override
	public List<Article> getArticlesEnAttente() {
		// TODO Auto-generated method stub
		return articleRepo.getArticlesEnAttente();
	}

	@Override
	public Article findById(Long id) {
		// TODO Auto-generated method stub
		return articleRepo.findById(id).get();
	}

	@Override
	public List<Article> getArticlesAccepte() {
		// TODO Auto-generated method stub
		return articleRepo.getArticlesAccepte();
	}

	@Override
	public List<Article> getArticlesPublie() {
		// TODO Auto-generated method stub
		return articleRepo.getArticlesPublie();
	}
	
	@Override
	public void supprimerArticle(Long id) {
		// TODO Auto-generated method stub
		articleRepo.deleteById(id);
	}

	@Override
	public List<Article> getArticlesByMotCle(String keyword) {
		// TODO Auto-generated method stub
		return articleRepo.getArticlesByMotCle(keyword);
	}

	@Override
	public List<Article> getArticlesByAuthor(String name) {
		// TODO Auto-generated method stub
		return articleRepo.getArticlesByAuthor(name);
	}
	
	

}
