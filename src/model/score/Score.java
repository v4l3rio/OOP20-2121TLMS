package model.score;

/**
 * model the
 * @param <U>       	
 * @param <V>
 */
public interface Score<U, V> {
	
	/**
	 * @return
	 * 	   the username 
	 */
	U getName();
	
	/**
	 * @param name
	 *     set the username
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
