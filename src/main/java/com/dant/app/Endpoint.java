package com.dant.app;


import java.io.BufferedReader;
import java.io.File;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Request;
import javax.ws.rs.core.Response;

import org.apache.http.protocol.RequestContent;
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
	Repartisseur repartisseur = new Repartisseur(false) ;
	@POST
	@Path("/createMainNode")
	public void initNodes() throws Exception {
		System.out.println("test create mainNode");
		this.repartisseur = new Repartisseur(true);
		System.out.println(this.repartisseur);
	}
	
	@POST
	@Path("/joinMainNode")
	@Produces(MediaType.TEXT_HTML)
	@Consumes(MediaType.APPLICATION_JSON)
	public void joinNodes(@QueryParam("mainPort") int mainPort, @QueryParam("portNb") int portSecond) throws Exception {
		System.out.println("test join mainNode");
		this.repartisseur = new Repartisseur(false);
		this.repartisseur.joinMainNode(mainPort, portSecond);
		
	}
	
	
	@GET
	@Path("/connect")
	@Produces(MediaType.TEXT_HTML)
	@Consumes(MediaType.APPLICATION_JSON)
	public void connect(
			@QueryParam("portNb") int portSecond
			) throws Exception {
		System.out.println("New venant");
		repartisseur.addNode(portSecond);
		
		System.out.println("the new is added");
	}
	
	
	@POST
	@Path("/parse")
	//@Produces(MediaType.TEXT_HTML)
	//@Consumes("text/csv")
	public Response parseCsv(
			@QueryParam("pathToFile") String pathToFile, 
			@QueryParam("nameOfFile") String nameOfFile
			) throws Exception {
		//csvReader = new CsvParser(pathToFile, nameOfFile);
		System.out.println("Fichier Parse1");
		this.repartisseur.ParceCSV(nameOfFile, pathToFile, this.repartisseur);
		//repartisseur.getListTables().add(csvReader.getTable());

		System.out.println("Fichier Parse");
		return Response.status(200).entity("ok").build();
	}
	
	@POST
	@Path("/addLinesFromFile")
	//@Produces(MediaType.TEXT_HTML)
	//@Consumes(MediaType.APPLICATION_JSON)
	public Response addLinesFromFile(
			@QueryParam("path") String path, 
			@QueryParam("nameFile") String nameFile
			) throws Exception {
		
		
		//System.out.println(lines);
		repartisseur.addLinesFromFile(path, nameFile);
		System.out.println("end added");
		return Response.status(200).entity("ok").build();
		
	}
	
	
	@POST
	@Path("/headers")
	@Produces(MediaType.TEXT_HTML)
	@Consumes(MediaType.APPLICATION_JSON)
	public void headers(
			@QueryParam("listHeaders") String listHeaders, 
			@QueryParam("nameTable") String nameTable
			) throws Exception {
		repartisseur.addHeader(listHeaders, nameTable);
	}
	
	
	
	@GET
	@Path("/test")
	@Produces(MediaType.TEXT_HTML)
	public String helloWorld() {
		
		return "<b>Hello World</b>";
	}
	
	
	@POST
	@Path("/addLines")
	//@Produces(MediaType.TEXT_HTML)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response addLines(
			@QueryParam("lines") String lines, 
			@QueryParam("nameFile") String nameFile, 
			String JSON
			) throws Exception {
		
		System.out.println("JSON");
		System.out.println("end added");
		//System.out.println(lines);
		repartisseur.addLines(lines, nameFile);
		System.out.println("end added");
		return Response.status(200).entity("ok").build();
		
	}
	
	
	
	
	
	@POST
	@Path("/addIndex")
	@Produces(MediaType.TEXT_HTML)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response createIndex(
			@QueryParam("nameIndex")  String nameIndex, 
			@QueryParam("nameFile")  String nameFile
			
			) throws Exception {
		
		List<String> listNameColumns = new ArrayList<String>();
		
		
		
		int sizeOfIndex = nameIndex.length();
		String word = "";
		for(int l = 0; l<sizeOfIndex; l++) {
			if(nameIndex.charAt(l) == '/') {
				listNameColumns.add(word);
				word = "";
				
			}
			else {
				word+=nameIndex.charAt(l);
			}
		}
		listNameColumns.add(word);
	
		System.out.println(listNameColumns);

			System.out.println("Index ajoute");
		this.repartisseur.addIndex(listNameColumns, nameFile);
		
		
		System.out.println(repartisseur.getListTables().get(0).toString());
		return Response.status(200).entity("ok").build();
 
	}
	
	
	
	
	
	
	@GET
	@Path("/get")
	@Produces(MediaType.TEXT_HTML)
	@Consumes(MediaType.APPLICATION_JSON)
	public String get(
			
			@QueryParam("nameTable") String nameTable, 
			@QueryParam("nameIndex") String nameIndex,
			@QueryParam("value")  String value
			) throws Exception {
		List<String> listIndex=new ArrayList<String>();
		List<String> listValue = new ArrayList<String>();
		
		int sizeOfIndex = nameIndex.length();
		String word = "";
		for(int l = 0; l<sizeOfIndex; l++) {
			if(nameIndex.charAt(l) == '/') {
				listIndex.add(word);
				word = "";

			}
			else {
				word+=nameIndex.charAt(l);
			}
		}
		listIndex.add(word);
		
		
		int sizeOfValue = value.length();
		String wordValue = "";
		for(int l = 0; l<sizeOfValue; l++) {
			if(value.charAt(l) == '/') {
				listValue.add(wordValue);
				wordValue = "";
				
			}
			else {
				
				wordValue+=value.charAt(l);
			}
		}
		
		listValue.add(wordValue);
		
		System.out.println("Index ajoute");	
		String results = this.repartisseur.get(nameTable, listIndex, listValue);
		return results;
	}
	
	
	
	
	
	@PUT
	@Path("/searchSmaller")
	@Produces(MediaType.TEXT_HTML)
	@Consumes(MediaType.APPLICATION_JSON)
	public List<Object[]> searchSmaller(
			@QueryParam("nameIndex") String nameIndex,
			@QueryParam("valueMax")  int valueMax
			) throws Exception {
		List<Object[]> listNbLines = new ArrayList<Object[]>();
		//List<Object[]> listNbLines = repartisseur.searchSmaller(nameIndex, valueMax);
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
		List<Object[]> listNbLines = new ArrayList<Object[]>();
		//List<Object[]> listNbLines = repartisseur.searchSmaller(nameIndex, valueMin);
		return listNbLines;
		//return (listNbLines.toJson());
	}
	
	@GET
	@Path("/tables")
	public HashMap<String, Controllers.Table> getTables() {
		return repartisseur.getListTables();
		
	}
	
	@GET
	@Path("/download")
	public Response getDl() {
		return Response.status(200).entity("ok").build();
	}
	
	
		
}