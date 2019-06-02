package Controllers;


import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;

public class Repartisseur {
	Boolean mainNode;
	List<Integer> otherNodes; 
	HashMap<String, Table> listTables = new HashMap<String, Table>();
	CsvParser CsvParser;
	int nbMainNode = 8080;
	GSONConverter gSONConverter = new GSONConverter();
	
	public Repartisseur(Boolean mainNode){
		this.mainNode = mainNode;
		this.otherNodes = new ArrayList();
	}
	
	public void addNode(int nbNode) {
		this.otherNodes.add(nbNode);
	}
	
	
	/*public void addIndex(List<String> nameIndex) throws MalformedURLException {
		listTables.get(0).addIndex(nameIndex);
	}*/
	
	
	// calling other nodes' API
	public String sendInstructions(String Instruction, String JSON) throws MalformedURLException {
		String otherNodesAnswers = "";
		for (Integer port : this.otherNodes) {
			String nameUrl = "http://";
			nameUrl += port.toString();
			nameUrl += Instruction;
			if(JSON!= null) {
				nameUrl += JSON;
			}
			URL url = new URL(nameUrl);
			// Mettre appel http aux autres noeuds
			/* Object answer = port.instruction(); 
			 * otherNodesAnswers.add();
			 * 
			 */
		}
		return otherNodesAnswers;
	
	}

	
	// Main functions to be shared 
	public void addIndex(List<String> nameIndex, String nameFile) throws MalformedURLException {
		//Attention, verifier le nom de l'endPoint
		if (this.mainNode) {
			String instruction = "addIndex?";
			instruction += "nameIndex=";
			instruction += nameIndex;
			instruction += "&nameFile=";
			instruction += nameFile;
			String otherNodesAnswers = sendInstructions(instruction, null);
			
		}
		listTables.get(0).addIndex(nameIndex);
		
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
	
	
	public String get(List<String> listIndex, List<String> listValue) throws MalformedURLException {
		
		
		
		String result = "";
		if(this.mainNode) {
			String instruction = "get?";
			instruction += "nameIndex=";
			instruction += this.gSONConverter.listToJson(listIndex);
			instruction += "&value=";
			instruction += this.gSONConverter.listToJson(listValue);
			result+=this.sendInstructions(instruction, null);
			 
		}
		
		
		return result;
	}


	public void addLine(List<HashMap<String, String>> lines, int nodeNumber) {
		String instruction = "addLine/";
		instruction += this.otherNodes.get(nodeNumber);
		instruction += lines;
		
		
		
	}
	
	public void sendLines(List<Object[]> lines, String nameFile) throws MalformedURLException {
		int nbLinesToSend = (int)(lines.size()/this.otherNodes.size());
		int k = 0;
		for(int node : this.otherNodes) {
			
			String instruction = ((Integer)node).toString();
			instruction += "/addLines?";
			instruction += "nameFile=";
			instruction += nameFile;
			String JSON;
			
			if(k+nbLinesToSend<=lines.size()) {
				JSON = this.gSONConverter.listArrayToJson(lines.subList(k, k+nbLinesToSend));
			}
			else {
				JSON = this.gSONConverter.listArrayToJson(lines.subList(k, lines.size()));
				
			}
			this.sendInstructions(instruction, JSON);
		}
		
		
		
		
		
		
		
		
		
		
	}

	
	


	public void ParceCSV(String path, String nameOfFile) throws Exception {
		
		if (this.mainNode) {
			CsvParser = new CsvParser(path, nameOfFile, this);
			
		}
		
		listTables.put(nameOfFile, CsvParser.getTable());
		
	}

	public void joinMainNode(int mainNode, int portSecond) throws MalformedURLException {
		this.nbMainNode = mainNode;
		this.otherNodes.add(mainNode);
		String instruction = "connect/";
		instruction += ((Integer)portSecond).toString();
		this.sendInstructions(instruction, null);
		
	}

	public void sendHeaders(List<String> headersTable, String nameTable) throws MalformedURLException {
		String instruction = "headers";
		instruction += "?nameTable";
		instruction += nameTable;
		instruction += "?listHeaders";
		String JSON = this.gSONConverter.listToJson(headersTable);
		this.sendInstructions(instruction, JSON);
		
	}

	
	
	public void addHeader(String listHeaders, String nameTable) throws IOException {
		// TODO Auto-generated method stub
		
		Table table = new Table("secondary");
		List<String> header = this.gSONConverter.jsonToList(listHeaders);
		table.init(header);
		this.listTables.put(nameTable, table);
		
	}

	public HashMap<String, Table> getListTables() {
		return (this.listTables);
	}

	public void addLines(String lines, String nameTable) throws IOException {
		Table table = this.listTables.get(nameTable);
		List<Object[]> linesToAdd = this.gSONConverter.jsonToArrayObject(lines);
		table.addLines(linesToAdd);
		
		
	}



	
	
	
	
	
}
