package com.bk.kabow.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.bk.kabow.entity.Location;

@RepositoryRestResource(path = "location")
public interface LocationReposity extends JpaRepository<Location, Integer> {
	
	@Query(value = "SELECT * FROM Location WHERE province LIKE %?1% AND category LIKE %?2% AND (people = ?3 OR people = ?4)", nativeQuery = true)
	List<Location> findByLocationAndCategory(String Location, String Category, int People1, int People2);
	
	@Query(value = "SELECT * FROM Location WHERE People = ?1", nativeQuery = true)
	List<Location> findByPeople(int people);
}
