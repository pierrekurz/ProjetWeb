package com.dant.app;
import com.dant.entity.Account;
import com.dant.entity.Colonne;
import com.dant.entity.Ligne;
import com.dant.entity.Table;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

/**
 * Created by pitton on 2017-02-20.
 */
@Path("/api")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class Endpoint {
	static Map<String,Table> tables=new HashMap<String,Table>();
	
	@GET
	@Produces(MediaType.TEXT_HTML)
	public String helloWorld() {
		return "Hello World";
	}
	
	@PUT
	@Path("/newtable")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public void tableCreated(@QueryParam("name") String name,Ligne ligne) {
	Table t = new Table(name);
		if(!tables.containsKey(name)) {
			tables.put(name,t);
			System.out.println("Table " + name + " crée");
			System.out.println("ligne " + ligne + " crée");

		}
		System.out.println(tables.toString());
	}
	
	
	@PUT
	@Path("/{tablename}/newColonne")
	@Produces(MediaType.TEXT_HTML)
	@Consumes(MediaType.APPLICATION_JSON)
	public void colonneCreated(@PathParam("tablename") String tablename,@QueryParam("colonnename") String colonnename,@QueryParam("colonnetype") String colonnetype ) {
		Colonne c = new Colonne(colonnename,colonnetype);
		Table t=tables.get(tablename);
				t.addColonne(c);
				System.out.println("Colonne ajoutée name : " + c.name +" type : " + c.type + " dans la table : " + tablename);
		
		}
	
	/*
	@PUT
	@Path("/{tablename}/insert")
	@Produces(MediaType.TEXT_HTML)
	@Consumes(MediaType.APPLICATION_JSON)
	public void insert(@PathParam("tablename") String tablename) {
		System.out.println("Received account " + account);
		Table t=tables.get(tablename);
		
		t.insert(account);
		
	}
			*/
	@GET
	@Path("/list")
	public List<String> getListInParams(@QueryParam("ids") List<String> ids) {
		System.out.println(ids);
		return ids;
	}


	@GET
	@Path("/exception")
	public Response exception() {
		throw new RuntimeException("Mon erreur");
	}
	
	@POST
	@Path("/entity")
	public Account getAccount(Account account) {
		System.out.println("Received account " + account);
		account.setUpdated(System.currentTimeMillis());
		return account;
	}

}
