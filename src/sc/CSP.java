package sc;
import java.util.*;

/*
 * The CSP class represents the TSP CSP that will be used
 */
public class CSP {
	LinkedList<Variable> variable;
	ArrayList<City> domain;
	ArrayList<Constraint> constraints;

	public CSP() {

		variable = new LinkedList<Variable>();
		domain = new ArrayList<City>();
		constraints = new ArrayList<Constraint>();

		/**
		 * City Construction
		 */
		City a = new City("a", new ArrayList<Edge>());
		City b = new City("b", new ArrayList<Edge>());
		City c = new City("c", new ArrayList<Edge>());
		City d = new City("d", new ArrayList<Edge>());
		City e = new City("e", new ArrayList<Edge>());
		City f = new City("f", new ArrayList<Edge>());
		City g = new City("g", new ArrayList<Edge>());

		/**
		 * Add edges for B & insert to domains
		 */
		ArrayList<Edge> edgesB = new ArrayList<Edge>();
		edgesB.add(new Edge(b, a, 2));
		edgesB.add(new Edge(b, c, 8));
		edgesB.add(new Edge(b, d, 2));
		edgesB.add(new Edge(b, e, 9));
		edgesB.add(new Edge(b, f, 4));
		edgesB.add(new Edge(b, g, 3));
		b.setEdges(edgesB);
		domain.add(b);

		/**
		 * Add edges for C & insert to domains
		 */
		ArrayList<Edge> edgesC = new ArrayList<Edge>();
		edgesC.add(new Edge(c, a, 1));
		edgesC.add(new Edge(c, b, 8));
		edgesC.add(new Edge(c, d, 5));
		edgesC.add(new Edge(c, e, 6));
		edgesC.add(new Edge(c, f, 4));
		edgesC.add(new Edge(c, g, 7));
		c.setEdges(edgesC);
		domain.add(c);

		/**
		 * Add edges for D & insert to domains
		 */
		ArrayList<Edge> edgesD = new ArrayList<Edge>();
		edgesD.add(new Edge(d, a, 8));
		edgesD.add(new Edge(d, b, 2));
		edgesD.add(new Edge(d, c, 5));
		edgesD.add(new Edge(d, e, 4));
		edgesD.add(new Edge(d, g, 3));
		d.setEdges(edgesD);
		domain.add(d);

		/**
		 * Add edges for E & insert to domains
		 */
		ArrayList<Edge> edgesE = new ArrayList<Edge>();
		edgesE.add(new Edge(e, a, 7));
		edgesE.add(new Edge(e, b, 9));
		edgesE.add(new Edge(e, c, 6));
		edgesE.add(new Edge(e, d, 4));
		edgesE.add(new Edge(e, f, 7));
		e.setEdges(edgesE);
		domain.add(e);

		/**
		 * Add edges for F & insert to domains
		 */
		ArrayList<Edge> edgesF = new ArrayList<Edge>();
		edgesF.add(new Edge(f, a, 7));
		edgesF.add(new Edge(f, b, 4));
		edgesF.add(new Edge(f, c, 4));
		edgesF.add(new Edge(f, e, 7));
		f.setEdges(edgesF);
		domain.add(f);

		/**
		 * Add edges for G & insert to domains
		 */
		ArrayList<Edge> edgesG = new ArrayList<Edge>();
		edgesG.add(new Edge(g, a, 9));
		edgesG.add(new Edge(g, b, 3));
		edgesG.add(new Edge(g, c, 7));
		edgesG.add(new Edge(g, d, 3));
		g.setEdges(edgesG);
		domain.add(g);

		/**
		 * Variable construction
		 */
		Variable city1 = new Variable("toko1", 1);
		city1.domains = new ArrayList<City>(domain);
		variable.add(city1);

		Variable city2 = new Variable("toko2", 2);
		city2.domains = new ArrayList<City>(domain);
		variable.add(city2);

		Variable city3 = new Variable("toko3", 3);
		city3.domains = new ArrayList<City>(domain);
		variable.add(city3);

		Variable city4 = new Variable("toko4", 4);
		city4.domains = new ArrayList<City>(domain);
		variable.add(city4);

		Variable city5 = new Variable("toko5", 5);
		city5.domains = new ArrayList<City>(domain);
		variable.add(city5);

		Variable city6 = new Variable("toko6", 6);
		city6.domains = new ArrayList<City>(domain);
		variable.add(city6);
	}

	/*
	 * The RangeConstraint class represents the constraint that the 1st and last city travelled must be a certain distance away from city A
	 */
	static class RangeConstraint extends Constraint {
		Variable var;
		public RangeConstraint (Variable var) {
			this.var = var;
		}
		
		/*
		 * The method infer revises the deomains during the ac3 algorithm
		 * @return true if arc consistent domain found, false otherwise
		 * (non-Javadoc)
		 * @see sc.Constraint#infer()
		 */
		public boolean infer() {
			if (var.order == 1 || var.order == 6) {
				for(City city : var.domains) {
					if (var.order == 1) {
						for (Edge e : city.edges) {
							if (e.origin.name.equals("a") || e.dest.name.equals("a")) {
								if (e.weight > 5) {
									var.domains.remove(city);
									return false;
								}
							}
						}
					}
					if (var.order == 6) {
						for (Edge e : city.edges) {
							if (e.origin.name.equals("a") || e.dest.name.equals("a")) {
								if (e.weight > 4) {
									var.domains.remove(city);
									return false;
								}
							}
						}
					}
				}
			}
			return true;
		}
		
		/*
		 * The method satisfy checks if newly add variable satisfies the constraint for all variables
		 * @return true if satisfies, false otherwise
		 * (non-Javadoc)
		 * @see sc.Constraint#infer()
		 */
		@Override
		public boolean satisfy() {
			if (var.order == 1 || var.order == 6) {
				if(var.assigned == null) {
					return true;
				}
				City city = var.assigned;
				if (var.order == 1) {
					for (Edge e : city.edges) {
						if (e.origin.equals('a')) {
							if (e.weight > 5) {
								return false;
							}
						}
					}
				}
				if (var.order == 6) {
					for (Edge e : city.edges) {
						if (e.dest.equals('a')) {
							if (e.weight > 4) {
								return false;
							}
						}
					}
				}

			}
			return true;
		}
	}

	static class UniqueConstraint extends Constraint {
		Variable var;
		CSP problem;

		public UniqueConstraint(Variable var1, CSP problem){
			this.var = var1;
			this.problem = problem;
		}

		/*
		 * The method infer revises the deomains during the ac3 algorithm, unused method
		 * @return true if arc consistent domain found, false otherwise
		 * (non-Javadoc)
		 * @see sc.Constraint#infer()
		 */
		public boolean infer(){
			return true;
		}

		/*
		 * The method satisfy checks if newly add variable satisfies the constraint for all variables
		 * @return true if satisfies, false otherwise
		 * (non-Javadoc)
		 * @see sc.Constraint#infer()
		 */
		@Override
		public boolean satisfy() {
			for (Variable solutionVar : problem.variable){
				if(solutionVar.name.equals(var.name)){
				}

				if (solutionVar.assigned != null && !(solutionVar.name.equals(var.name))) {
					if (var.assigned.name.equals(solutionVar.assigned.name)){
						return false;
					}
				}
			}
			return true;
		}
	}
}
