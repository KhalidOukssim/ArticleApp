package ma.ensa.ArticlePub.service.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ma.ensa.ArticlePub.domain.CoAuteur;
import ma.ensa.ArticlePub.repository.CoAuteurRepository;
import ma.ensa.ArticlePub.service.CoAuteurService;

@Service
public class CoAuteurServiceImpl implements CoAuteurService{
	
	@Autowired
	private CoAuteurRepository coAuteurRepo;

	@Override
	public CoAuteur save(CoAuteur coAuteur) {
		// TODO Auto-generated method stub
		return coAuteurRepo.save(coAuteur);
	}

	@Override
	public List<CoAuteur> findAll() {
		// TODO Auto-generated method stub
		return coAuteurRepo.findAll();
	}

}
