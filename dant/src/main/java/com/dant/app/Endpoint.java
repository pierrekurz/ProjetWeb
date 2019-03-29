package com.dant.app;

import com.dant.entity.Account;
import com.dant.entity.Colonne;
import com.dant.entity.CsvParser;
import com.dant.entity.Ligne;
import com.dant.entity.Table;
import com.dant.entity.lecteurTable;

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
	final lecteurTable l = new lecteurTable();

	static Map<String, Table> tables = new HashMap<String, Table>();

	@GET
	@Produces(MediaType.TEXT_HTML)
	public String helloWorld() {
		return "Hello World";
	}

	@PUT
	@Path("/newtable")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public void tableCreated(@QueryParam("name") String name, Ligne ligne) {
		Table t = new Table(name);
		if (!tables.containsKey(name)) {
			tables.put(name, t);
			System.out.println("Table " + name + " cr�e");
			System.out.println("ligne " + ligne + " cr�e");

		}
		System.out.println(tables.toString());
	}

	@PUT
	@Path("/{tablename}/newColonne")
	@Produces(MediaType.TEXT_HTML)
	@Consumes(MediaType.APPLICATION_JSON)
	public void colonneCreated(@PathParam("tablename") String tablename, @QueryParam("colonnename") String colonnename,
			@QueryParam("colonnetype") String colonnetype) {
		Colonne c = new Colonne(colonnename, colonnetype);
		Table t = tables.get(tablename);
		try {
			t.addColonne(colonnename, colonnetype);
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println(
				"Colonne ajout�e name : " + c.getName() + " type : " + c.getType() + " dans la table : " + tablename);

	}




	/*
	 * @PUT
	 * 
	 * @Path("/{tablename}/insert")
	 * 
	 * @Produces(MediaType.TEXT_HTML)
	 * 
	 * @Consumes(MediaType.APPLICATION_JSON) public void
	 * insert(@PathParam("tablename") String tablename) {
	 * System.out.println("Received account " + account); Table
	 * t=tables.get(tablename);
	 * 
	 * t.insert(account);
	 * 
	 * }
	 */
	@GET
	@Path("/list")
	public List<String> getListInParams(@QueryParam("ids") List<String> ids) {
		System.out.println(ids);
		return ids;
	}

	@GET
	@Path("/readTable")
	public void readTable(@QueryParam("nameTable") String nameTable, @QueryParam("nameColumn") String nameColumn,
			@QueryParam("element") String element) {
		Table t = tables.get(nameTable);
		l.lecteurTable(t, nameColumn, element);
	}


	@GET
	@Path("/getElementsFigures")
	public void readTable(@QueryParam("nameTable") String nameTable, @QueryParam("nameColumn") String nameColumn,
			@QueryParam("condition") String condition, @QueryParam("value") String value) {
		Table t = tables.get(nameTable);
		//l.getLinesByConditionsNumber(t, nameColumn, condition, value);
	}


	@GET
	@Path("/getElementsByColumns")
	public void readTable(@QueryParam("nameTable") String nameTable, @QueryParam("nameColumn") List<String> nameColumns,
			@QueryParam("element") String element) {
		Table t = tables.get(nameTable);
		//l.getLinesByConditionsNumber(t, nameColumns, nameColumns);
	}

	@GET
	@Path("/exception")
	public Response exception() {
		throw new RuntimeException("Mon erreur");
	}

	@POST
	@Path("/parsecsv")
	public void parseCsv() throws Exception {
		CsvParser.parseCsv("/Users/kurzed/Desktop/NTWeb/ProjetWeb/deniro.csv");
	}

	@POST
	@Path("/entity")
	public Account getAccount(Account account) {
		System.out.println("Received account " + account);
		account.setUpdated(System.currentTimeMillis());
		return account;
	}

}
