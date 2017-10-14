import java.io.BufferedReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.LinkedList;
import java.util.List;

/**
 * 
 * Nastavuje nazev souboru, ze ktereho
 * se budou nacitat data
 * 
 * Umoznuje ulozit data do seznamu
 *
 */
public class DataReader {
	
	private String fileName;	

	public DataReader(String fileName) {		
		this.fileName = fileName;		
	}

	public String getFileName() {
		return fileName;
	}	

	public List<String> readFile(Path path) {		
		
		List<String> listOfWords = new LinkedList<String>();
		
		try (BufferedReader bfr = Files.newBufferedReader(path)) {			
			
			String line;
			
			while ((line = bfr.readLine()) != null) {				
				
				String[] lineArray = line.split(" ");
				
				for (String s : lineArray) {					
					
					// vymaze interpunkcni znaky 
					s = s.replaceAll("\\W", "").toLowerCase().trim();					
					
					if (s.isEmpty()) {
						continue;
					}
					else {
						listOfWords.add(s);
					}
				}
				
			}
			
		} 
		catch (Exception e) {
			System.err.println(e);
		}				
		
		return listOfWords;
		
	}
	
	

}
