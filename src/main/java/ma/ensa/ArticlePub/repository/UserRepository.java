package ma.ensa.ArticlePub.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import ma.ensa.ArticlePub.domain.User;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {
    User findByUsername(String username);
}
