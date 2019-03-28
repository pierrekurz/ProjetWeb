package com.dant.entity;
import com.google.gson.JsonObject;


public class lecteurTable {

	
	public Table lecteurTable(Table nameTable, String nameColumn, String element) {

		this.currentTable = nameTable
		this.nameColumn = nameColumn
		this.element = element
		this.returnTable = new Table("table indexes" + nameColumn + " element recherche : " + element)

		for (int j = 0; j < this.currentTable.cols.size(); j++){

			if (this.element in this.currentTable.elements[j].get(this.nameColumn)){

				this.returnTable.add(this.currentTable[j])
			}

		}

	}
 
}
