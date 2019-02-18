package com.dant.entity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Table {
	
	Map<String, Colonne> colonnes = new HashMap<>();
	
	public Table(/*Colonne... cols*/) {
		/*for(Colonne col : cols) {
			this.colonnes.put(col.name, col);
		}*/
	}
	
	public Table addColonne(String name, String type) {
		this.colonnes.put(name, new Colonne(name, type));
		return this;
	}
}
