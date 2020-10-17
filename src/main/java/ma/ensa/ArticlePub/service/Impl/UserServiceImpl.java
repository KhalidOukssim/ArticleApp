package ma.ensa.ArticlePub.service.Impl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import ma.ensa.ArticlePub.Exception.UserNotFoundException;
import ma.ensa.ArticlePub.domain.Role;
import ma.ensa.ArticlePub.domain.User;
import ma.ensa.ArticlePub.domain.Dto.UserDto;
import ma.ensa.ArticlePub.domain.enums.Roles;
import ma.ensa.ArticlePub.repository.RoleRepository;
import ma.ensa.ArticlePub.repository.UserRepository;
import ma.ensa.ArticlePub.service.UserService;

@Service
public class UserServiceImpl implements UserDetailsService, UserService {

	@Autowired
	private UserRepository userDao;
	@Autowired
	private RoleRepository roleRepo;

	@Autowired
	private BCryptPasswordEncoder bcryptEncoder;

	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userDao.findByUsername(username);
		if (user == null) {
			throw new UsernameNotFoundException("Invalid username or password.");
		}
		return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(),
				getAuthority(user));
	}

	private Set<SimpleGrantedAuthority> getAuthority(User user) {
		Set<SimpleGrantedAuthority> authorities = new HashSet<>();
		user.getRoles().forEach(role -> {
			// authorities.add(new SimpleGrantedAuthority(role.getName()));
			authorities.add(new SimpleGrantedAuthority("ROLE_" + role.getName()));
		});
		return authorities;
		// return Arrays.asList(new SimpleGrantedAuthority("ROLE_ADMIN"));
	}

	public List<User> findAll() {
		List<User> list = new ArrayList<>();
		userDao.findAll().iterator().forEachRemaining(list::add);
		return list;
	}

	@Override
	public void delete(long id) {
		userDao.deleteById(id);
	}

	@Override
	public User findOne(String username) {
		return userDao.findByUsername(username);
	}

	@Override
	public User findById(Long id) {
		if (userDao.findById(id) == null) {
			throw new UserNotFoundException("No User exist with id: " + id);
		}
		return userDao.findById(id).get();
	}

	public User findByUsername(String user) {
		if (userDao.findByUsername(user) == null) {
			throw new UserNotFoundException("No User exist with username: " + user);
		}
		return userDao.findByUsername(user);
	}

	@Override
	public User save(UserDto user) {
		User newUser = new User();
		newUser.setUsername(user.getUsername());
		newUser.setPassword(bcryptEncoder.encode(user.getPassword()));
		newUser.setAge(user.getAge());
		newUser.setCity(user.getCity());
		Set<Role> roles = new HashSet<>();
		roles.add(roleRepo.findByName(Roles.SUBSCRIBED.getRole()));
		newUser.setRoles(roles);
		return userDao.save(newUser);
	}

	@Override
	public User saveUser(User user) {
		return userDao.save(user);
	}
}
