package test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import model.score.JsonScore;
import model.score.Score;
import model.score.ScoreModel;
import model.score.ScoreModelImpl;

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

}
