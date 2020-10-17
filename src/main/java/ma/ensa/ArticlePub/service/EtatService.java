package ma.ensa.ArticlePub.service;

import java.util.List;

import ma.ensa.ArticlePub.domain.Etat;

public interface EtatService {
	
	Etat save(Etat etat);
    List<Etat> findAll();


}
