import java.util.*;

public class MainApp {

	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);		
		
		System.out.println(sc.toString());		
				
		if (args.length == 0) {
			System.out.println("bez parametru");
		}
		else {
			for (String s : args) {
				System.out.println(s);
			}
		}
		
		sc.close();

	}

}
