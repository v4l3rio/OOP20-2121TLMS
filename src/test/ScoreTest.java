package test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import main.model.score.JSonScoreBuilder;
import main.model.score.JsonScore;
import main.model.score.Score;
import main.model.score.ScoreModel;
import main.model.score.ScoreModelImpl;


public class ScoreTest {
	
	 @Test
	    public void testScoreModelTopThree() {
		 	final ScoreModel model = new ScoreModelImpl();
		 	final List<String> list = new ArrayList<>();
		 	list.add("15 MARK");
		 	list.add("51 LUKE");
		 	list.add("24 JOHN");
	        assertFalse(model.isInTopThree(list.stream(), 13));
	        assertFalse(model.isInTopThree(list.stream(), -10));
	        assertTrue(model.isInTopThree(list.stream(), 15));
	        assertTrue(model.isInTopThree(list.stream(), 100));
	    }

	 @Test
	    public void testScoreModelUpdateRanking() {
		 	final ScoreModel model = new ScoreModelImpl();
		 	final List<String> list = new ArrayList<>();
		 	Score<String, Integer> newScore = new JsonScore("MICHAEL", 45);
		 	list.add("19 MARK");
		 	list.add("51 LUKE");
		 	list.add("23 JOHN");
		 	final List<String> newList = new ArrayList<>();
		 	newList.add("51 LUKE");
		 	newList.add("45 MICHAEL");
		 	newList.add("23 JOHN");
	        assertEquals(model.updateRanking(list, newScore), newList);

	        list.clear();
	        newList.clear();

	        newScore = new JsonScore("MICHAEL", 55);
		 	list.add("19 MARK");
		 	list.add("52 LUKE");
		 	list.add("27 JOHN");
		 	newList.add("55 MICHAEL");
		 	newList.add("52 LUKE");
		 	newList.add("27 JOHN");
	        assertEquals(model.updateRanking(list, newScore), newList);

	        list.clear();
	        newList.clear();

	        newScore = new JsonScore("GUNTHER", 20);
		 	list.add("21 JOEY");
		 	list.add("25 ROSS");
		 	list.add("30 CHANDLER");
		 	newList.add("30 CHANDLER");
		 	newList.add("25 ROSS");
		 	newList.add("21 JOEY");
	        assertEquals(model.updateRanking(list, newScore), newList);
	    }

	 @Test
	    public void testJSonScoreBuilder() {
		 final Score<String, Integer> score = new JsonScore("Phoebe", 300);
		 final Score<String, Integer> score1 = new JSonScoreBuilder()
				 						    .nameFromString("Phoebe")
				 						    .score(300)
				 						    .build();
		 assertEquals(score, score1);
		 final Score<String, Integer> score2 = new JSonScoreBuilder()
				    .nameFromString("PHOEBE")
				    .score(300)
				    .build();
         assertNotEquals(score, score2);
		 final Score<String, Integer> score3 = new JSonScoreBuilder()
				 								   .nameFromString("John")
				                                   .score(300)
				                                   .build();
		 assertNotEquals(score, score3);
	 }

}
