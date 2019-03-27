package com.dant.app;
import com.dant.entity.Colonne;
import com.dant.entity.Table;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

/**
 * Created by pitton on 2017-02-20.
 */
@Path("/api")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class Endpoint {
	Table t;
	@GET
	@Produces(MediaType.TEXT_HTML)
	public String helloWorld() {
		return "Hello World";
	}
	
	@PUT
	@Path("/newtable")
	@Produces(MediaType.TEXT_HTML)
	@Consumes(MediaType.APPLICATION_JSON)
	public void tableCreated() {
		t=Table.getInstance();
		System.out.println("test creation table");
	}
	
	@PUT
	@Path("/newtable/newcolonne")
	@Produces(MediaType.TEXT_HTML)
	@Consumes(MediaType.APPLICATION_JSON)
	public void colonneCreated(@QueryParam("name") String name,@QueryParam("type") String type ) {
		Colonne c = new Colonne(name,type);
		if(t != null) {
			t.addColonne(c);
			System.out.println("Colonne ajoutée name : " + c.name +" type : " + c.type);
		}
	}
	
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

}
