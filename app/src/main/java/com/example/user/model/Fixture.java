package com.example.user.Model;

public class Fixture {

	private int id;
	private int idFamily;
	private String nameFixture;


	public Fixture(int id,int idFamily, String nameFixture) {
		this.id = id;
		this.idFamily = idFamily;
		this.nameFixture = nameFixture;
	}
	
	public int getIdFamily() {
		return idFamily;
	}

	public void setIdFamily(int idFamily) {
		this.idFamily = idFamily;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNameFixture() {
		return nameFixture;
	}

	public void setNameFixture(String nameFixture) {
		this.nameFixture = nameFixture;
	}


}
