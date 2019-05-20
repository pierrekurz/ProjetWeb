package Controllers;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CsvParser {
	Table table;
	public CsvParser(String path) throws Exception {
		this.table = parseCsv(path);
	}
	
	public Table getTable(){
		return this.table;
	}
	
    public static Table parseCsv(String path) throws Exception {
        // "C:\\Users\\Nguye\\OneDrive\\Documents\\dant-master\\test.csv";
        String csvFile = path;
        BufferedReader br = null;
        String line = "";
        String cvsSplitBy = ",";
        int compteurColonne = 1;
        int cpt = 0;
        Table table = new Table("table generale", true);
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
            System.out.println(nameColonne);
            table.init(headersTable);
            /*System.out.println("kkk 1");
            System.out.println(nameColonne);
            System.out.println(headersTable.size());
            */
            
            nameColonne = "";
            
            
            List<Object[]> listLinesAvailable = new ArrayList<>();
            int countLine = 0;
            while ((line = br.readLine()) != null) {
            	if (countLine<3800000) {
	            	if (countLine/100000f == (int)countLine/100000) {
	            		System.out.println(countLine);
	            	}
            	}
            	else {
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
                	listLinesAvailable.add(elementCSV);
                }
                else {
                	/*System.out.println(nameColonne);
                	System.out.println("niark");*/
                }
                cpt++;
                //System.out.println(nameColonne);
            }
            System.out.println("done!");
            table.addLines(listLinesAvailable);

            



        } catch (FileNotFoundException e) {
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
