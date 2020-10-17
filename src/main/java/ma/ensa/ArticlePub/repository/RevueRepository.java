package ma.ensa.ArticlePub.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ma.ensa.ArticlePub.domain.Revue;

	@Repository
	public interface RevueRepository extends JpaRepository<Revue, Long> {
		
		public Revue findByName(String name);

	}

