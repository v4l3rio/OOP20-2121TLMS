package main.model.score;

/**
 * The POJO Score interface provides getter and setter
 * methods for user name and score.
 * @param <U>
 * 		      the left element type: the user name
 * @param <V>
 * 		      the right element type: the score
 */
public interface Score<U, V> {
	
	/**
	 * @return
	 * 	   the user name 
	 */
	U getName();
	
	/**
	 * @param name
	 *     set the user name
	 */
	void setName(U name);
	
	/**
	 * @return
	 * 	    the score
	 */
	V getScore();

	/**
	 * @param score
	 *     set the score	
	 */
	void setScore(V score);
	
}
