package com.alkemy.ong.entities;
//Clase de prueba para probar la lógica del Mapper, reemplazar por la verdadera clase "Organization" cuando esté disponible.
public class OrganizationMock {
	
	private Long id;
	private String name;
	private String image;
	private Integer phone;
	private String address;
	//Valor secreto que no se mapea al DTO
	private int secretValue;
	
	public OrganizationMock() {
		// TODO Auto-generated constructor stub
	}
	
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
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
