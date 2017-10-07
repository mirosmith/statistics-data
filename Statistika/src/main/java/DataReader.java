import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

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
	
	public List<String> readFile() throws IOException {
		
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
					
					s = s.toLowerCase().trim();
					
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
			bfr.close();
		}		
		
		return listOfWords;
		
	}
	
	

}
