package sc;
import java.util.*;

/**
 * This is the main class for the program
 * The program processes the default input then outputs the route as stated in the problemset
 * @author Tjokorde Gde Agung Octavio Putra
 *
 */
public class Main {
	public static void main (String[] args) {
		CSP csp = new CSP();
		Backtrack backtrack = new Backtrack(csp);
		backtrack.aq3();
		backtrack.rearrangeByMostConstrained();
		List<Variable> result = backtrack.backtrackSearch();
		if (result.isEmpty()) {
			System.out.println("Failed to Find Solution");
		} else {
			HashMap<Integer, City> resultMap = new HashMap<>();
			for (Variable var : csp.variable) {
				resultMap.put(var.order, var.assigned);
			}
			String resultString = "Route : a -> ";
			for(int i = 1 ; i <= resultMap.size() ; i++) {
				resultString += resultMap.get(i).name + " -> ";
			}
			System.out.println(resultString + "a");
		}
	}
}
