package ma.ensa.ArticlePub.service;



import java.util.List;

import ma.ensa.ArticlePub.domain.User;
import ma.ensa.ArticlePub.domain.Dto.UserDto;

public interface UserService {

    User save(UserDto user);
    List<User> findAll();
    void delete(long id);
    User findOne(String username);
    public User findByUsername(String user);
    User findById(Long id);
	User saveUser(User user);
}
