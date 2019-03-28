package com.dant.entity;

import java.io.Serializable;

public class Ligne implements Serializable{
	String name;
	
	public String getName() {
		return this.name;
	}
	public void setName(String name) {
		this.name= name;
	}
	@Override
	public int hashCode() {
		return name.hashCode();
	}

	@Override
	public String toString() {
		return name;
	}
}
