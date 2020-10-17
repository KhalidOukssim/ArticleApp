package ma.ensa.ArticlePub.service;

import java.util.List;

import ma.ensa.ArticlePub.domain.MotCle;

public interface MotCleService {
	
	MotCle save(MotCle motCle);
    List<MotCle> findAll();

}
