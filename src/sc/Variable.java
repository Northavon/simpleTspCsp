package sc;
import java.util.*;

/**
 * The Variable class handles the variables for the CSP
 * @author Tjokorde Gde Agung Octavio Putra
 *
 */
public class Variable {
	String name;
	List<City> domains;
	int order;
	City assigned;
	
	/*
	 * Variable Constructor
	 */
	public Variable(String name, int order) {
		this.order = order;
		this.name = name;
	}
	
	/*
	 * The isNotAssigned method checks if the variable hasn't been assigned a domain value yet
	 * @return true if not yet assigned, false if null
	 */
	public boolean isNotAssigned() {
		if (assigned == null) {
			return true;
		} else {
			return false;
		}
	}
	
	/*
	 * toString method for debugging purpose
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		return "Variable " + name + " domains: " + domains.toString() + " order: " + order + " assigned " + assigned;
	}
}
