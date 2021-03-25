package com.bk.kabow.service.location;

import java.util.List;

import com.bk.kabow.entity.Location;

public interface LocationService {

	public List<Location> findAll();
	
	public Location findById(int theId);
	
	public void save(Location theLocation);
	
	public void deleteById(int theId);
	
	public List<Location> findByPeople(int People);
	
	public List<Location> findByLocationAndCategory(String Location, String Category, int People1, int People2);
	
}
