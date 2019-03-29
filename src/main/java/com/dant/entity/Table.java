package com.dant.entity;
import com.google.gson.JsonObject;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.gson.JsonObject;

public class Table implements Serializable{
	public String name;
	Map<String, Index> index = new HashMap<>();
	
	public Table(String name) {
		this.name=name;
	}
 
   
	public Table addColonne(Colonne c) {
		return this;
	}
	
	public void insert(Account donnees) {
		
		
	}
}
