package com.bk.kabow.entity;

public class Searching {

	private String location;
	private String category;
	private String people;
	
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getPeople() {
		return people;
	}
	public void setPeople(String people) {
		this.people = people;
	}
	
	public Searching() {
		// TODO Auto-generated constructor stub
	}
	
	public Searching(String location, String category, String people) {
		this.location = location;
		this.category = category;
		this.people = people;
	}
	
	
}
