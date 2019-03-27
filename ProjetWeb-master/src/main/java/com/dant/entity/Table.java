package com.dant.entity;
import com.google.gson.JsonObject;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.gson.JsonObject;

public class Table implements Serializable{
	private String name;
	private Map<String, Index> index = new HashMap<>();
	private List<Colonne> cols=new ArrayList<Colonne>();
	
	public List<Colonne> getCols() {
		return cols;
	}


	public void setCols(List<Colonne> cols) {
		this.cols = cols;
	}


	public Table(String name) {
		this.name=name;
	}
 
   
	public void addColonne(String colName, String type) throws Exception {
		Colonne c=new Colonne(colName, type);
		if(cols.contains(c)) {
			throw new Exception("La colonne est déjà existante");
		}
		else cols.add(c);
	}
	
	public void insert(Account donnees) {
		
		
	}
	public String getName() {
		return this.name;
	}


	public Map<String, Index> getIndex() {
		return index;
	}


	public void setIndex(Map<String, Index> index) {
		this.index = index;
	}
}
