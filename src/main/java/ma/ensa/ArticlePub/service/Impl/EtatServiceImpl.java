package ma.ensa.ArticlePub.service.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ma.ensa.ArticlePub.domain.Etat;
import ma.ensa.ArticlePub.repository.EtatRepository;
import ma.ensa.ArticlePub.service.EtatService;

@Service
public class EtatServiceImpl implements EtatService {

	@Autowired
	private EtatRepository etatRepo;

	@Override
	public Etat save(Etat etat) {
		return etatRepo.save(etat);
	}

	@Override
	public List<Etat> findAll() {
		return etatRepo.findAll();
	}

}
