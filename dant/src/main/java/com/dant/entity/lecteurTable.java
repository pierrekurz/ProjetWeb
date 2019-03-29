package com.dant.entity;
import java.util.List;

import com.google.gson.JsonObject;


public class lecteurTable {
	
	Table currentTable;
	String nameColumn;
	String element;
	Table returnTable;
	
	

	
	public void lecteurTable(Table nameTable, String nameColumn, String element) {

		this.nameColumn = nameColumn;
		this.element = element;
		returnTable = new Table("table indexes" + nameColumn + " element recherche : " + element);

		for (int j = 0; j < this.currentTable.cols.size(); j++){
			if (nameTable.elementsTable.get(j).get(nameColumn).contains(element )){
				returnTable.insert(nameTable.elementsTable.get(j));
				System.out.println(nameTable.elementsTable.get(j));
			}

		}

	}


/*	public void getLinesByConditionsNumber(Table nameTable, String nameColumn, String condition, int value){
		// function to get elements from a condition on its value 

		returnTable = new Table("table indexes" + nameColumn + " element recherche : " + element);
		Colonne currentColonne = nameTable.cols.get(nameColumn);

		if (currentColonne.getType() != "int"){
			// the column doesn t contains figure so it is impossible 
			System.out.println("error : not int in selected column");
		}

		else{

			for (int k=0; k< nameTable.elementsTable; k++){
				// for each element in the column, we are going to test if the condition is validated

				if(condition == "=" && nameTable.elementsTable.get(k).get(nameColumn) == value){
					// condiiton of equality
					returnTable.insert(nameTable.elementsTable.get(k));
					System.out.println(nameTable.elementsTable.get(k));
				}
				else if(condition == ">" && nameTable.elementsTable.get(k).get(nameColumn) > value){
					// condition to be bigger than a value
					returnTable.insert(nameTable.elementsTable.get(k));
					System.out.println(nameTable.elementsTable.get(k));
				}

				else if(condition == "<" && nameTable.elementsTable.get(k).get(nameColumn) < value){
					// condition to be smaller than a value
					returnTable.insert(nameTable.elementsTable.get(k));
					System.out.println(nameTable.elementsTable.get(k));
				}

				else if(condition == ">=" && nameTable.elementsTable.get(k).get(nameColumn) >= value){
					// condition to be bigger or equal to a value
					returnTable.insert(nameTable.elementsTable.get(k));
					System.out.println(nameTable.elementsTable.get(k));
				}

				else if(condition == "<=" && nameTable.elementsTable.get(k).get(nameColumn) <= value){
					// condition to be smaller or equal to a value
					returnTable.insert(nameTable.elementsTable.get(k));
					System.out.println(nameTable.elementsTable.get(k));
				}

			}

		}

	}*/



	public void searchOnManyColumns(Table nameTable, List<String> nameColumns, String element) {
		// function to get all the lines of the table with the element in any column of the given list

		returnTable = new Table("table indexes" + nameColumn + " element recherche : " + element);

		for (int j = 0; j < this.currentTable.cols.size(); j++){
			//for each line of the column
			for (int k=0; k < nameColumns.size();k++){
				if (nameTable.elementsTable.get(j).get(nameColumns.get(k)).contains(element )){
					returnTable.insert(nameTable.elementsTable.get(j));
					System.out.println(nameTable.elementsTable.get(j));
				}
			}

		}

	}
 
}
