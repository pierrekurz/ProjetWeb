package com.dant.entity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Table {
	Map<String, Colonne> colonnes = new HashMap<>();
	
	private Table(/*Colonne... cols*/) {
		/*for(Colonne col : cols) {
			this.colonnes.put(col.name, col);
		}*/
	} private static class TableHolder
    {       
        /** Instance unique non préinitialisée */
        private final static Table instance = new Table();
    }
 
    /** Point d'accès pour l'instance unique du singleton */
    public static Table getInstance()
    {
        return TableHolder.instance;
    }
    
	
	public Table addColonne(Colonne c) {
		this.colonnes.put(c.name, c);
		return this;
	}
}
