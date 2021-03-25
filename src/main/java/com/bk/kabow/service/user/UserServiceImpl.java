package com.bk.kabow.service.user;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.bk.kabow.dao.UserRepository;
import com.bk.kabow.entity.Role;
import com.bk.kabow.entity.User;
import com.bk.kabow.entity.userRegistration;

@Service
public class UserServiceImpl implements UserService{

	private UserRepository repository;
	
	@Autowired
	private BCryptPasswordEncoder passwordencoder;
	
	@Autowired
	public UserServiceImpl(UserRepository userRepository) {
		repository = userRepository;
	}
	
	@Override
	public List<User> findAll() {
		// TODO Auto-generated method stub
		return repository.findAll();
	}

	@Override
	public User findById(int theId) {
		// TODO Auto-generated method stub
		Optional<User> result = repository.findById(theId);
		User theUser = null;
		
		if (result.isPresent()) {
			theUser = result.get();	
		} 
		else {
			throw new RuntimeException("did not find user id: "+theId);
		}
		
		return theUser;
	}

	@Override
	public void save(User theUser) {
		repository.save(theUser);
		
	}
	
	@Override
	public void updateInfo(String name, int yearOfBirth, String address, int phoneNumber, int id) {
		repository.updateInfo(name, yearOfBirth, address, phoneNumber, id);
	}
	
	public void changePassword(String password, int id) {
		repository.changePassword(password, id);
	}

	@Override
	public User registration(userRegistration userRegistration) {
		
		User user = new User(userRegistration.getName(), userRegistration.getEmail(), passwordencoder.encode(userRegistration.getPassword()), Arrays.asList(new Role("ROLE_USER")));
		
		return repository.save(user);
	}
	
	@Override
	public void deleteById(int theId) {
		repository.deleteById(theId);
		
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		User user = repository.findByEmail(username);
		
		if (username == null) {
			throw new UsernameNotFoundException("Invalid User or Password");
		}
		
		return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(),mapRolesToAuthorities(user.getRoles()));
		
	}

	private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Collection<Role> roles){
		return roles.stream().map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());
	}

	@Override
	public User findByEmail(String email) {
		User user = repository.findByEmail(email);
		return user;
	}
}
