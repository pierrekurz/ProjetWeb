package Controllers;





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
    HashMap<String, Index> listIndex = new HashMap<String, Index>();
    List<Object[]> data = new ArrayList<>();
    Repartisseur repartisseur;
    int nodeTurn;


    public Table(String name, Boolean mainNode) {
        this.name = name;
        this.mainNode = mainNode;
        this.repartisseur = new Repartisseur(mainNode);
        this.nodeTurn = 0;
    	System.out.println("Table créée : ");
    	System.out.println(this.name);
    	System.out.println("\n");
    }

    public void init(List<String> line0) {
    	//System.out.println("Table initialisée : ");
        for (String row : line0) {
            this.columnNames.add(row);
            //System.out.println("Titres : ");
            //System.out.println(row);
        	//System.out.println("\n");
        }
    }

    public void addLines(List<Object[]> lines) {
    	if (mainNode) {
    		// this is the main node
    		if (this.nodeTurn != 0) {
    			//it's not this node's turn to record the line
    			repartisseur.addLine(lines,nodeTurn );
    			return;
    		}
    		this.nodeTurn += 1;
    		// next node's turn
    		if (this.nodeTurn == this.repartisseur.otherNodes.size()) {
    			this.nodeTurn = 0;
    		}
    	}
    	
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

    public void addIndex(String nameIndex) throws MalformedURLException {
    	if (this.mainNode == true) {
    		// need to distribute the instruction
    		repartisseur.addIndex(nameIndex);
    	}
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
    
    public List<Object[]> searchBigger(String nameIndex, int valueMin) throws MalformedURLException {
    	
    	Index index = listIndex.get(nameIndex);
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
    
    public void get(String nameIndex, String value) {
    	Index index = listIndex.get(nameIndex);
    	List<Object[]> results = index.get(value);
    	for (Object[] line : results) {
    		//System.out.println(line);	
    	}
    	if (this.mainNode) {
    		// need to distribute the instruction
    		results.addAll(repartisseur.get(nameIndex, value));
    	}
    }
}