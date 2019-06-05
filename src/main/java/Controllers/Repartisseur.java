package Controllers;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;


public class Repartisseur {
	Boolean mainNode;
	List<Integer> otherNodes; 
	HashMap<String, Table> listTables = new HashMap<String, Table>();
	CsvParser CsvParser;
	int nbMainNode = 8080;
	GSONConverter gSONConverter = new GSONConverter();
	private final Gson gson = new GsonBuilder().serializeNulls().create();
	final String beginingUrl = "http://localhost:";
	
	public Repartisseur(Boolean mainNode){
		//Creation du repartisseur
		System.out.println("repartisseur cree");
		this.mainNode = mainNode;// est il noeud principal?
		this.otherNodes = new ArrayList();// liste des autres noeuds
	}
	
	public void addNode(int nbNode) {
		this.otherNodes.add(nbNode);// ajout d'un autre noeud
	}
	
	public List<Integer> getOtherNodesPort() {
		return otherNodes;
	}
	
	
	/*public void addIndex(List<String> nameIndex) throws MalformedURLException {
		listTables.get(0).addIndex(nameIndex);
	}*/
	
	
	// calling other nodes' API
	public String sendInstructions(String Instruction, String JSON, String typeRequest) throws IOException {
		System.out.println("Sending instruction");
		String otherNodesAnswers = "";
		for (Integer port : this.otherNodes) {
			String nameUrl = beginingUrl;
			
			nameUrl += port.toString();
			nameUrl += "/";
			nameUrl += Instruction;
			System.out.println(nameUrl);
			
			if(JSON!= null) {
				nameUrl += JSON;
			}
			System.out.println("Instruction written");
			URL url = new URL(nameUrl);
			HttpURLConnection con = (HttpURLConnection)url.openConnection();
			con.setRequestMethod(typeRequest);
			
			
			BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
			String inputLine;
			StringBuffer content = new StringBuffer();
			while ((inputLine = in.readLine()) != null) {
					    content.append(inputLine);
					}
			in.close();
			otherNodesAnswers += content.toString();
			System.out.println("Instrction sent");
		}
		return otherNodesAnswers;
	
	}

	
	// Main functions to be shared 
	public void addIndex(List<String> nameIndex, String nameFile) throws IOException {
		//add an Index
		System.out.println("Ici1");
		if (this.mainNode) {
			System.out.println("Ici12");
			String instruction = "addIndex?";
			instruction += "nameIndex=";
			instruction += nameIndex;
			instruction += "&nameFile=";
			instruction += nameFile;
			String otherNodesAnswers = sendInstructions(instruction, null, "POST");
			
		}
		System.out.println("Ici4");
		System.out.println(listTables);
		listTables.get(nameFile).addIndex(nameIndex);
		System.out.println("Ici5");
		
	}



	/*public List<Object[]> searchBigger(String nameIndex, int valueMin ) throws MalformedURLException {
		List<Object[]> otherNodesAnswers = new ArrayList<Object[]>();
		if (this.mainNode) {
			String instruction = "searchBigger/";
			instruction += nameIndex;
			instruction += ((Integer)valueMin).toString();
			otherNodesAnswers.addAll(sendInstructions(instruction, null));
		}
		//otherNodesAnswers.add(this.listTables.get(0).searchBigger(nameIndex, valueMin));
		return otherNodesAnswers;
	}*/



