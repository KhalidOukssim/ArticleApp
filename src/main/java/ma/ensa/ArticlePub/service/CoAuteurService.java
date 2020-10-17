package ma.ensa.ArticlePub.service;

import java.util.List;

import ma.ensa.ArticlePub.domain.CoAuteur;


public interface CoAuteurService {
	
	CoAuteur save(CoAuteur coAuteur);
    List<CoAuteur> findAll();

}
