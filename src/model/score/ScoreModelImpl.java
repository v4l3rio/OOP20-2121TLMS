package model.score;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

/**
 *  ScoreModelImpl implements the {@link ScoreModel} interface
 */
public class ScoreModelImpl implements ScoreModel {

	@Override
	public boolean isInTopThree(Stream<String> stream, Integer score) {	
		return stream.map(l -> l.split(" "))
				.map(s -> Integer.valueOf(s[0]))
				.filter(n -> n>score).count()!=3;
	}

	@Override
	public List<String> updateRanking(List<String> list, Score<String, Integer> newScore) {
		List<String> newList = new ArrayList<>(list);
		String strName = newScore.getName();
		String strScore = String.valueOf(newScore.getScore());
		newList.add(strScore + " " + strName);
		newList.sort((String s1, String s2) -> 
			Integer.valueOf(s2.split(" ")[0])
			.compareTo(Integer.valueOf(s1.split(" ")[0]))
		);
		newList.remove(list.size() - 1);
		return newList;
	}

}
