package com.dant.entity;




import java.util.HashMap;
import java.util.Map;

import com.google.gson.Gson;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Index implements Serializable{
	List<Integer> nbElementToIndex;

    public Index(List<Integer> nbElement) {
         this.nbElementToIndex = nbElement;
    }

    List<Object[]> lines = new ArrayList<Object[]>();
    Map<List<Object>,List<Integer>> index = new HashMap<List<Object>, List<Integer>>();

    public void insert(Object[] line) {
        int nbLines = lines.size();
        lines.add(line);
        List<Object> valueInRow = new ArrayList<Object>();
        int ind = 0;
        boolean oneNull = true;
        //List<Integer> rows = new ArrayList<Integer>();
        for(Integer nbIndex : this.nbElementToIndex){
        	Object valueToAdd = line[this.nbElementToIndex.get(ind)];
        	valueInRow.add(valueToAdd);
        	ind++;        	
        }
        List<Integer> rows = index.get(valueInRow);
        if(rows==null) {
            rows=new ArrayList<Integer>();
            //System.out.println(line[this.nbElementToIndex]);
            if (line!= null) {
            	index.put(valueInRow, rows);
            }
        }
        //System.out.println(line[this.nbElementToIndex]);
        rows.add(nbLines);
    }
       
    
    public List<Object[]> get(List<String> nameOfMovie) {
        List<Integer> rows = index.get(nameOfMovie);
        System.out.println(nameOfMovie);
        System.out.println(index);
        if(rows==null) {
            return null;
        }
        List<Object[]> result = new ArrayList<>();
        for(Integer row : rows) {
            result.add(lines.get(row));
            //System.out.println(lines.get(row)[2]);
            System.out.println(row);
            

        }
        return result;

    }
    
    
    public List<Object> searchBigger(int valueMin) {
    	List<Object> result = new ArrayList<Object>() ;
    	for (List<Object> key : index.keySet()) {
    		if(Integer.parseInt((String) key.get(0)) >= valueMin) {
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
    
    public List<Object> searchSmaller(int valueMax) {
    	List<Object> result = new ArrayList<Object>() ;
    	for (List<Object> key : index.keySet()) {
    		if(Integer.parseInt((String) key.get(0)) <= valueMax) {
    			for (int nbLine : index.get(key)) {
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
    public String toString() {
    	Gson gson=new Gson();
    	return gson.toJson(this);
    }
}
