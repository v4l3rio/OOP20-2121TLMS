package model.score;

import java.util.List;
import java.util.stream.Stream;

/**
 * The ScoreModel interface provides methods to update a top3 list.
 */
public interface ScoreModel {

	/**
	 * @param stream
	 *            the top three ranking stream
	 * @param score
	 *            the score to compare with the top3
	 * @return
	 *     true if the score is in the top3 and the ranking will be updated 
	 */
	boolean isInTopThree(Stream<String> stream, Integer score);
	
	/**
	 * 
	 * @param list
	 * 		      the list with the top3
	 * @param newScore
	 * 	          the score to insert in top3
	 * @return the list updated
	 */       
	List<String> updateRanking(List<String> list, Score<String, Integer> newScore);
	
	 /**
     * This method takes a list of strings. Each string is in the "map:USER1 score,USER2 score,USER3 score" form.
     * <p>
     * It returns a list of pairs. Each pair is in the (map, ["USER1 score","USER2 score","USER3 score"]) form
     * @param rankingList
     * @return
     */
    List<Pair<String,List<String>>> rankingListRefactor(final List<String> rankingList);
}
