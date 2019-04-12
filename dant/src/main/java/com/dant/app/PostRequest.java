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
@Path("/create")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class PostRequest {
	final lecteurTable l = new lecteurTable();

	static Map<String, Table> tables = new HashMap<String, Table>();

	@POST
	@Path("/parsecsv")
	public void parseCsv(@QueryParam("path") String path) throws Exception {
		Table t = CsvParser.parseCsv(path);
		tables.put(t.getName(), t);
	}

}
