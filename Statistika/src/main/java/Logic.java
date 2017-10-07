import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Trida vytvari statistiku 
 * pomoci objektu DataReader a nacteneho souboru 
 *
 */
public class Logic {
	
	private DataReader dataR;

	public Logic(DataReader dataR) {		
		this.dataR = dataR;
	}
	
	// ze ziskaneho seznamu slov vytvori mapu (statistiku)
	public Map<String, Integer> createStatistics() {
		
		Map<String, Integer> mapStats = new HashMap<String, Integer>();
		
		if (dataR == null) {
			return mapStats;
		}
		
		List<String> words = dataR.readFile();
		
		if (words.isEmpty()) {
			return mapStats;
		}
		
		int num;
		for (String element : words) {
			if (mapStats.containsKey(element)) {
				num = mapStats.get(element);
				mapStats.put(element, ++num);
			}
			else {
				mapStats.put(element, 1);
			}
		}
		
		return mapStats;
	}

}
