package model.score;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

/**
 *  ScoreModelImpl implements the {@link ScoreModel} interface
 */
public class ScoreModelImpl implements ScoreModel {

	@Override
	public boolean isInTopThree(final Stream<String> stream, final Integer score) {	
		return stream.map(l -> l.split(" "))
				.map(s -> Integer.valueOf(s[0]))
				.filter(n -> n>score).count()!=3;
	}

	@Override
	public List<String> updateRanking(final List<String> list, final Score<String, Integer> newScore) {
		final List<String> newList = new ArrayList<>(list);
		final String strName = newScore.getName();
		final String strScore = String.valueOf(newScore.getScore());
		newList.add(strScore + " " + strName);
		newList.sort((String s1, String s2) -> 
			Integer.valueOf(s2.split(" ")[0])
			.compareTo(Integer.valueOf(s1.split(" ")[0]))
		);
		newList.remove(list.size() - 1);
		return newList;
	}

}
