package com.example.user.model;

import java.util.Date;

public class Fixture {

	private int id;
	private int idFamily;
	private String nameFixture;
	private String drawing;
	
	public Fixture() {
		
	}
	
	public Fixture(int id,int idFamily, String nameFixture, String drawing) {
		this.id = id;
		this.idFamily = idFamily;
		this.nameFixture = nameFixture;
		this.drawing = drawing;
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

	public String getDrawing() {
		return drawing;
	}

	public void setDrawing(String drawing) {
		this.drawing = drawing;
	}

}
