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
public class GetRequest {
	final lecteurTable l = new lecteurTable();
	static Map<String, Table> tables = new HashMap<String, Table>();

	@GET
	@Produces(MediaType.TEXT_HTML)
	public String helloWorld() {
		return "Hello World";
	}

	@GET
	@Path("/readtable")
	public void readTable(@QueryParam("nameTable") String nameTable, @QueryParam("nameColumn") String nameColumn,
			@QueryParam("element") String element) {
		Table t = tables.get(nameTable);
		l.liretable(t, nameColumn, element);
	}


	@GET
	@Path("/lineresult")
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

}
