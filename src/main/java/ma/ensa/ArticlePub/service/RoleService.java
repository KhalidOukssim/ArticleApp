package ma.ensa.ArticlePub.service;


import ma.ensa.ArticlePub.domain.Role;

public interface RoleService {

	Role findByName(String name);
	
}
