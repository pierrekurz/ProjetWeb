package com.dant.entity;

import java.io.IOException;
import java.io.Serializable;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.gson.Gson;

import Controllers.FileManager;
import Controllers.Repartisseur;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;




public class Table implements Serializable {
    private String name;
    Boolean mainNode;
    //private Map<String, Index> index = new HashMap<>();
    List<String> columnNames = new ArrayList<String>();
    HashMap<List<String>, Index> listIndex = new HashMap<List<String>, Index>();
    List<Object[]> data = new ArrayList<>();
    Repartisseur repartisseur;
    int nodeTurn;
    FileManager fileManager;


    public Table(String name, Boolean mainNode) {
        this.name = name;
        this.mainNode = mainNode;
        this.repartisseur = new Repartisseur(mainNode);
        this.nodeTurn = 0;
        this.fileManager = new FileManager();
    	System.out.println("Table créée : ");
    	System.out.println(this.name);
    	System.out.println("\n");
    }

    public void init(List<String> line0) throws IOException {
    	//System.out.println("Table initialisée : ");
    	FileManager.createFile(this.name, line0);
        for (String row : line0) {
            this.columnNames.add(row);
            //System.out.println("Titres : ");
            //System.out.println(row);
        	//System.out.println("\n");
        }
    }

    public void addLines(List<Object[]> lines) throws IOException {
    	if (mainNode) {
    		// this is the main node
    		/*if (this.nodeTurn != 0) {
    			//it's not this node's turn to record the line
    			repartisseur.addLine(lines,nodeTurn );
    			return;
    		}*/
    		this.nodeTurn += 1;
    		// next node's turn
    		if (this.nodeTurn == this.repartisseur.getOtherNodes().size()) {
    			this.nodeTurn = 0;
    		}
    	}
    	
    	//System.out.println("Lignes ajoutées : ");
    	int llklk = 0;
        for (Object[] line : lines) {
        	/*System.out.println(line[0]);
        	System.out.println(line[1]);
        	System.out.println(line[2]);*/
        	//System.out.println(llklk );
        	llklk ++;
           
            
        }
        //System.out.println("nb elements");
        //System.out.println(data.size());
        this.fileManager.writeLines(this.name, lines);

        for (List<String> nameCurrentIndex : listIndex.keySet()) {
            for (Object[] line : lines) {
            	listIndex.get(nameCurrentIndex).insert(line);
            }
        }
    }

    public void addIndex(List<String> nameIndex) throws MalformedURLException {
    	if (this.mainNode == true) {
    		// need to distribute the instruction
    		repartisseur.addIndex(nameIndex);
    	}
    	//System.out.println("Ajout Index");
        int nbLine = 0;
        List<Integer> placeOfValue = new ArrayList<Integer>();
        for (String name : columnNames) {
        	for (String possibleName : nameIndex)
	            if (name.equals(possibleName)) {
	                placeOfValue.add(nbLine);
	            } 
            nbLine++;
        }
        //System.out.println("nb elements");
        //System.out.println(data.size());
        //System.out.println(placeOfValue);
        
        Index newIndex = new Index(placeOfValue);
        listIndex.put(nameIndex, newIndex);
        for (Object[] line : data) {
        	//System.out.println("Valeur indexee: "); 
        	//System.out.println(line[placeOfValue.get(0)]);
        	newIndex.insert(line);	
        }
        System.out.println("\n");
    }
    
    public List<Object[]> searchBigger(String nameIndex, int valueMin) throws MalformedURLException {
    	
    	Index index = listIndex.get(nameIndex);//.get(nameOfMovie);
    	List<Object[]> results = index.searchBigger(valueMin);
    	if (this.mainNode) {
    		// need to distribute the instruction
    		results.addAll(repartisseur.searchBigger(nameIndex, valueMin));
    	}
    	for (Object[] line : results) {
    		//System.out.println(line);
    	}
    	return results;
    }
    
    public List<Object[]> searchSmaller(String nameIndex, int valueMin) throws MalformedURLException {
    	Index index = listIndex.get(nameIndex);
    	List<Object[]> results = index.searchSmaller(valueMin);
    	for (Object[] line : results) {
    		//System.out.println(line);
    	}
    	if (this.mainNode) {
    		// need to distribute the instruction
    		results.addAll(repartisseur.searchSmaller(nameIndex, valueMin));
    	}
    	return results;
    }
    
    public void get(List<String> nameOfIndex1, List<String> nameOfMovie) {
    	Index index = listIndex.get(nameOfIndex1);
    	//System.out.println(index);
    	List<Object[]> results;
    	if (index == null) {
    		
    		results = new ArrayList<Object[]>();
    		List<Integer> placeOfValue = new ArrayList<Integer>();
    		int nbLine = 0;
            for (String name : columnNames) {
            	for (String possibleName : nameOfIndex1)
    	            if (name.equals(possibleName)) {
    	                placeOfValue.add(nbLine);
    	                
    	            } 
                nbLine++;
            }
            int nbOfLineInData = 0;
    		for (Object[] line : data) {
    			int nbWordsOk = 0;
    			for (int rgLine : placeOfValue) {
    				//System.out.println(line[rgLine]);
    				for (String nameHeader : nameOfMovie) {
    					//System.out.println(nameHeader);
    					
    					
    					if (line[rgLine].equals(nameHeader)) {
    						nbWordsOk ++;
    						//System.out.println(nbWordsOk);
        					//System.out.println(line[rgLine]);
    						
    						
    					}
    				}
    			}
    			if (nbWordsOk>=nameOfMovie.size()){
    				results.add(line);
    				System.out.println(nbOfLineInData);
    				
    			}
    			nbOfLineInData ++;
    		}
    		
    		
    		
    	}
    	else {
    	results = index.get(nameOfMovie);
    	}
 
    	for (Object[] line : results) {
    		//System.out.println(line);	
    	}
    	/*if (this.mainNode) {
    		// need to distribute the instruction
    		results.addAll(repartisseur.get(nameIndex, value));
    	}*/
    }
    public String toString() {
    	Gson gson=new Gson();
    	return gson.toJson(this);
    }
}