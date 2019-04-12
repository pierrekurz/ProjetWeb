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
@Path("/insert")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class PutRequest {
	final lecteurTable l = new lecteurTable();

	static Map<String, Table> tables = new HashMap<String, Table>();

	

	


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

}
