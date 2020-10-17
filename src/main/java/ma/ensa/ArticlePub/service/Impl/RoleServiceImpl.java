package ma.ensa.ArticlePub.service.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ma.ensa.ArticlePub.domain.Role;
import ma.ensa.ArticlePub.repository.RoleRepository;
import ma.ensa.ArticlePub.service.RoleService;

@Service
public class RoleServiceImpl implements RoleService {

	@Autowired
	private RoleRepository roleRepo;

	@Override
	public Role findByName(String name) {
		return roleRepo.findByName(name);
	}
}
