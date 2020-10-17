package ma.ensa.ArticlePub.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ma.ensa.ArticlePub.domain.Evaluation;

@Repository
public interface EvaluationRepository  extends JpaRepository<Evaluation, Long> {

}
