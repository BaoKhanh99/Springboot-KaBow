package com.bk.kabow.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.transaction.annotation.Transactional;

import com.bk.kabow.entity.User;

@RepositoryRestResource(path = "user")
public interface UserRepository extends JpaRepository<User, Integer> {

	User findByEmail(String email);
	
	@Transactional
	@Modifying
	@Query("UPDATE User u SET u.name= ?1, u.yearOfBirth=?2, u.address=?3, u.phoneNumber= ?4 WHERE u.id= ?5")
	void updateInfo(String name, int yearOfBirth, String address, int phoneNumber, int id);
	
	@Transactional
	@Modifying
	@Query("UPDATE User u SET u.password= ?1 WHERE u.id= ?2")
	void changePassword(String password, int id);
	
}
