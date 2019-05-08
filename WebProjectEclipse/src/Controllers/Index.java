package Controllers;




import java.util.HashMap;
import java.util.Map;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Index{
    int nbElementToIndex;

    public Index(int nbElement) {
         this.nbElementToIndex = nbElement;
    }

    List<Object[]> lines = new ArrayList<Object[]>();
    Map<String,List<Integer>> index = new HashMap<String, List<Integer>>();

    public void insert(Object[] line) {
        int nbLines = lines.size();
        lines.add(line);
        List<Integer> rows=index.get(line[this.nbElementToIndex]);
        if(rows==null) {
            rows=new ArrayList<Integer>();
            //System.out.println(line[this.nbElementToIndex]);
            if (line!= null) {
            	String valueToAdd = line[this.nbElementToIndex].toString();
            	index.put(valueToAdd, rows);
            }
        }
        //System.out.println(line[this.nbElementToIndex]);
        rows.add(nbLines);
    }
       
    
    public List<Object[]> get(String key) {
        List<Integer> rows = index.get(key);
        if(rows==null) {
            return null;
        }
        List<Object[]> result = new ArrayList<>();
        for(Integer row : rows) {
            result.add(lines.get(row));
            System.out.println(lines.get(row)[2]);
            

        }
        return result;

    }
    
    
    public List<Object[]> searchBigger(int valueMin) {
    	List<Object[]> result = new ArrayList<Object[]>() ;
    	for (String key : index.keySet()) {
    		if(Integer.parseInt(key) >= valueMin) {
    			for (Integer nbLine : index.get(key)) {
    				result.add(lines.get(nbLine));
    				System.out.println(lines.get(nbLine)[2]);
    			}	
    		}
        }
    	//System.out.println("\n");
    	//System.out.println(result.size());
    	
        return result;
    }
    
    public List<Object[]> searchSmaller(int valueMax) {
    	List<Object[]> result = new ArrayList<Object[]>() ;
    	for (String key : index.keySet()) {
    		if(Integer.parseInt(key) <= valueMax) {
    			for (Integer nbLine : index.get(key)) {
    				result.add(lines.get(nbLine));
    				System.out.println(lines.get(nbLine)[2]);
    			}	
    		}
        }
    	System.out.println("\n");
    	System.out.println(result.size());
    	
        return result;
    }
        
    
    public List<Object[]> getWithoutIndex(String key){ // exemple sans index

        List<Object[]> res = new ArrayList<Object[]>();
        for(Object[] line : lines) {
            if(line[0].equals(key)) {
                res.add(line);
            }
        }
        return res;
    }
}
