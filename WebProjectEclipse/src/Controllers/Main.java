package Controllers;

public class Main {
	 public static void main(String[] args) throws Exception {
	        CsvParser csvReader = new CsvParser("/Users/David/Documents/GitHub/ProjetWeb/deniro.csv");
	        //csvReader.parseCsv("/Users/David/Documents/GitHub/ProjetWeb/deniro.csv");
	        Table table = csvReader.getTable();
	        table.addIndex("Score");
	        table.addIndex("Title");
	        //table.searchBigger("Score", 99);
	        table.searchSmaller("Score", 10);
	        table.get("Title", "\"Godsend\"");
	        



	    }
}
