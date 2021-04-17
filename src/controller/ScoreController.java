package controller;

import java.io.IOException;
import java.util.List;

import model.score.Score;

/**
 * controller of the the read/write score system
 */
public interface ScoreController {
	
	void updateScore(Score<String, Integer> score) throws IOException;

	List<String> getRanking() throws IOException;

}
