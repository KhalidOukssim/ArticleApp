package ma.ensa.ArticlePub.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ma.ensa.ArticlePub.domain.Refere;

@Repository
public interface RefereRepository extends JpaRepository<Refere,Long>{

}
