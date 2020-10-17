package ma.ensa.ArticlePub.service.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ma.ensa.ArticlePub.domain.Evaluation;
import ma.ensa.ArticlePub.repository.EvaluationRepository;
import ma.ensa.ArticlePub.service.EvaluationService;

@Service
public class EvaluationServiceImpl implements EvaluationService {

	@Autowired
	private EvaluationRepository evaluationRepository;

	@Override
	public Evaluation save(Evaluation evaluation) {
		return evaluationRepository.save(evaluation);
	}

	@Override
	public List<Evaluation> findAll() {
		return evaluationRepository.findAll();
	}

	@Override
	public Evaluation findById(long id) {
		return evaluationRepository.findById(id).get();
	}

}
