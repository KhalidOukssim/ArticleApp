package ma.ensa.ArticlePub.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import ma.ensa.ArticlePub.domain.Controle;

@Repository
public interface ControleRepository extends JpaRepository<Controle, Long>{
	
	@Query("FROM Controle c JOIN c.referes rf WHERE rf.id=?1")
	public List<Controle> getControleByIdRefere(Long id);

}
