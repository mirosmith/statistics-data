import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Map;

/**
 * 
 * Umoznuje vypsat vysledky do konzole
 * nebo do souboru
 *
 */
public class Presentation {
	
	private Logic logic;

	public Presentation(Logic logic) {		
		this.logic = logic;
	}
	
	private Map<String, Integer> getMap() {
		return logic.createStatistics();
	}
	
	// vystup do konzole
	public void outputConsole() {
		
		if (logic == null) {
			System.out.println("error due processing");
		}
		
		Map<String, Integer> map = getMap();
		
		System.out.println("------ WORD STATISTICS ------");
		
		for (Map.Entry<String, Integer> item : map.entrySet()) {
			System.out.format("%-15s %d %n", item.getKey(), item.getValue());
		}
		
	}
	
	// pomocna metoda, ktera zkontroluje spravny nazev souboru
	// nazev musi mit koncovku ".txt"
	// nazev musi zacinat pismenem
	private boolean outputFileCheck(String fName) {
		
		if (fName == null) {
			System.out.println("error due processing");
			return false;
		}
		
		fName = fName.trim();
		
		if (!fName.endsWith(".txt")) {
			System.out.println("file name must end with \".txt\"");
			return false;
		}
		else {
			String nameBeforeSuffix = fName.substring(0, fName.indexOf(".")).trim();
			
			if (nameBeforeSuffix.isEmpty()) {
				System.out.println("file name must contain letters");
				return false;
			}
			else {				
				char firstLetter = nameBeforeSuffix.charAt(0);
				int intFirstLetter = (int) firstLetter;				
				
				if ((intFirstLetter < 65 || intFirstLetter > 90) && (intFirstLetter < 97 || intFirstLetter > 122)) {
					System.out.println("file name must start with a letter");
					return false;
				}
			}
		}
		
		return true;
	}
	
	// vystup do souboru
	public void outputFile(String outputFileName) {
		
		if (logic == null) {
			System.out.println("error due processing");
		}	
		
		if (outputFileCheck(outputFileName)) {
			
			BufferedWriter bw = null;
			try {
				Map<String, Integer> myMap = getMap();
				
				bw = new BufferedWriter(new FileWriter(outputFileName));
				
				bw.write("------ WORD STATISTICS ------\n");
				for (Map.Entry<String, Integer> item : myMap.entrySet()) {
					bw.write(item.getKey() + "     " + item.getValue() + "\n");					
				}
				System.out.println("Successfully saved into " + outputFileName);
			}
			catch (Exception e) {
				e.printStackTrace();
			}
			finally {
				try {
					bw.close();
				} catch (IOException e) {				
					e.printStackTrace();
				}
			}
			
		}
		
		
	}
	

}
