package ma.ensa.ArticlePub.service.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ma.ensa.ArticlePub.Exception.RevueNotFoundException;
import ma.ensa.ArticlePub.domain.Revue;
import ma.ensa.ArticlePub.repository.RevueRepository;
import ma.ensa.ArticlePub.service.RevueService;

@Service
public class RevueServiceImpl implements RevueService {

	@Autowired
	private RevueRepository revueRepo;

	@Override
	public Revue save(Revue revue) {
		return revueRepo.save(revue);
	}

	@Override
	public List<Revue> findAll() {
		return revueRepo.findAll();
	}

	@Override
	public Revue findByName(String name) {
		if (revueRepo.findByName(name) == null) {
			throw new RevueNotFoundException("Revue does not exist");
		}
		return revueRepo.findByName(name);
	}

}
