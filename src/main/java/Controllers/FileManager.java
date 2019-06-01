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
import java.util.Scanner;

public class FileManager {

	static HashMap<String, File> fileAvailable = new HashMap<String, File>();
	static HashMap<String, List<String>> headersInFile = new HashMap<String, List<String>>();

	public static void createFile(String nameFile, List<String> nameHeaders) throws IOException {
		File file = new File(nameFile);
		file.createNewFile();
		fileAvailable.put(nameFile, file);// on l'enregistre
		headersInFile.put(nameFile, nameHeaders);// on enregistre ses headers
	}

	public static void writeLines(String nameFile, List<Object[]> linesToWrite) throws IOException {
		File file = fileAvailable.get(nameFile);
		FileWriter ffw;
		ffw = new FileWriter(file, true);
		// BufferedWriter out = new BufferedWriter(ffw);
		// out.write(str);
		// out.close();

		for (Object[] currentLine : linesToWrite) {
			int length = currentLine.length;
			for (int nbWord = 0; nbWord < length; nbWord++) {
				String currentWord = currentLine[nbWord].toString();
				int lenWord = currentWord.length();
				// System.out.println(currentWord);
				for (int nbLetter = 0; nbLetter < lenWord; nbLetter++) {
					// System.out.println(currentWord.charAt(nbLetter));
					ffw.write(currentWord.charAt(nbLetter));
				}
				ffw.write(",");
			}
			ffw.write("//");

		}

		ffw.close(); // fermer le fichier à la fin des traitements

		fileAvailable.put(" ", file);
	}

	public static List<HashMap<String, String>> readLines(String nameFile, List<Integer> numbersToGet) {
		List<HashMap<String, String>> results = new ArrayList<HashMap<String, String>>();// liste de toutes les lignes
																							// qui sont OK
		File currentFile = fileAvailable.get(nameFile);// on recupere le bon fichier
		Integer nbWord = headersInFile.get(nameFile).size();// on recupere le nombre de mots par ligne
		BufferedReader br = null;
		int readingLineNb = 0;
		Collections.sort(numbersToGet);// on trie la liste des lignes a recupere pour ne pas avoir a retourner en
										// arriere
		int nextNumberToGet = 0;// Prochain numero dans la liste des lignes à recuperer qu on va chercher dans
								// le fichier
		try(Scanner sc = new Scanner(currentFile)) {
			// FileReader f = new FileReader(currentFile);
			

			// we just need to use \\Z as delimiter
			sc.useDelimiter("//");

			String readLine = sc.next();

			while (nextNumberToGet < numbersToGet.size()) {

				while (readingLineNb < numbersToGet.get(nextNumberToGet)) {
					// System.out.println("ll");
					readingLineNb++;
					readLine = sc.next();
				}
				nextNumberToGet++;// On a trouve la premiere ligne

				// System.out.println(readLine);

				List<String> headersToGet = headersInFile.get(nameFile);// liste des headers du fichier
				HashMap<String, String> currentLine = new HashMap<String, String>();// ligne a retourner

				int letterInTheWord = 0;
				for (String header : headersToGet) {
					String currentWord = "";
					char currentLetter;
					while (!((currentLetter = readLine.charAt(letterInTheWord)) == ',')
							&& (letterInTheWord <= readLine.length())) {
						// System.out.println(currentLetter);
						letterInTheWord++;
						// On est pas a la fin du mot
						currentWord += currentLetter;
					}
					currentLine.put(header, currentWord);
					letterInTheWord++;

				}

				results.add(currentLine);
			}
		} catch (FileNotFoundException e) {
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
