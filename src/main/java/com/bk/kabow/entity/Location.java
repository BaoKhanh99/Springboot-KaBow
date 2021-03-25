package com.bk.kabow.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "location")
public class Location {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int ID;
	
	@Column(name = "title")
	private String Title;
	
	@Column(name = "detail")
	private String Detail;
	
	@Column(name = "image")
	private String Image;
	
	@Column(name = "time")
	private String TimeUpdate;
	
	@Column(name = "province")
	private String Province;
	
	@Column(name = "id_service")
	private int IdService;
	
	@Column(name = "id_comment")
	private int IdComment;
	
	@Column(name = "category")
	private String Category;
	
	@Column(name = "people")
	private int People;
	
	public Location() {
		// TODO Auto-generated constructor stub
	}

	public Location(String title, String detail, String image, String timeUpdate, String province, int idService,
			int idComment, String category, int amountOfPeople) {
		Title = title;
		Detail = detail;
		Image = image;
		TimeUpdate = timeUpdate;
		Province = province;
		IdService = idService;
		IdComment = idComment;
		Category = category;
		People = amountOfPeople;
	}

	public int getID() {
		return ID;
	}

	public void setID(int iD) {
		ID = iD;
	}

	public String getTitle() {
		return Title;
	}

	public void setTitle(String title) {
		Title = title;
	}

	public String getDetail() {
		return Detail;
	}

	public void setDetail(String detail) {
		Detail = detail;
	}

	public String getImage() {
		return Image;
	}

	public void setImage(String image) {
		Image = image;
	}

	public String getTimeUpdate() {
		return TimeUpdate;
	}

	public void setTimeUpdate(String timeUpdate) {
		TimeUpdate = timeUpdate;
	}

	public String getProvince() {
		return Province;
	}

	public void setProvince(String province) {
		Province = province;
	}

	public int getIdService() {
		return IdService;
	}

	public void setIdService(int idService) {
		IdService = idService;
	}

	public int getIdComment() {
		return IdComment;
	}

	public void setIdComment(int idComment) {
		IdComment = idComment;
	}

	public String getCategory() {
		return Category;
	}

	public void setCategory(String category) {
		Category = category;
	}

	public int getPeople() {
		return People;
	}

	public void setPeople(int amountOfPeople) {
		People = amountOfPeople;
	}

	@Override
	public String toString() {
		return "Location [ID=" + ID + ", Title=" + Title + ", Detail=" + Detail + ", Image=" + Image + ", TimeUpdate="
				+ TimeUpdate + ", Province=" + Province + ", IdService=" + IdService + ", IdComment=" + IdComment
				+ ", Category=" + Category + ", AmountOfPeople=" + People + "]";
	}
	
	
}
