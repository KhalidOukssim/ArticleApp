package ma.ensa.ArticlePub.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ma.ensa.ArticlePub.domain.CoAuteur;

@Repository
public interface CoAuteurRepository extends JpaRepository<CoAuteur, Long> {

}
