package controller;

import java.io.IOException;
import java.util.List;

import model.score.Score;

/**
 * The ScoreController interface provides methods to read/write ranking
 */
public interface ScoreController {
	
	/**
	 * @param score
	 *            The new score to insert in ranking
	 * @throws IOException
	 * 		       if an I/O error occurs
	 */
	void updateScore(Score<String, Integer> score) throws IOException;

	/**
	 * 
	 * @return the ranking in a list
	 * @throws IOException
	 *             if an I/O error occurs
	 */
	List<String> getRanking() throws IOException;

}
