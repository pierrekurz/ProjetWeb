package Controllers;
import java.util.List;
import java.util.Map;
import java.net.MalformedURLException;
import java.util.ArrayList;

public class MainController {
	List<Table> listTables = new ArrayList<>();
	CsvParser CsvParser;
	
	public MainController() {
		
	}

	public void ParceCSV(String path) throws Exception {
		CsvParser = new CsvParser(path);
		listTables.add(CsvParser.getTable());
	}
	
	public void addIndex(List<String> nameIndex) throws MalformedURLException {
		listTables.get(0).addIndex(nameIndex);
	}
	
	
}
