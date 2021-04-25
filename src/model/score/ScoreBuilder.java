package model.score;

import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * 
 * Interface that provides methods to create easier a {@link Score}.
 *
 * @param <U>
 *          the user name
 * @param <V>
 *          the score
 */
public interface ScoreBuilder<U, V> {


	/**
	 * @param name
	 * 	          A string with the user name
	 * @return
	 *		  The {@link ScoreBuilder} builder with user name
	 */
	ScoreBuilder<U, V> nameFromString(String name);

	/**
	 * @param path
	 * 	          A string with the path of the user name file
	 * @return The {@link ScoreBuilder} builder with user name
	 * @throws FileNotFoundException 
	 * 		        if the named file does not exist
	 * @throws IOException
	 * 		       if an I/O error occurs
	 */
	ScoreBuilder<U, V> nameFromPath(String path) throws FileNotFoundException, IOException ;

	/**
	 * @param score
	 * 		    The integer score of the game
	 * @return The {@link ScoreBuilder} builder with score
	 */
	ScoreBuilder<U, V> score(Integer score);

	/**
	 * 
	 * @return A {@link Score} only if there are not exceptions
	 * @throws IllegalStateException
	 *             if name or score are unset
	 * @throws IOException
	 *             if an I/O error occurs
	 */
	Score<U, V> build();
}
