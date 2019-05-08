package Controllers;





import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;




public class Table implements Serializable {
    private String name;
    //private Map<String, Index> index = new HashMap<>();
    List<String> columnNames = new ArrayList<String>();
    HashMap<String, Index> listIndex = new HashMap<String, Index>();
    List<Object[]> data = new ArrayList<>();


    public Table(String name) {
        this.name = name;

    	System.out.println("Table créée : ");
    	System.out.println(this.name);
    	System.out.println("\n");
    }

    public void init(List<String> line0) {
    	//System.out.println("Table initilaisée : ");
        for (String row : line0) {
            this.columnNames.add(row);
            //System.out.println("Titres : ");
            //System.out.println(row);
        	//System.out.println("\n");
        }
    }

    public void addLines(List<Object[]> lines) {
    	System.out.println("Lignes ajoutées : ");
    	int llklk = 0;
        for (Object[] line : lines) {
        	/*System.out.println(line[0]);
        	System.out.println(line[1]);
        	System.out.println(line[2]);*/
        	//System.out.println(llklk );
        	llklk ++;
            data.add(line);
        }
        //System.out.println("nb elements");
        //System.out.println(data.size());

        for (String nameCurrentIndex : listIndex.keySet()) {
            for (Object[] line : lines) {
            	listIndex.get(nameCurrentIndex).insert(line);
            }
        }
    }

    public void addIndex(String nameIndex) {
    	//System.out.println("Ajout Index");
        int nbLine = 0;
        int placeOfValue = 0;
        for (String name : columnNames) {
            if (name.equals(nameIndex)) {
                placeOfValue = nbLine;
            } 
            nbLine++;
        }
        //System.out.println("nb elements");
        //System.out.println(data.size());
        Index newIndex = new Index(placeOfValue);
        listIndex.put(nameIndex, newIndex);
        int rr = 0;
        for (Object[] line : data) {
        	//System.out.println("Valeur indexee: "); 
        	//System.out.println(line[placeOfValue]);
        	//System.out.println(rr);
        	rr++;
        	newIndex.insert(line);	
        }
        System.out.println("\n");
    }
    
    public void searchBigger(String nameIndex, int valueMin) {
    	Index index = listIndex.get(nameIndex);
    	List<Object[]> results = index.searchBigger(valueMin);
    	for (Object[] line : results) {
    		//System.out.println(line);
    		
    	}
    }
    
    public void searchSmaller(String nameIndex, int valueMin) {
    	Index index = listIndex.get(nameIndex);
    	List<Object[]> results = index.searchSmaller(valueMin);
    	for (Object[] line : results) {
    		//System.out.println(line);
    	}
    }
    
    public void get(String nameIndex, String value) {
    	Index index = listIndex.get(nameIndex);
    	List<Object[]> results = index.get(value);
    	for (Object[] line : results) {
    		//System.out.println(line);
    		
    	}
    }
}