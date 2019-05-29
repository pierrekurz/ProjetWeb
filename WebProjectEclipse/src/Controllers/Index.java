package Controllers;




import java.util.HashMap;
import java.util.Map;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Index{
	List<Integer> nbElementToIndex;
	List<String> nameIndex;
	FileManager fileManager;
	String nameFile;

    public Index(List<Integer> nbElement, List<String> nameIndex, String nameFile) {
         this.nbElementToIndex = nbElement;
         this.nameIndex = nameIndex;
         this.fileManager = new FileManager();
         this.nameFile = nameFile;
         
    }

    //List<Object[]> lines = new ArrayList<Object[]>();
    Map<List<Object>,List<Integer>> index = new HashMap<List<Object>, List<Integer>>();

    public void insert(HashMap<String, String> line, int nbOfLine) {
        //int nbLines = lines.size();
        //lines.add(line);
    	List<Object> valueOfLine = new ArrayList<Object>();
        
    	for (String nameToIndex : nameIndex) {
    		valueOfLine.add(line.get(nameToIndex));	
    	}
    	
    	
    	//List<Object> valueInRow = new ArrayList<Object>();
        //int ind = 0;
        //boolean oneNull = true;
        //List<Integer> rows = new ArrayList<Integer>();
        
        List<Integer> rows = index.get(valueOfLine);
        if(rows==null) {
            rows=new ArrayList<Integer>();
            //System.out.println(line[this.nbElementToIndex]);
            if (line!= null) {
            	index.put(valueOfLine, rows);
            }
        }
        //System.out.println(line[this.nbElementToIndex]);
        rows.add(nbOfLine);
    }
       
    
    public List<HashMap<String, String>> get(List<String> nameOfMovie) {
        List<Integer> rows = index.get(nameOfMovie);
        System.out.println(nameOfMovie);
        System.out.println(index);
        if(rows==null) {
            return null;
        }
        List<HashMap<String, String>> result = new ArrayList<>();
        
        int k=0;
        List<Integer> row = new ArrayList<Integer>();
        while(k<rows.size()) {
        	row.add(rows.get(k));
        	if (row.size()==10000) {
        		result.addAll(this.fileManager.readLines(this.nameFile, row));
        		row = new ArrayList<Integer>();
        	}
        	k++;
        } 		
           
        return result;

    }
    
    
    public List<HashMap<String, String>> searchBigger(int valueMin) {
    	List<HashMap<String, String>> result = new ArrayList<HashMap<String, String>>();
    	for (List<Object> key : index.keySet()) {
    		if(Integer.parseInt((String) key.get(0)) >= valueMin) {
    			List<Integer> numbersToGet = index.get(key);
    		}
        }
    	//System.out.println("\n");
    	//System.out.println(result.size());
    	
        return result;
    }
    
    public List<Object[]> searchSmaller(int valueMax) {
    	List<Object[]> result = new ArrayList<Object[]>() ;
    	for (List<Object> key : index.keySet()) {
    		if(Integer.parseInt((String) key.get(0)) <= valueMax) {
    			List<Integer> numbersToGet = index.get(key);
    				
    		}
        }
    	System.out.println("\n");
    	System.out.println(result.size());
    	
        return result;
    }
        
    
   
}
