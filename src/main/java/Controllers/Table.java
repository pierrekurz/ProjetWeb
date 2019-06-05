package Controllers;

import java.io.IOException;
import java.io.Serializable;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;




public class Table implements Serializable {
    private String name;
    Boolean mainNode;
    //private Map<String, Index> index = new HashMap<>();
    List<String> columnNames = new ArrayList<String>();
    HashMap<List<String>, Index> listIndex = new HashMap<List<String>, Index>();
    //List<Object[]> data = new ArrayList<>();
    Repartisseur repartisseur;
    int nodeTurn;
    FileManager fileManager;
    int nbLinesParsed;
    List<String> headers;


    public Table(String name) {
        this.name = name;
        this.mainNode = false;
        this.repartisseur = new Repartisseur(mainNode);
        this.nodeTurn = 0;
        this.fileManager = new FileManager();
        this.nbLinesParsed=0;
        this.headers = new ArrayList<String>();
    	System.out.println("Table créée : ");
    	System.out.println(this.name);
    	System.out.println("\n");
    }

    public void init(List<String> line0) throws IOException {
    	//System.out.println("Table initialisée : ");
    	this.headers = line0;
    	this.fileManager.createFile(this.name, line0);
        for (String row : line0) {
            this.columnNames.add(row);
            //System.out.println("Titres : ");
            //System.out.println(row);
        	//System.out.println("\n");
        }
    }

    public void addLines(List<Object[]> lines) throws IOException {
    	System.out.println("ajout lignes");
    	System.out.println(lines);
        
        
    	
    	
        //System.out.println("nb elements");
        //System.out.println(data.size());
        this.fileManager.writeLines(this.name, lines);
        
        List<HashMap<String, String>> listformatedLine = new ArrayList<HashMap<String, String>>();
        for(Object[] line:lines) {
	        HashMap<String, String> formatedLine = new HashMap<String, String>();
	        int nbOfWordAddedToHashMap = 0;
	        for (String header : this.headers) {
	        	formatedLine.put(header, line[nbOfWordAddedToHashMap].toString());
	        	nbOfWordAddedToHashMap++;
	        }
	        listformatedLine.add(formatedLine);
        }

        for (List<String> nameCurrentIndex : listIndex.keySet()) {
        	int l=0;
            for (HashMap<String, String> formatedLine : listformatedLine) {
            	System.out.println("nb elements");
            	listIndex.get(nameCurrentIndex).insert(formatedLine, this.nbLinesParsed+l);
            	l++;
            }
        }
        this.nbLinesParsed += lines.size();
        
        
    }

    public void addIndex(List<String> nameIndex) throws MalformedURLException {
    	/*if (this.mainNode == true) {
    		// need to distribute the instruction
    		repartisseur.addIndex(nameIndex);
    	}*/
    	System.out.println("Ajout Index");
        int nbLine = 0;
        List<Integer> placeOfValue = new ArrayList<Integer>();
        for (String name : columnNames) {
        	for (String possibleName : nameIndex)
	            if (name.equals(possibleName)) {
	                placeOfValue.add(nbLine);
	            } 
            nbLine++;
        }
        System.out.println("Ici6");
        //System.out.println("nb elements");
        //System.out.println(data.size());
        //System.out.println(placeOfValue);
        
        Index newIndex = new Index(placeOfValue, nameIndex, this.name);
        listIndex.put(nameIndex, newIndex);
        System.out.println("Ici7");
    	List<HashMap<String, String>> linesToIndex;
    	List<Integer> numbersToGet = new ArrayList<Integer>();
        for (int k = 0; k<this.nbLinesParsed;k++) {
        	numbersToGet.add(k);
        	if (numbersToGet.size() == 10000) {
        		linesToIndex = fileManager.readLines(this.name, numbersToGet);
        		int l=0;
        		for (HashMap<String, String> lineToIndex : linesToIndex) {
        			newIndex.insert(lineToIndex, k+l);
        			l++;
        		}
        		
        	}
        	
        	System.out.println("Valeur indexee: "); 
        	//System.out.println(line[placeOfValue.get(0)]);
        	//	
        }
    		linesToIndex = fileManager.readLines(this.name, numbersToGet);
    		int l=0;
    		for (HashMap<String, String> lineToIndex : linesToIndex) {
    			newIndex.insert(lineToIndex, (int)(this.nbLinesParsed/10000)+l);
    			l++;
    		}
        System.out.println("\n");
    }
    
    public List<HashMap<String, String>> searchBigger(String nameIndex, int valueMin) throws MalformedURLException {
    	
    	Index index = listIndex.get(nameIndex);
    	List<HashMap<String, String>> results = index.searchBigger(valueMin);
    	return results;
    }
    
    public List<Object[]> searchSmaller(String nameIndex, int valueMin) throws MalformedURLException {
    	Index index = listIndex.get(nameIndex);
    	List<Object[]> results = index.searchSmaller(valueMin);
    	for (Object[] line : results) {
    		//System.out.println(line);
    	}
    	
    	return results;
    }
    
    
    
    public List<HashMap<String, String>> getWithoutIndex(List<String> key, List<String> valueToGet){ 
    	List<HashMap<String, String>> results = new ArrayList<HashMap<String, String>>();
    	List<Integer> nbToGet= new ArrayList<Integer>();
    	nbToGet.add(-1);
    	List<HashMap<String, String>> lineToSort = this.fileManager.readLines(this.name, nbToGet);
        for(HashMap<String, String> line : lineToSort) {
        	int nbIndexFound = 0;//savoir combien on a trouve d elements correspondant aux valeurs recherchees
        	int nbOfNameInIndex = 0;//savoir combientieme elemeent on en est
        	for(String indexAvailable:key) {
	            if(line.get(indexAvailable).equals(valueToGet.get(nbOfNameInIndex))) {
	            	nbIndexFound ++;
	            }
	            nbOfNameInIndex++;
        	}
        	if (nbIndexFound == key.size()) {
        		results.add(line);
        	}
        
        }
        return results;
    }
    
    
    
    
    public List<HashMap<String, String>> get(List<String> nameOfIndex1, List<String> valueToGet) {
    	Index index = listIndex.get(nameOfIndex1);
    	
    	if (index==null) {
    		return getWithoutIndex(nameOfIndex1,valueToGet);
    	}
    	
    	List<HashMap<String, String>> results;
    	if (index == null) {
    		results = getWithoutIndex(nameOfIndex1, valueToGet);
    		
    	}
    	else {
		System.out.println("passe par la");	
    	results = index.get(valueToGet);
    	}
    	
    	return results;
    }
    
    
    
}