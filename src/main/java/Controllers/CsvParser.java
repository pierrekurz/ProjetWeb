package Controllers;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CsvParser {
	String name;
	Table table;
	Repartisseur repartisseur;
	int turnRepartition = 0;
	
	public CsvParser(String name, String path,  Repartisseur repartisseur) throws Exception {

		
		this.name = name;
		
		this.repartisseur = repartisseur;
		this.table = parseCsv(path, this.name);
	}
	
	public Table getTable(){
		return this.table;
	}
	
    public Table parseCsv(String path, String nameOfTable) throws Exception {
        // "C:\\Users\\Nguye\\OneDrive\\Documents\\dant-master\\test.csv";
        String csvFile = path;
        BufferedReader br = null;
        String line = "";
        String cvsSplitBy = ",";
        int compteurColonne = 1;
        Table table = new Table(nameOfTable);
        boolean firstLetterHeader = true;
        try {
            System.out.println("ok parser");
            br = new BufferedReader(new FileReader(csvFile));
            line = br.readLine();
            String nameColonne = "";
            List<String> headersTable = new ArrayList<>();
            for (int i = 0; i < line.length(); i++) {
                if (line.charAt(i) == ',' ) {
                    compteurColonne++;
                    headersTable.add(nameColonne); 
                    //System.out.println("colonne test");
                    System.out.println(nameColonne);
                    nameColonne = "";
                    firstLetterHeader = true;
                    //i++;
                } else {
                	if(firstLetterHeader) {
                		if (line.charAt(i) == ' ') { 
                		}
                		else {
                			firstLetterHeader = false;
                    		nameColonne += line.charAt(i);
                		}
                	}
                	else {
                		nameColonne += line.charAt(i);
                		//System.out.println(nameColonne);
                	}
                }
            }
            //compteurColonne++;
            headersTable.add(nameColonne);
            
            System.out.println("ici1");
            if(this.repartisseur.otherNodes.size()>0) {
            	this.repartisseur.sendHeaders(headersTable,nameOfTable);
            }
            table.init(headersTable);
            /*System.out.println("kkk 1");
            System.out.println(nameColonne);
            System.out.println(headersTable.size());
            */
            
            nameColonne = "";
            
            
            List<Object[]> listLinesAvailable = new ArrayList<>();
            List<Object[]> listLinesToBeSent = new ArrayList<>();
            
            int countLine = 0;
            while ((line = br.readLine()) != null) {
            	
            	if (listLinesAvailable.size() >= 1000000) {
            		table.addLines(listLinesAvailable);
                	listLinesAvailable = new ArrayList<>();
                	if(this.repartisseur.otherNodes.size()>0) {
                    	this.repartisseur.sendLines(listLinesToBeSent, this.name);
                    }
                	
            	}
            	
            	
            	
            	
            	if (countLine%100000 ==  0){
            		System.out.println("Nb lignes parsees");
            		System.out.println(countLine);
            	}
            	countLine++;
            	nameColonne = "";
                int countWord = 0;
                boolean firstLetter = true;
                //System.out.println("test readline");

                String elementCSV[] = new String[compteurColonne];
                for (int i = 0; i < line.length(); i++) {
                    if (line.charAt(i) == ',') {
                        elementCSV[countWord] = nameColonne;
                        //System.out.println(nameColonne);
                        nameColonne = "";
                        firstLetter = true;
                        countWord++;
                        //i++;
                    } else {
                    	if(firstLetter) {
                    		if (line.charAt(i) == ' ') { 
                    		}
                    		else {
                    			firstLetter = false;
                        		nameColonne += line.charAt(i);
                    		}
                    	}
                    	else {
                    		nameColonne += line.charAt(i);
                    	}
                        
                    }
                }
                
                elementCSV[countWord] = nameColonne;
                /*System.out.println(cpt);
                System.out.println("voici 3 mots");
                System.out.println(elementCSV[0]);
                System.out.println(elementCSV[1]);
                System.out.println(elementCSV[2]);
                System.out.println("\n");*/
                //System.out.println(compteurColonne);
                if (elementCSV[compteurColonne-1] != null){
                	//System.out.println("Ajout ligne");
                	if(this.turnRepartition == 0) {
                		listLinesAvailable.add(elementCSV);
                		turnRepartition++;
                	}
                	else {
                		if(this.turnRepartition >= this.repartisseur.otherNodes.size()) {
                			this.turnRepartition = 0;
                		}
                		
                		if(this.turnRepartition == 0) {
                    		listLinesAvailable.add(elementCSV);
                    		turnRepartition++;
                    	}
                		else {
                		listLinesToBeSent.add(elementCSV);
                		this.turnRepartition ++;
                		}
                		
                	}
                	
                	
                	
                } 
            }
            table.addLines(listLinesAvailable);
            if(this.repartisseur.otherNodes.size()>0) {
            	this.repartisseur.sendLines(listLinesToBeSent, this.name);
            }
            
            System.out.println("done!");
            

            



        } 
        
        
        
        
        catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
		return table;

    }
    
    
    
    public Table parseCsvToFile(String path, String nameOfTable) throws Exception {
        String csvFile = path;
        BufferedReader br = null;
        String line = "";
        String cvsSplitBy = ",";
        int compteurColonne = 1;
        Table table = this.getTable();
        
        boolean firstLetterHeader = true;
        System.out.println("ok parser");
        try {
            System.out.println("ok parser");
            br = new BufferedReader(new FileReader(csvFile));
            line = br.readLine();
            String nameColonne = "";
            List<String> headersTable = new ArrayList<>();
            for (int i = 0; i < line.length(); i++) {
                if (line.charAt(i) == ',' ) {
                    compteurColonne++;
                    headersTable.add(nameColonne); 
                    //System.out.println("colonne test");
                    System.out.println(nameColonne);
                    nameColonne = "";
                    firstLetterHeader = true;
                    //i++;
                } else {
                	if(firstLetterHeader) {
                		if (line.charAt(i) == ' ') { 
                		}
                		else {
                			firstLetterHeader = false;
                    		nameColonne += line.charAt(i);
                		}
                	}
                	else {
                		nameColonne += line.charAt(i);
                		//System.out.println(nameColonne);
                	}
                }
            }
            //compteurColonne++;
            
           
            nameColonne = "";
            
            
            List<Object[]> listLinesAvailable = new ArrayList<>();
            List<Object[]> listLinesToBeSent = new ArrayList<>();
            System.out.println("Nb lignes parsees");
            int countLine = 0;
            while ((line = br.readLine()) != null) {
            	
            	if (listLinesAvailable.size() >= 1000000) {
            		table.addLines(listLinesAvailable);
                	listLinesAvailable = new ArrayList<>();
                	
                	if(this.repartisseur.otherNodes.size()>0) {
                    	this.repartisseur.sendLines(listLinesToBeSent, this.name);
                    }
                	
            	}
            	
            	
            	
            	
            	if (countLine%100000 ==  0){
            		System.out.println("Nb lignes parsees");
            		System.out.println(countLine);
            	}
            	countLine++;
            	nameColonne = "";
                int countWord = 0;
                boolean firstLetter = true;
                //System.out.println("test readline");

                String elementCSV[] = new String[compteurColonne];
                for (int i = 0; i < line.length(); i++) {
                    if (line.charAt(i) == ',') {
                        elementCSV[countWord] = nameColonne;
                        //System.out.println(nameColonne);
                        nameColonne = "";
                        firstLetter = true;
                        countWord++;
                        //i++;
                    } else {
                    	if(firstLetter) {
                    		if (line.charAt(i) == ' ') { 
                    		}
                    		else {
                    			firstLetter = false;
                        		nameColonne += line.charAt(i);
                    		}
                    	}
                    	else {
                    		nameColonne += line.charAt(i);
                    	}
                        
                    }
                }
                
                elementCSV[countWord] = nameColonne;
                /*System.out.println(cpt);
                System.out.println("voici 3 mots");
                System.out.println(elementCSV[0]);
                System.out.println(elementCSV[1]);
                System.out.println(elementCSV[2]);
                System.out.println("\n");*/
                //System.out.println(compteurColonne);
                if (elementCSV[compteurColonne-1] != null){
                	//System.out.println("Ajout ligne");
                	if(this.turnRepartition == 0) {
                		listLinesAvailable.add(elementCSV);
                		turnRepartition++;
                	}
                	else {
                		listLinesToBeSent.add(elementCSV);
                		this.turnRepartition ++;
                		if(this.turnRepartition >= this.repartisseur.otherNodes.size()) {
                			this.turnRepartition = 0;
                		}
                	}
                	
                	
                	
                } 
            }
            table.addLines(listLinesAvailable);
            if(this.repartisseur.otherNodes.size()>0) {
            	this.repartisseur.sendLines(listLinesToBeSent, this.name);
            }
            
            System.out.println("done!");
            

        } 
        
        
        catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
		return table;

    }

}