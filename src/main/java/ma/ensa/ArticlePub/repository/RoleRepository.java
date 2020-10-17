package ma.ensa.ArticlePub.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ma.ensa.ArticlePub.domain.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role,Long>{
	
	 Role findByName(String name);

}
