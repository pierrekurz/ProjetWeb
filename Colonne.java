package com.dant.entity;

public class Colonne {
	private String name;


	private String type;
	


	public Colonne(String name, String type){
		this.name= name;
		this.type= type;
	}
		public String getType() {
		return type;
	}
		public String getName() {
		return name;
	}
}
