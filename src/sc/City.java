package sc;
import java.util.*;

/*
 * The City class handles the cities which acts as nodes in a weighted graph
 */
public class City {
	String name;
	List<Edge> edges;
	
	public City (String name, List<Edge> edges) {
		this.name = name;
		this.edges = edges;
	}
	
	/*
	 * setEdges is a setter method for edges
	 * @param List of edges to enter
	 */
	public void setEdges(List<Edge> edges) {
		this.edges = edges;
	}
	
	/*
	 * toString method for debugging purposes
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		return "City Name: " + name;
	}
}