	/*public List<Object[]> searchSmaller(String nameIndex, int valueMax) throws MalformedURLException {
		List<Object[]> otherNodesAnswers = new ArrayList<Object[]>();
		if (this.mainNode) {
			String instruction = "searchSmaller/";
			instruction += nameIndex;
			instruction += ((Integer)valueMax).toString();
			otherNodesAnswers.addAll(sendInstructions(instruction, null));
		}
		otherNodesAnswers.addAll(this.listTables.get(0).searchSmaller(nameIndex, valueMax));
		return otherNodesAnswers;
	}*/
	
	
	public String get(String nameTable, List<String> listIndex, List<String> listValue) throws IOException {
		// getting the values thanks to inde
		System.out.println("Reprtisseur get");
		
		String result = "";
		if(this.mainNode) {
			String instruction = "/get?";
			instruction += "nameIndex=";
			instruction += this.gSONConverter.listToJson(listIndex);
			instruction += "&value=";
			instruction += this.gSONConverter.listToJson(listValue);
			result+=this.sendInstructions(instruction, null, "GET");
			 
		}
		System.out.println("get here");
		result += this.listTables.get(nameTable).get(listIndex, listValue);
		
		
		return result;
	}


	public void addLine(List<HashMap<String, String>> lines, int nodeNumber) {
		String instruction = "addLine/";
		instruction += this.otherNodes.get(nodeNumber);
		instruction += lines;
		
	}
	
	public void sendLines(List<Object[]> lines, String nameFile) throws IOException {
		int nbLinesToSend = (int)(lines.size()/this.otherNodes.size());
		int k = 0;
		for(int node : this.otherNodes) {
			
			String instruction = ((Integer)node).toString();
			instruction += "/api/addLines?";
			instruction += "nameFile=";
			instruction += nameFile;
			String JSON;
			
			if(k+nbLinesToSend<=lines.size()) {
				JSON = this.gson.toJson(lines.subList(k, k+nbLinesToSend));
			}
			else {
				JSON = this.gson.toJson(lines.subList(k, lines.size()));
				
			}
			
			String nameUrl = "http://localhost:";
			
			
			
			nameUrl += instruction;
			
			URL url = new URL(nameUrl);
			HttpURLConnection con = (HttpURLConnection)url.openConnection();
			con.setRequestMethod("POST");
			
			
			BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
			String inputLine;
			StringBuffer content = new StringBuffer();
			while ((inputLine = in.readLine()) != null) {
					    content.append(inputLine);
					}
			in.close();

			
	
			
		}
		
		
	}

	
	


	public void ParceCSV( String nameOfFile, String path, Repartisseur repartisseur) throws Exception {
		System.out.println("dans Fichier Parse out");
		System.out.println(this.mainNode);
		//if (this.mainNode) {
			System.out.println("dans Fichier Parse");
			CsvParser = new CsvParser(nameOfFile, path, repartisseur);
			
		//}
		
		this.listTables.put(nameOfFile, CsvParser.getTable());
		
	}

	public void joinMainNode(int mainNode, int portSecond) throws IOException {
		System.out.println("Joining");
		this.nbMainNode = mainNode;
		this.otherNodes.add(mainNode);
		String instruction = "api/connect?portNb=";
		System.out.println("Joined");
		instruction += ((Integer)portSecond).toString();
		this.sendInstructions(instruction, null, "GET");
		System.out.println("Joined");
		
	}

	public void sendHeaders(List<String> headersTable, String nameTable) throws IOException {
		
		String instruction = "headers";
		instruction += "?nameTable";
		instruction += nameTable;
		instruction += "?listHeaders";
		String JSON = this.gSONConverter.listToJson(headersTable);
		this.sendInstructions(instruction, JSON, "POST");
		
	}

	
	
	public void addHeader(String listHeaders, String nameTable) throws IOException {
		// Send the headers of the table to other nodes
		Table table = new Table("secondary");
		List<String> header = this.gson.fromJson(listHeaders, List.class);
		table.init(header);
		this.listTables.put(nameTable, table);
	}

	public HashMap<String, Table> getListTables() {
		return (this.listTables);
	}

	public void addLines(String lines, String nameTable) throws IOException {
		Table table = this.listTables.get(nameTable);
		List<Object[]> linesToAdd = this.gson.fromJson(lines, List.class);
		table.addLines(linesToAdd);
		
		
	}



	
	
	
	
	
}
