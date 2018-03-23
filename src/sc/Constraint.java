package sc;
import java.util.*;

/*
 * The abstract Constraint class models the following Constraint classes
 */
public abstract class Constraint {
	
	/*
	 * The method infer revises the deomains during the ac3 algorithm
	 * @return true if arc consistent domain found, false otherwise
	 * (non-Javadoc)
	 * @see sc.Constraint#infer()
	 */
	public abstract boolean infer();
	
	/*
	 * The method satisfy checks if newly add variable satisfies the constraint for all variables
	 * @return true if satisfies, false otherwise
	 * (non-Javadoc)
	 * @see sc.Constraint#infer()
	 */
	public abstract boolean satisfy();
}
