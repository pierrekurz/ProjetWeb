package com.dant.entity;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.net.HttpURLConnection;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.URL;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;




public class Repartisseur {


	List<Integer> portsARepartir;
	List<Socket> socketCommunication;
	InetAddress ici;

	public Repartisseur(){
		this.portsARepartir = new ArrayList<Integer>();
		 try {
			this.ici = InetAddress.getLocalHost();
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


	}

	public void addPort(int newPort){
		// methode d'ajout de noeud dans le réseau//

		//Enregistrement du port du nouveau noeud 
		this.portsARepartir.add(newPort);


		// creation du socket de communication entre la machine principale et le nouveau noeud
		InetSocketAddress serverAddress = new InetSocketAddress(this.ici, newPort);
		Socket socket = new Socket();
		try {
			socket.connect(serverAddress);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.socketCommunication.add(socket);
	}



	public String repartirInstruction(String endpoint, String typeRequest){
		 /*endpoint = endopoint appele sur les autres noeuds. 
		
		*/

		// methode de repartition d'une instruction aux autres noeuds

	HttpURLConnection connection = null;

  	try {
	    //Create connection
	    URL url = new URL("http://localhost");
	    connection = (HttpURLConnection) url.openConnection();
	    connection.setRequestMethod(typeRequest);
	    /*connection.setRequestProperty("Content-Type",  "application/x-www-form-urlencoded");

	    connection.setRequestProperty("Content-Length", 
	        Integer.toString(urlParameters.getBytes().length));
	    connection.setRequestProperty("Content-Language", "en-US");  */

	    connection.setUseCaches(false);
	    connection.setDoOutput(true);

	    //Send request
	    DataOutputStream wr = new DataOutputStream (connection.getOutputStream());
	    //wr.writeBytes(urlParameters);
	    wr.close();

	    //Get Response  
	    InputStream is = connection.getInputStream();
	    BufferedReader rd = new BufferedReader(new InputStreamReader(is));
	    StringBuilder response = new StringBuilder(); // or StringBuffer if Java version 5+
	    String line;
	    while ((line = rd.readLine()) != null) {
	      response.append(line);
	      response.append('\r');
	    }
	    rd.close();
	    return response.toString();
  	}
  	catch (Exception e) {
    	e.printStackTrace();
    	return null;
  	} 
  	finally {
    	if (connection != null) {
      	connection.disconnect();
    	}
  	}

	/*for (InetSocketAddress socket : socketCommunication){
		OutputStream os = socket.getOutputStream();
		os.write(instruction);
	}*/
}


	public void recupererReponses() throws IOException{

		// methode de repartition d'une instruction aux autres noeuds

		for (Socket socket : socketCommunication){
			InputStream is = socket.getInputStream();
			int x = is.read();
			

		}
	}
}






