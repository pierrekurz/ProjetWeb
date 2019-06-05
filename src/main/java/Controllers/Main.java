package Controllers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;

public class Main {
	 public static void main(String[] args) throws Exception {
		 //System.out.println((int)5000/1000 == 5000/1000f);

		 
		 /*
		 	//CsvParser csvReader = new CsvParser("/Users/David/Documents/GitHub/ProjetWeb/WebProjectEclipse/yellow_tripdata_2015-07.csv");
	        //csvReader.parseCsv("/Users/David/Documents/GitHub/ProjetWeb/deniro.csv");
		 	//CsvParser csvReader = new CsvParser("/Users/David/Desktop/L3 DANT/S2/Web_O_Pitton/green_tripdata_2018-01.csv");
		 	//CsvParser csvReader1 = new CsvParser("/Users/David/Desktop/L3 DANT/S2/Web_O_Pitton/green_tripdata_2018-01.csv");
		 	//CsvParser csvReader2 = new CsvParser("/Users/David/Desktop/L3 DANT/S2/Web_O_Pitton/green_tripdata_2018-01.csv");
		 	//CsvParser csvReader3 = new CsvParser("/Users/David/Desktop/L3 DANT/S2/Web_O_Pitton/green_tripdata_2018-01.csv");
		 	//CsvParser csvReader4 = new CsvParser("/Users/David/Desktop/L3 DANT/S2/Web_O_Pitton/green_tripdata_2018-01.csv");
		 	//CsvParser csvReader5 = new CsvParser("/Users/David/Desktop/L3 DANT/S2/Web_O_Pitton/green_tripdata_2018-01.csv");
	       
	        
	        //System.out.println("kkl");
	        //System.out.println(table.data.size());
	        //table.addIndex("Score");
	        List<String> nameOfIndex1 = new ArrayList<String>();
	        nameOfIndex1.add("Score");
	        nameOfIndex1.add("Title");
	        
	        List<String> nameOfIndex2 = new ArrayList<String>();
	        nameOfIndex1.add("jk");
	        nameOfIndex1.add("Title");
	        
	        //table.addIndex(nameOfIndex1);
	        List<String> nameOfMovie = new ArrayList<String>();
	        nameOfMovie.add("50");
	        nameOfMovie.add("\"Stone\"");
	        
	        
	        //System.out.println(nameOfIndex1);
	        //table.searchBigger("Score", 99);
	        //table.searchSmaller("Score", 10);
	        //table.get(nameOfIndex1, nameOfMovie);
	        table.get(nameOfIndex1, nameOfMovie);
	        */
		 
		 /*FileManager fileManager = new FileManager();
		 List<String> nameHeaders = new ArrayList<String>();
		 nameHeaders.add("premier");
		 nameHeaders.add("second");
		 nameHeaders.add("troisieme");
		 nameHeaders.add("quatrieme");
		 
		 fileManager.createFile("premierFichier", nameHeaders);
		 
		 List<Object[]> listeLine1 = new ArrayList<Object[]>();
		 String[] line1 = {"bonjour", "au revoir", "demain", "kk"};
		 String[] line2 = {"1", "22", "333", "44"};
		 
		 
		 
		 listeLine1.add(line1);
		 listeLine1.add(line2);
		 listeLine1.add(line1);
		 listeLine1.add(line2);
		 listeLine1.add(line1);
		 listeLine1.add(line2);
		 
		 fileManager.writeLines("premierFichier",listeLine1 );
		 List<Integer> lineToRead = new ArrayList<Integer>();
		 lineToRead.add(0);
		 //System.out.println("kkl1");
		 List<HashMap<String, String>> returnsReads = fileManager.readLines("premierFichier", lineToRead);
		 //System.out.println("kkl");
		 for (HashMap<String, String> toRead :returnsReads ) {
			 //System.out.println("kkl2");
			 for(Entry<String, String> word : toRead.entrySet() ) {
				// System.out.println("kkl3");
				 System.out.println(word.getKey());
				 //System.out.println("kkl4");
				 System.out.println(word.getValue());
				 
			 }
			 
		 }
		 */
		 
		 
		 //CsvParser csvReader = new CsvParser("/Users/David/Documents/GitHub/ProjetWeb/deniro.csv");
		 
		 
		CsvParser csvReader = new CsvParser("tab1","yellow_tripdata_2015-08.csv", null);
		//Table table = csvReader.getTable();   
		
		//CsvParser csvReader = new CsvParser("tab1", "/Users/David/Desktop/L3 DANT/S2/Web_O_Pitton/green_tripdata_2018-01.csv");
		Table table = csvReader.getTable(); 
		List<String> nameIndex = new ArrayList<String>();
		nameIndex.add("class_0");
		table.addIndex(nameIndex);
		List<String> nameVendor = new ArrayList<String>();
		nameVendor.add("2.43");
		table.get(nameIndex, nameVendor);
		 
		 
		 
		 
		 
		 
		 
		 
		 
		 
		 
		 
		 
		 
		 

	    }
}
