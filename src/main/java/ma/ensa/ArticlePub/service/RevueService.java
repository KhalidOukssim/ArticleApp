package ma.ensa.ArticlePub.service;

import java.util.List;

import ma.ensa.ArticlePub.domain.Revue;

public interface RevueService {
	
	Revue save(Revue revue);
    List<Revue> findAll();
	Revue findByName(String name);

}
