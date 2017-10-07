import java.util.Map;

public class Presentation {
	
	private Logic logic;

	public Presentation(Logic logic) {		
		this.logic = logic;
	}
	
	private Map<String, Integer> getMap() {
		return logic.createStatistics();
	}
	
	public void outputConsole() {
		
		if (logic == null) {
			System.out.println("error due processing");
		}
		
		Map<String, Integer> map = getMap();
		
		System.out.println("------ WORDS STATISTICS ------");
		
		for (Map.Entry<String, Integer> item : map.entrySet()) {
			System.out.format("%-15s %d %n", item.getKey(), item.getValue());
		}
		
	}
	
	

}
