package sc;
import java.util.*;

import sc.CSP.RangeConstraint;
import sc.CSP.UniqueConstraint;

/*
 * The Backtrack function runs most of the backtracking related functions
 */
public class Backtrack {
	CSP problem;
	public List<Variable> result;

	public Backtrack(CSP csp) {
		problem = csp;
	}

	/*
	 * The backtrackSearch method instantiates the backtrack method
	 * @return List of Variables from the CSP problem
	 */
	public List<Variable>backtrackSearch() {
		return backtrack(problem);
	}
	
	/*
	 * The backtrack method runs the backtrack CSP algorithm
	 * @return List of Variables from the CSP problem
	 */
	public List<Variable> backtrack(CSP problem) {
		if (assignmentFinished()) {
			return problem.variable;
		}
		Variable unassigned = selectUnassignedVariable();
		while(unassigned.domains.size()>1) {
			for (City c : unassigned.domains) {
				if (assignmentFinished()) {
					return problem.variable;
				}
				unassigned.assigned = c;
				if(inference(unassigned) == false) {
					unassigned.assigned = null;
				} else {
					backtrack(problem);
				}
			}
		}
		return problem.variable;
	}

	/*
	 * The inference method tests wether the newly added variable conflicts with constraints of the problem or not
	 * @param var recently added Variable
	 * @return boolean true if adheres to constraints, false if doesnt
	 */
	public boolean inference(Variable var) {
		UniqueConstraint uq = new UniqueConstraint(var, problem);
		if(!uq.satisfy()){
			return false;
		}
		return true;
	}

	/*
	 * The assignmentFinished test checks if all Variables have been assigned a value
	 * @return boolean true if all Variables have values, false otherwise
	 */
	public boolean assignmentFinished() {
		for (Variable v : problem.variable) {
			if (v.isNotAssigned()) {
				return false;
			}
		}
		return true;
	}

	/*
	 * The aq3 method runs the aq3 algorithm for arc consistency
	 * @return boolean true if consistent, false otherwise
	 */
	public boolean aq3() {
		LinkedList<Variable> queue = new LinkedList<Variable>(problem.variable);
		while(!queue.isEmpty()) {
			Variable[] firstSecond =  removeFirst(queue);
			if (firstSecond.length == 0) {
				queue.pop();
				return true;
			} else {
				if (revise(firstSecond, problem)) {
					if (firstSecond[0].domains.size() == 0){
						return false;
					}
					queue.add(firstSecond[0]);
					queue.add(firstSecond[1]);
				}
			}
		}
		return true;
	}
	
	/*
	 * The rearrangeByMostConstrained rearranges the variables variable in the CSP problem by the most constrained variable
	 */
	public void rearrangeByMostConstrained() {
		int lowestNumOfDomain = 6;
		LinkedList<Variable> vars = problem.variable;
		LinkedList<Variable> newVars = new LinkedList<Variable>();
		for (int i = 0 ; i < vars.size() ; i++){
			Variable v = vars.get(i);
			if (v.domains.size()<=lowestNumOfDomain) {
				lowestNumOfDomain = v.domains.size();
				newVars.add(v);
			}
		}
		for (int i = 0 ; i < vars.size() ; i++){
			Variable v = vars.get(i);
			if (!newVars.contains(v)) {
				lowestNumOfDomain = v.domains.size();
				newVars.add(v);
			}
		}
		problem.variable = newVars;
	}

	/*
	 * The removeFirst method pops the queue for the aq3 method
	 * @param queue of variable graph edges
	 * @return array of 2 variables to be inserted into the aq3 method
	 */
	public Variable[] removeFirst(LinkedList<Variable> queue){
		if(queue.size() > 1){
			Variable first = queue.pop();
			Variable second = queue.pop();
			Variable[] results = {first, second};
			return results;
		} else {
			Variable[] results = {};
			return results;
		}
	}

	/*
	 * The selectUnassignedVariable returns a variable that hasn't been assigned a value
	 * @return Variable that hasn't been assigned a value
	 */
	public Variable selectUnassignedVariable() {
		for (Variable v : problem.variable) {
			if (v.isNotAssigned()) {
				return v;
			}
		}
		return null;
	}

	/*
	 * the revise method revises arc inconsistent arcs
	 * @param Variables to be checked for arc consistency, CSP problem of the problemset
	 * @return true if revised, false otherwise
	 */
	public boolean revise(Variable[] vars, CSP problem) {
		boolean revised = false;
		for (Variable var : vars) {
			RangeConstraint rq = new RangeConstraint(var);
			if(!rq.infer()){
				revised = true;
				return revised;
			}
		}
		return revised;
	}

}
