import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
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

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}	
	
	public List<String> readFile() {
		
		List<String> listOfWords = new LinkedList<String>();
		
		if (fileName == null || fileName.isEmpty()) {
			return listOfWords;
		}
		
		BufferedReader bfr = null;
		
		try {
			bfr = new BufferedReader(new FileReader(fileName));
			
			String line;
			
			while ((line = bfr.readLine()) != null) {				
				
				String[] lineArray = line.split(" ");
				
				for (String s : lineArray) {					
					
					// removes a non-word characters from string 
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
			e.printStackTrace();
		}
		finally {
			try {
				bfr.close();
			} catch (IOException e) {				
				e.printStackTrace();
			}
		}		
		
		return listOfWords;
		
	}
	
	

}
