
import utilityPackage.Utility;

public class MainApp {
	
	/**	 
	 * Pokud je program spusten s vstupnim i vystupnim souborem, vysledky se vypisou do souboru.
	 * Pokud je program spusten pouze s vstupnim souborem, vysledky se vypisou do konzole.
	 */

	public static void main(String[] args) {	
		
		if (args.length == 2) {
			
			String input = args[0];
			String output = args[1];
			
			if (Utility.inputFileCheck(input) && Utility.outputFileCheck(output)) {
				DataReader dr = new DataReader(input);
				Logic lg = new Logic(dr);
				Presentation pt = new Presentation(lg);
				pt.outputFile(output);
			}
			
		}
		else if (args.length == 1) {
			
			String input = args[0]; 	
			
			if (Utility.inputFileCheck(input)) {
				DataReader dr = new DataReader(input);
				Logic lg = new Logic(dr);
				Presentation pt = new Presentation(lg);
				pt.outputConsole();
			}
		}
		else {
			System.out.println("input file name not specified "
								+ "or too many arguments");
		}			
		
		
	}

}
