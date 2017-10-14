package utilityPackage;

import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Utility {	
	
	// metoda, ktera zkontroluje spravny nazev a existenci vstupniho souboru
	// nazev musi mit koncovku ".txt"	
	public static boolean inputFileCheck(String file) {
		
		if (file == null || file.isEmpty()) {
			System.out.println("error due processing");
			return false;
		}		
		
		if ((!file.endsWith(".txt")) &&	(!file.endsWith(".zip"))) {
			System.out.println("unknown file: " + file + ", (must be .txt or .zip format)");
			return false;
		}		
	
		Path path = Paths.get(file);
		path = path.toAbsolutePath();
		
		
		if (Files.notExists(path)) {
			System.out.println("file does not exist: " + file);
			return false;
		}
		
		if ((!Files.isRegularFile(path, LinkOption.NOFOLLOW_LINKS)) || (!Files.isReadable(path))) {
			System.out.println("file is not readable: " + file);
			return false;
		}
		
		return true;
	}

	// metoda, ktera zkontroluje spravny nazev souboru
	// nazev musi mit koncovku ".txt"
	// nazev musi zacinat pismenem
	public static boolean outputFileCheck(String fName) {

		if (fName == null) {
			System.out.println("error due processing");
			return false;
		}

		fName = fName.trim();

		if (!fName.endsWith(".txt")) {
		   System.out.println("unsupported format: "+ fName + ", (must be .txt format)");
		   return false;
		} else {
			String nameBeforeSuffix = fName.substring(0, fName.indexOf(".")).trim();

			if (nameBeforeSuffix.isEmpty()) {
				System.out.println("file name must contain letters: " + fName);
				return false;
			} else {
				char firstLetter = nameBeforeSuffix.charAt(0);
				int intFirstLetter = (int) firstLetter;

				if ((intFirstLetter < 65 || intFirstLetter > 90) && (intFirstLetter < 97 || intFirstLetter > 122)) {
					System.out.println("file name must start with a letter: " + fName);
					return false;
				}
			}
		}

		return true;
	}	

}
