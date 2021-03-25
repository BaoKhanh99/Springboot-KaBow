package com.bk.kabow.entity;

import java.util.Collection;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name = "users")
public class User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(name = "name")
	private String name;
	
	@Column(name = "year_of_birth")
	private int  yearOfBirth;
	
	@Column(name = "address")
	private String address;
	
	@Column(name = "phone_number")
	private int phoneNumber;
	
	@Column(name = "email")
	private String email;
	
	@Column(name = "password")
	private String password;
	
	@ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinTable(
			name = "users_roles",
			joinColumns = @JoinColumn(
					name = "id_user", referencedColumnName = "id"),
			inverseJoinColumns = @JoinColumn(
					name = "id_role", referencedColumnName = "id"))
	private Collection<Role> roles;

	public User() {
		// TODO Auto-generated constructor stub
	}


	public User( String name, int yearOfBirth, String address, int phoneNumber, String email, String password,
			Collection<Role> roles) {
		super();
		this.name = name;
		this.yearOfBirth = yearOfBirth;
		this.address = address;
		this.phoneNumber = phoneNumber;
		this.email = email;
		this.password = password;
		this.roles = roles;
	}

	
	
	public User(String name, String email, String password, Collection<Role> roles) {
		super();
		this.name = name;
		this.email = email;
		this.password = password;
		this.roles = roles;
	}



	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getID() {
		return id;
	}

	public void setID(int iD) {
		id = iD;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getYearOfBirth() {
		return yearOfBirth;
	}

	public void setYearOfBirth(int yearOfBirth) {
		this.yearOfBirth = yearOfBirth;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public int getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(int phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	
	public Collection<Role> getRoles() {
		return roles;
	}

	public void setRoles(Collection<Role> roles) {
		this.roles = roles;
	}

	@Override
	public String toString() {
		return "User [ID=" + id + ", Name=" + name + ", YearOfBirth=" + yearOfBirth + ", Address=" + address
				+ ", PhoneNumber=" + phoneNumber + ", Email=" + email + ", Password=" + password + ", roles=" + roles
				+ "]";
	}


	
}
