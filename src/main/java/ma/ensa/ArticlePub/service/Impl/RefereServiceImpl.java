package ma.ensa.ArticlePub.service.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ma.ensa.ArticlePub.domain.Refere;
import ma.ensa.ArticlePub.repository.RefereRepository;
import ma.ensa.ArticlePub.service.RefereService;

@Service
public class RefereServiceImpl implements RefereService {

	@Autowired
	private RefereRepository refereRepo;

	@Override
	public Refere save(Refere refere) {
		return refereRepo.save(refere);
	}

	@Override
	public List<Refere> findAll() {
		return refereRepo.findAll();
	}

	@Override
	public Refere findById(long id) {
		return refereRepo.findById(id).get();
	}

	@Override
	public void delete(long id) {
		refereRepo.deleteById(id);
	}

}
