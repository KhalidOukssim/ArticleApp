package ma.ensa.ArticlePub.service;

import java.util.List;

import ma.ensa.ArticlePub.domain.Controle;

public interface ControleService {
	
	Controle save(Controle controle);
    List<Controle> findAll();
	List<Controle> getMyArticles(Long id);
    
}
