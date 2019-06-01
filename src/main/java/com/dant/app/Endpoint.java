package com.dant.app;


import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.jboss.logging.Logger;

import com.dant.entity.Table;
//import org.json.simple.JSONObject;
import com.google.gson.*;

import Controllers.CsvParser;
import Controllers.Repartisseur;

/**
 * Created by pitton on 2017-02-20.
 */


@Path("/api")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class Endpoint {
	CsvParser csvReader=null;
	Repartisseur repartisseur = new Repartisseur(true);
	@POST
	@Path("/createMainNode")
	public void initNodes() throws Exception {
		System.out.println("test create mainNode");

		repartisseur = new Repartisseur(true);
	}
	
	@POST
	@Path("/joinMainNode")
	public void joinNodes() throws Exception {
		System.out.println("test join mainNode");
		repartisseur = new Repartisseur(false);
	}
	
	@GET
	@Path("/test")
	@Produces(MediaType.TEXT_HTML)
	public String helloWorld() {
		return "<b>Hello World</b>";
	}
	
	@POST
	@Path("/parse")
	//@Produces(MediaType.TEXT_HTML)
	@Consumes("text/csv")
	public Response parseCsv() throws Exception {
		//File file= new File("yellow_tripdata_2009-01.csv");
		File file= new File("/");
		String path=file.getAbsolutePath();
		System.out.println(path);
		repartisseur.ParceCSV(file.getAbsolutePath());
		csvReader = new CsvParser("parser", "yellow_tripdata_2009-01.csv");
		System.out.println("Fichier Parse");
		return Response.status(200).entity("ok").build();
	}
	
	
	@PUT
	@Path("/addIndex/{nameIndex}")
	@Produces(MediaType.TEXT_HTML)
	@Consumes(MediaType.APPLICATION_JSON)
	public void createIndex(@QueryParam("nameIndex")  String nameIndex) throws Exception {
		
		List<String> listNameColumns = new ArrayList<String>();
		//for (String word : nameIndex ) {
			//listNameColumns.add(word);
		//}
		repartisseur.addIndex(listNameColumns);
		System.out.println("Index ajoute");
	}
	
	@PUT
	@Path("/searchSmaller")
	@Produces(MediaType.TEXT_HTML)
	@Consumes(MediaType.APPLICATION_JSON)
	public List<Object[]> searchSmaller(
			@QueryParam("nameIndex") String nameIndex,
			@QueryParam("valueMax")  int valueMax
			) throws Exception {
		List<Object[]> listNbLines = repartisseur.searchSmaller(nameIndex, valueMax);
		return listNbLines;
		//return (listNbLines.);
	}
	
	@PUT
	@Path("/searchBigger")
	@Produces(MediaType.TEXT_HTML)
	@Consumes(MediaType.APPLICATION_JSON)
	public List<Object[]> searchBigger(
			@QueryParam("nameIndex") String nameIndex,
			@QueryParam("valueMin")  int valueMin
			) throws Exception {
		List<Object[]> listNbLines = repartisseur.searchSmaller(nameIndex, valueMin);
		return listNbLines;
		//return (listNbLines.toJson());
	}
	
	@GET
	@Path("/tables")
	public List<Table> getTables() {
		return repartisseur.getListTables();
		
	}
	
	@GET
	@Path("/download")
	public Response getDl() {
		return Response.status(200).entity("ok").build();
	}
	
	
		
}
