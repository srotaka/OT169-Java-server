package com.alkemy.ong.entities;

public class OrganizationMock {
	private String name;
	private String image;
	private Integer phone;
	private String address;
	private int secretValue;
	
	public OrganizationMock() {
		// TODO Auto-generated constructor stub
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public Integer getPhone() {
		return phone;
	}

	public void setPhone(Integer phone) {
		this.phone = phone;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}
	
	public int getSecretValue() {
		return secretValue;
	}
	
	public void setSecretValue(int secretValue) {
		this.secretValue = secretValue;
	}
}
