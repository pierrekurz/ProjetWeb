package Controllers;


import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class Repartisseur {
	Boolean mainNode;
	List<Integer> otherNodes; 
	
	public Repartisseur(Boolean mainNode){
		this.mainNode = mainNode;
		this.otherNodes = new ArrayList();
	}
	
	
	
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
		String instruction = "addIndex/";
		instruction += nameIndex;
		List<Object[]> otherNodesAnswers = sendInstructions(instruction);
	}



	public List<Object[]> searchBigger(String nameIndex, int valueMin ) throws MalformedURLException {
		String instruction = "searchBigger/";
		instruction += nameIndex;
		instruction += ((Integer)valueMin).toString();
		List<Object[]> otherNodesAnswers = sendInstructions(instruction);
		return otherNodesAnswers;
	}



	public List<Object[]> searchSmaller(String nameIndex, int valueMax) throws MalformedURLException {
		String instruction = "searchSmaller/";
		instruction += nameIndex;
		instruction += ((Integer)valueMax).toString();
		List<Object[]> otherNodesAnswers = sendInstructions(instruction);
		return null;
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



	
	
	
	
	
}
