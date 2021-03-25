package com.bk.kabow.service.location;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bk.kabow.dao.LocationReposity;
import com.bk.kabow.entity.Location;

@Service
public class LocationServiceImpl implements LocationService{

	private LocationReposity repository;
	
	@Autowired
	public LocationServiceImpl(LocationReposity LocationRepository) {
		repository = LocationRepository;
	}

	@Override
	public List<Location> findAll() {
		// TODO Auto-generated method stub
		return repository.findAll();
	}

	@Override
	public Location findById(int theId) {
		Optional<Location> result = repository.findById(theId);
		Location theLocation = null;
		
		if (result.isPresent()) {
			theLocation = result.get();
			
		} else {
			throw new RuntimeException("did not find location id: "+theId);
		}
		
		return theLocation;
	}

	@Override
	public void save(Location theLocation) {
		repository.save(theLocation);
		
	}

	@Override
	public void deleteById(int theId) {
		repository.deleteById(theId);
		
	}

	@Override
	public List<Location> findByLocationAndCategory(String Location, String Category, int People1, int People2) {
		// TODO Auto-generated method stub
		
		
		return repository.findByLocationAndCategory(Location, Category, People1, People2);
	}

	@Override
	public List<Location> findByPeople(int People) {
		// TODO Auto-generated method stub
		return repository.findByPeople(People);
	}
	


}
