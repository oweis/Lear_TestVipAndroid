package com.example.user.Model;

public class Splice {

	private int id;
	private int idFamily;
	private String nameSplice;
	
	public Splice() {

	}

	public Splice(int id,int idFamily, String nameSplice) {
		this.id = id;
		this.idFamily = idFamily;
		this.nameSplice = nameSplice;
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
	public String getNameSplice() {
		return nameSplice;
	}
	public void setNameSplice(String nameSplice) {
		this.nameSplice = nameSplice;
	}
}
