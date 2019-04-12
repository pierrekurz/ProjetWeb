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


@Path("/api/table")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)

public class TableController {
	final lecteurTable l = new lecteurTable();


	@POST
	@Path("/parsecsv")
	public void parseCsv(@QueryParam("path") String path) throws Exception {
		Table t = CsvParser.parseCsv(path);
		TableService.tables.put(t.getName(), t);
	}
	
	@PUT
	@Path("/newtable")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public void tableCreated(@QueryParam("name") String name, Ligne ligne) {
		Table t = new Table(name);
		if (!TableService.tables.containsKey(name)) {
			TableService.tables.put(name, t);
			System.out.println("Table " + name + " creee");
			System.out.println("ligne " + ligne + " creee");

		}
		System.out.println(TableService.tables.toString());
	}
	@PUT
	@Path("/{tablename}/newcolonne")
	@Produces(MediaType.TEXT_HTML)
	@Consumes(MediaType.APPLICATION_JSON)
	public void colonneCreated(@PathParam("tablename") String tablename, @QueryParam("colonnename") String colonnename,
			@QueryParam("colonnetype") String colonnetype) {
		Colonne c = new Colonne(colonnename, colonnetype);
		Table t = TableService.tables.get(tablename);
		try {
			t.addColonne(colonnename, colonnetype);
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println(
				"Colonne ajoutee name : " + c.getName() + " type : " + c.getType() + " dans la table : " + tablename);

	}


	
}
