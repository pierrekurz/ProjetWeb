package com.dant.entity;
import com.google.gson.JsonObject;


public class lecteurTable {
	
	Table currentTable;
	String nameColumn;
	String element;
	Table returnTable;
	
	

	
	public void lecteurTable(Table nameTable, String nameColumn, String element) {

		this.currentTable = nameTable;
		this.nameColumn = nameColumn;
		this.element = element;
		this.returnTable = new Table("table indexes" + nameColumn + " element recherche : " + element);

		for (int j = 0; j < this.currentTable.cols.size(); j++){

			if (this.currentTable.elementsTable.get(j).get(this.nameColumn).contains(this.element )){
				this.returnTable.insert(this.currentTable.elementsTable.get(j));
				System.out.println(this.currentTable.elementsTable.get(j));
			}

		}

	}
 
}
