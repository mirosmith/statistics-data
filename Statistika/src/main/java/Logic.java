import java.io.IOException;
import java.net.URI;
import java.nio.file.DirectoryStream;
import java.nio.file.FileSystem;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * Trida vytvari statistiku pomoci objektu DataReader
 */
public class Logic {

	private DataReader dataR;
	private Map<String, Integer> mapStats;

	public Logic(DataReader dataR) {
		this.dataR = dataR;
		setMapStats();
	}	

	public Map<String, Integer> getMapStats() {		
		return mapStats;
	}	

	public void setMapStats() {
		if(dataR.getFileName().endsWith(".zip")) {
			this.mapStats = createStatisticsFromZip();
		}
		else {
			this.mapStats = createStatistics();
		}
	}

	// vytvori statistiku z textoveho souboru
	public Map<String, Integer> createStatistics() {

		Map<String, Integer> map1 = new TreeMap<String, Integer>();

		if (dataR == null) {
			return map1;
		}		
		
		String inputFile = dataR.getFileName();
		Path inputFilePath = Paths.get(inputFile);
		
		List<String> words = dataR.readFile(inputFilePath);

		if (words.isEmpty()) {
			return map1;
		}

		int num;
		for (String element : words) {
			if (map1.containsKey(element)) {
				num = map1.get(element);
				map1.put(element, ++num);
			} else {
				map1.put(element, 1);
			}
		}		

		return map1;
	}
	
	// vytvori statistiku z zip souboru
	public Map<String, Integer> createStatisticsFromZip() {		
		
		Map<String, Integer> map2 = new TreeMap<String, Integer>();

		if (dataR == null) {
			return map2;
		}
		
		List<Path> filePaths = new LinkedList<>();
		
		String inputFile = dataR.getFileName();
		Path inputFilePath = Paths.get(inputFile);				
			
		URI uri = URI.create("jar:" + inputFilePath.toUri());
		
		try (FileSystem fs = FileSystems.newFileSystem(uri, new HashMap<>())) {
			
			Path directoryPath = fs.getPath("\\");
			DirectoryStream<Path> ds = Files.newDirectoryStream(directoryPath);			
			
			for (Path item : ds) {	
				String s = item.toString();
				
				if ((!s.endsWith(".txt")) &&
					(!s.endsWith(".pdf"))) {
					System.out.println("unsupprted file: " + s);
					continue;
				}		
				filePaths.add(item);				
			}
			
			if (filePaths.isEmpty()) {
				System.out.println("empty zip file");
				return map2;
			}			
			
			for (Path item2 : filePaths) {

				List<String> words = dataR.readFile(item2);

				if (words.isEmpty()) {
					return map2;
				}

				int num;
				for (String element : words) {
					if (map2.containsKey(element)) {
						num = map2.get(element);
						map2.put(element, ++num);
					} else {
						map2.put(element, 1);
					}
				}
			}
			
		}		
		catch (IOException e) {
			System.err.println(e);
		}
		
		return map2;
		
	}	
	
}
