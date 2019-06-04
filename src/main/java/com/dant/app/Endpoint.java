package com.dant.app;


import java.io.File;
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
	@Produces(MediaType.TEXT_HTML)
	@Consumes(MediaType.APPLICATION_JSON)
	public void joinNodes(@QueryParam("mainPort") int mainPort, @QueryParam("portNb") int portSecond) throws Exception {
		System.out.println("test join mainNode");
		repartisseur = new Repartisseur(false);
		repartisseur.joinMainNode(mainPort, portSecond);
	}
	
	
	@GET
	@Path("/connect")
	@Produces(MediaType.TEXT_HTML)
	@Consumes(MediaType.APPLICATION_JSON)
	public void connect(
			@QueryParam("portNb") int portSecond
			) throws Exception {
		
		repartisseur.addNode(portSecond);
	}
	
	
	@POST
	@Path("/parse")
	//@Produces(MediaType.TEXT_HTML)
	@Consumes("text/csv")
	public Response parseCsv(
			@QueryParam("pathToFile") String pathToFile, 
			@QueryParam("nameOfFile") String nameOfFile
			) throws Exception {
		//csvReader = new CsvParser(pathToFile, nameOfFile);
		repartisseur.ParceCSV(pathToFile, nameOfFile);
		//repartisseur.getListTables().add(csvReader.getTable());

		System.out.println("Fichier Parse");
		return Response.status(200).entity("ok").build();
	}
	
	
	/*@GET
	@Path("/headers")
	@Produces(MediaType.TEXT_HTML)
	@Consumes(MediaType.APPLICATION_JSON)
	public void headers(
			@QueryParam("listHeaders") String listHeaders, 
			@QueryParam("nameTable") String nameTable
			) throws Exception {
		repartisseur.addHeader(listHeaders, nameTable);
	}*/
	
	
	
	@GET
	@Path("/test")
	@Produces(MediaType.TEXT_HTML)
	public String helloWorld() {
		
		return "<b>Hello World</b>";
	}
	
	
	/*@POST
	@Path("/addLines")
	//@Produces(MediaType.TEXT_HTML)
	@Consumes("text/csv")
	public Response addLines(
			@QueryParam("lines") String lines, 
			@QueryParam("nameFile") String nameFile
			) throws Exception {
		repartisseur.addLines(lines, nameFile);
		return Response.status(200).entity("ok").build();
	}
	
	
	
	@PUT
	@Path("/addIndex")
	@Produces(MediaType.TEXT_HTML)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response createIndex(
			@QueryParam("nameIndex")  String nameIndex, 
			@QueryParam("nameFile")  String nameFile
			
			) throws Exception {
		
		List<String> listNameColumns = new ArrayList<String>();
		//for (String word : nameIndex ) {
			listNameColumns.add(nameIndex);
		//}
		repartisseur.addIndex(listNameColumns, nameFile);
		//repartisseur.sendInstructions("");
		System.out.println("Index ajoute");
		System.out.println(repartisseur.getListTables().get(0).toString());
		return Response.status(200).entity("ok").build();
 
	}*/
	
	
	
	
	
	
	@GET
	@Path("/get")
	@Produces(MediaType.TEXT_HTML)
	@Consumes(MediaType.APPLICATION_JSON)
	public String get(
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
				l++;
			}
			else {
				word+=nameIndex.charAt(l);
			}
		}
		
		
		int sizeOfValue = value.length();
		String wordValue = "";
		for(int l = 0; l<sizeOfIndex; l++) {
			if(value.charAt(l) == '/') {
				listValue.add(wordValue);
				l++;
			}
			else {
				wordValue+=value.charAt(l);
			}
		}
				
		String results = repartisseur.get(listIndex, listValue);
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
	
	/*@GET
	@Path("/tables")
	public HashMap<String, Controllers.Table> getTables() {
		return repartisseur.getListTables();
		
	}*/
	
	@GET
	@Path("/download")
	public Response getDl() {
		return Response.status(200).entity("ok").build();
	}
	
	
		
}