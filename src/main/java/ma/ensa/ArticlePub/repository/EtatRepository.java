package ma.ensa.ArticlePub.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ma.ensa.ArticlePub.domain.Etat;

@Repository
public interface EtatRepository extends JpaRepository<Etat, Long> {

}
