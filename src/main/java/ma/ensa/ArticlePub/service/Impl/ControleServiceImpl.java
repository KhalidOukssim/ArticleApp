package ma.ensa.ArticlePub.service.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ma.ensa.ArticlePub.domain.Controle;
import ma.ensa.ArticlePub.repository.ControleRepository;
import ma.ensa.ArticlePub.service.ControleService;

@Service
public class ControleServiceImpl implements ControleService {
	
	@Autowired
	private ControleRepository controlerepo;

	@Override
	public Controle save(Controle controle) {
		// TODO Auto-generated method stub
		return controlerepo.save(controle);
	}

	@Override
	public List<Controle> findAll() {
		// TODO Auto-generated method stub
		return controlerepo.findAll();
	}

	@Override
	public List<Controle> getMyArticles(Long id) {
		// TODO Auto-generated method stub
		return controlerepo.getControleByIdRefere(id);
	}
	

}
