package com.dant.entity;

import java.util.HashMap;
import java.util.Map;

package testIndex;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Index {

	List<Object[]> lines;
	Map<String,List<Integer>> index;
	
	public void insert(Object[] line) {
		int nbLines = lines.size();
		lines.add(line);
		List<Integer> rows=index.get(line[0]);
		if(rows==null) {
			rows=new ArrayList<Integer>();
			index.put(line[0], rows);
		}
		rows.add(nbLines);
	}
	public List<Object[]> get(String key) {
		List<Integer> rows = index.get(key);
		if(rows==null) {
			return null;
		}
		List<Object> result = new ArrayList<Object>();
		for(Integer row : rows) {
			result.add(lines.get(row));
			
		}
		return result;
		
	}
	public List<Object[]> getWithoutIndex(String key){
		List<Object[]> res = new ArrayList<Object[]>();
		for(Object[] line : lines) {
			if(line[0].equals(key)) {
				res.add(line);
			}
		}
		return res;
	}
}
