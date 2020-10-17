package ma.ensa.ArticlePub.service.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import ma.ensa.ArticlePub.domain.MotCle;
import ma.ensa.ArticlePub.repository.MotCleRepository;
import ma.ensa.ArticlePub.service.MotCleService;

public class MotCleServiceImpl implements MotCleService {

	@Autowired
	private MotCleRepository motCleRepo;

	@Override
	public MotCle save(MotCle motCle) {
		return motCleRepo.save(motCle);
	}

	@Override
	public List<MotCle> findAll() {
		return motCleRepo.findAll();
	}

}
