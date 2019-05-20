package Controllers;

import java.util.ArrayList;
import java.util.List;

public class Main {
	 public static void main(String[] args) throws Exception {
		 //System.out.println((int)5000/1000 == 5000/1000f);

		 
		 CsvParser csvReader = new CsvParser("/Users/David/Documents/GitHub/ProjetWeb/deniro.csv");
		 	//CsvParser csvReader = new CsvParser("/Users/David/Documents/GitHub/ProjetWeb/WebProjectEclipse/yellow_tripdata_2015-07.csv");
	        //csvReader.parseCsv("/Users/David/Documents/GitHub/ProjetWeb/deniro.csv");
		 	//CsvParser csvReader = new CsvParser("/Users/David/Desktop/L3 DANT/S2/Web_O_Pitton/green_tripdata_2018-01.csv");
		 	//CsvParser csvReader1 = new CsvParser("/Users/David/Desktop/L3 DANT/S2/Web_O_Pitton/green_tripdata_2018-01.csv");
		 	//CsvParser csvReader2 = new CsvParser("/Users/David/Desktop/L3 DANT/S2/Web_O_Pitton/green_tripdata_2018-01.csv");
		 	//CsvParser csvReader3 = new CsvParser("/Users/David/Desktop/L3 DANT/S2/Web_O_Pitton/green_tripdata_2018-01.csv");
		 	//CsvParser csvReader4 = new CsvParser("/Users/David/Desktop/L3 DANT/S2/Web_O_Pitton/green_tripdata_2018-01.csv");
		 	//CsvParser csvReader5 = new CsvParser("/Users/David/Desktop/L3 DANT/S2/Web_O_Pitton/green_tripdata_2018-01.csv");
	        Table table = csvReader.getTable();
	        
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
	        



	    }
}
