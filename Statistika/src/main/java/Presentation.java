import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;

/**  
 * Umoznuje vypsat vysledky do konzole nebo do souboru
 */
public class Presentation {

	private Logic logic;

	public Presentation(Logic logic) {
		this.logic = logic;
	}

	// vystup do konzole
	public void outputConsole() {

		if (logic == null) {
			System.out.println("error due processing");
		}

		Map<String, Integer> map = logic.getMapStats();
		
		if (map.isEmpty()) {
			System.out.println("no data");
		}
		else {			
	
			System.out.println("------ WORD STATISTICS ------");
	
			for (Map.Entry<String, Integer> item : map.entrySet()) {
				System.out.format("%-15s %d %n", item.getKey(), item.getValue());
			}
		}

	}

	// vystup do souboru
	public void outputFile(String outputFileName) {

		if (logic == null) {
			System.out.println("error due processing");
		}
		
		Path path = Paths.get(outputFileName);
		
		try (BufferedWriter bfw = Files.newBufferedWriter(path)){
			
			Map<String, Integer> myMap = logic.getMapStats();
			
			if (myMap.isEmpty()) {
				bfw.write("no data");
			}
			else {
				
				bfw.write("------ WORD STATISTICS ------ \n");
				
				for (Map.Entry<String, Integer> item : myMap.entrySet()) {
					bfw.write(item.getKey() + "     " + item.getValue() + " \n");
				}
				
				System.out.println("Successfully saved into: \n" + path.toAbsolutePath().toString());
			}
			
		} 
		catch (IOException e) {
			System.err.println(e);
		} 		

	}	

}
