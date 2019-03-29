package com.dant.entity;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class CsvParser {
	public static void parseCsv(String path) throws Exception {
		// "C:\\Users\\Nguye\\OneDrive\\Documents\\dant-master\\test.csv";
		String csvFile = path;
		BufferedReader br = null;
		String line = "";
		String cvsSplitBy = ",";
		int compteurColonne = 1;
		int cpt = 0;
		Table table = new Table("table generale");
		try {
			System.out.println("ok parser");
			br = new BufferedReader(new FileReader(csvFile));
			line = br.readLine();
			String nameColonne = "";
			for (int i = 0; i < line.length(); i++) {
				if (line.charAt(i) == ',') {
					compteurColonne++;
					table.addColonne(nameColonne, "String");
					System.out.println("colonne test");
					System.out.println(nameColonne);
					nameColonne = "";
				} else {
					nameColonne += line.charAt(i);
				}
			}

			compteurColonne++;
			table.addColonne(nameColonne, "String");
			System.out.println(nameColonne);
			nameColonne = "";

			while ((line = br.readLine()) != null) {

				int countWord = 0;
				System.out.println("test readline");

				String elementCSV[] = new String[compteurColonne];

				for (int i = 0; i < line.length(); i++) {
					if (line.charAt(i) == ',') {
						elementCSV[countWord] = nameColonne;
						nameColonne = "";
						countWord++;
					} else {
						nameColonne += line.charAt(i);
					}
				}

				table.insert(elementCSV);
				cpt++;
				System.out.println(cpt);
			}

		} catch (FileNotFoundException e) {
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

	}

}
