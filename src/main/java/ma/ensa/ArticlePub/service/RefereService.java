package ma.ensa.ArticlePub.service;

import java.util.List;

import ma.ensa.ArticlePub.domain.Refere;

public interface RefereService {

	public Refere save(Refere refere);

	public List<Refere> findAll();

	Refere findById(long id);

	void delete(long id);

}
