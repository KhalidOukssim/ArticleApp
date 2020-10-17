package ma.ensa.ArticlePub.service;

import java.util.List;

import ma.ensa.ArticlePub.domain.Evaluation;

public interface EvaluationService {
	Evaluation save(Evaluation evaluation);
    List<Evaluation> findAll();
    Evaluation findById(long id);
}
