package sc;
import java.util.*;

/*
 * The Edge class represents edges in a weighted graph
 */
public class Edge {
	City origin;
	City dest;
	int weight;
	
	public Edge(City origin, City dest, int weight) {
		this.origin = origin;
		this.dest = dest;
		this.weight = weight;
	}
}
