package Controllers;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;

public class FileManager {

	static HashMap<String, File> fileAvailable =  new HashMap<String, File>();
	static HashMap<String, List<String>> headersInFile =  new HashMap<String, List<String>>();
	
	
	public static void createFile(String nameFile, List<String> nameHeaders) throws IOException {
		File file=new File(nameFile); 
		file.createNewFile();
		fileAvailable.put(nameFile, file);// on l'enregistre
		headersInFile.put(nameFile, nameHeaders);// on enregistre ses headers
	}
	
	
	
	public static void writeLines(String nameFile, List<HashMap<String, String>> linesToWrite) throws IOException {
		File file = fileAvailable.get(nameFile);
		FileWriter ffw;

		ffw=new FileWriter(file);
		
			
		for (HashMap<String, String> currentLine : linesToWrite) {
			for (Entry<String, String> currentWord :currentLine.entrySet()) {
				ffw.write(currentWord.getValue());
				ffw.write(",");	
			}
			ffw.write("/");	
			
		
		}
		
		ffw.close(); // fermer le fichier à la fin des traitements
		
		fileAvailable.put(" ", file);
	}
	
	
	
	
	
	
	
	
	public static List<HashMap<String, String>> readLines(String nameFile, List<Integer> numbersToGet) {
		List<HashMap<String, String>> results = new ArrayList<HashMap<String, String>>();//liste de toutes les lignes qui sont OK
		File currentFile = fileAvailable.get(nameFile);// on recupere le bon fichier
		Integer nbWord = headersInFile.get(nameFile).size();// on recupere le nombre de mots par ligne
		BufferedReader br = null;
		int readingLineNb = 0;
		Collections.sort(numbersToGet);// on trie la liste des lignes a recupere pour ne pas avoir a retourner en arriere
		int nextNumberToGet = 0;// Prochain numero dans la liste des lignes à recuperer qu on va chercher dans le fichier
        try {
            br = new BufferedReader(new FileReader(currentFile));
            String currentLetter = br.readLine();
            
            while (nextNumberToGet<numbersToGet.size()) {
            
	            while (readingLineNb<numbersToGet.get(nextNumberToGet)) {
	            
	            	int passedLetters = 0;
	            	String previousLetter;
	            	while(!(((previousLetter=currentLetter)=="/") && (currentLetter = br.readLine()) == "/")) {
	            		//On a deux fois / , c est a dire que c est la fin de la ligne
	            		passedLetters++;
	            	}
	            	
	            }
	            nextNumberToGet++;//On a trouve la premiere ligne
	            
	            
	            List<String> headersToGet = headersInFile.get(nameFile);// liste des headers du fichier
	            HashMap<String, String> currentLine =  new HashMap<String, String>();// ligne a retourner
	            
	            for (String header : headersToGet) {
	            	String currentWord = "";
		            while(!((currentLetter = br.readLine()) == ",")) {
		        		//On est pas a la fin du mot
		            	currentWord+=currentLetter;
		        	}
		            currentLine.put(header, currentWord);
		            
	            }
	            
	            results.add(currentLine);
            }
        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return results;
	}
	
}
