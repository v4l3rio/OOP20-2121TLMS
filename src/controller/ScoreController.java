package controller;

import java.io.IOException;
import java.util.List;

import model.score.Pair;
import model.score.Score;

/**
 * The ScoreController interface provides methods to read/write ranking.
 */
public interface ScoreController {
	
	/**
	 * Create folder and files score 
	 * @throws IOException 
	 *             if an I/O error occurs
	 */
	void firstGame() throws IOException;
	/**
	 * @param score
	 *            The new score to insert in ranking
	 * @throws IOException
	 * 		       if an I/O error occurs
	 */
	void updateScore(Score<String, Integer> score) throws IOException;

	/**
	 * This method provide a list of all the rankings.
	 * A component of this list, a pair, is composed like this:
	 * <p>
	 *  ("Cemetery", ["Mark 30", "John 20", "Luke 12"]) 
	 * @return a list of pairs (String map, List<String> ranking)
	 * @throws IOException
	 *             if an I/O error occurs
	 */
	List<Pair<String, List<String>>> getRanking() throws IOException;
	
	/**
	  * @return
	  * 	 the last User saved on file
	  * @throws IOException
	  *             if an I/O error occurs
	  */
	String readUser() throws IOException;
	
	/**
	 * @param user
	 *           the user to write on file
	 * @throws IOException
	 *             if an I/O error occurs
	 */
	void writeUser(String user) throws IOException;

}
