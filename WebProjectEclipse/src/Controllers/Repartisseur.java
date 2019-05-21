package Controllers;


import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class Repartisseur {
	Boolean mainNode;
	List<Integer> otherNodes; 
	List<Table> listTables = new ArrayList<>();
	CsvParser CsvParser;
	
	public Repartisseur(Boolean mainNode){
		this.mainNode = mainNode;
		this.otherNodes = new ArrayList();
	}
	
	
	/*public void addIndex(List<String> nameIndex) throws MalformedURLException {
		listTables.get(0).addIndex(nameIndex);
	}*/
	
	
	// calling other nodes' API
	public List<Object[]> sendInstructions(String Instruction) throws MalformedURLException {
		List<Object[]> otherNodesAnswers = new ArrayList<Object[]>();
		for (Integer port : this.otherNodes) {
			String nameUrl = "http://";
			nameUrl += port.toString();
			nameUrl += Instruction;
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
	public void addIndex(List<String> nameIndex) throws MalformedURLException {
		//Attention, verifier le nom de l'endPoint
		if (this.mainNode) {
			String instruction = "addIndex/";
			instruction += nameIndex;
			List<Object[]> otherNodesAnswers = sendInstructions(instruction);
			
		}
		listTables.get(0).addIndex(nameIndex);
		
	}



	public List<Object[]> searchBigger(String nameIndex, int valueMin ) throws MalformedURLException {
		List<Object[]> otherNodesAnswers = new ArrayList<Object[]>();
		if (this.mainNode) {
			String instruction = "searchBigger/";
			instruction += nameIndex;
			instruction += ((Integer)valueMin).toString();
			otherNodesAnswers.addAll(sendInstructions(instruction));
		}
		otherNodesAnswers.addAll(this.listTables.get(0).searchBigger(nameIndex, valueMin));
		return otherNodesAnswers;
	}



	public List<Object[]> searchSmaller(String nameIndex, int valueMax) throws MalformedURLException {
		List<Object[]> otherNodesAnswers = new ArrayList<Object[]>();
		if (this.mainNode) {
			String instruction = "searchSmaller/";
			instruction += nameIndex;
			instruction += ((Integer)valueMax).toString();
			otherNodesAnswers.addAll(sendInstructions(instruction));
		}
		otherNodesAnswers.addAll(this.listTables.get(0).searchSmaller(nameIndex, valueMax));
		return otherNodesAnswers;
	}
	
	
	public List<Object[]> get(String nameIndex, String value) {
		String instruction = "get/";
		instruction += nameIndex;
		instruction += value;
		
		return null;
	}


	public void addLine(List<Object[]> lines, int nodeNumber) {
		String instruction = "addLine/";
		instruction += this.otherNodes.get(nodeNumber);
		instruction += lines;
		
		
		
	}



	public void ParceCSV(String path) throws Exception {
		if (this.mainNode) {
			String instruction = "parse/" + path; 
			sendInstructions(instruction);
		}
		CsvParser = new CsvParser(path);
		listTables.add(CsvParser.getTable());
		
	}



	
	
	
	
	
}
