package controller;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import application.TheLastManStandingApp;
import model.score.Score;

public class ScoreControllerImpl implements ScoreController {

	@Override
	public void updateScore(Score<String, Integer> score) throws IOException {
		
		String strName = score.getName();
		String strScore = String.valueOf(score.getScore());
		Stream<String> stream = Files.lines(Paths.get(TheLastManStandingApp.PATH_SCORE));
		List<String> list = new ArrayList<>(stream.collect(Collectors.toList()));
		
		stream.close();
		if(isInTopThree(list.stream(), score.getScore())) {
			BufferedWriter writer = new BufferedWriter(new FileWriter(new File(TheLastManStandingApp.PATH_SCORE)));
			list.add(strScore + " " + strName);
			list.sort((String s1, String s2) -> 
				Integer.valueOf(s2.split(" ")[0])
				.compareTo(Integer.valueOf(s1.split(" ")[0]))
			);
			list.remove(list.size() - 1);
			writer.write(list.get(0) + "\n");
			writer.append(list.get(1) + "\n");
			writer.append(list.get(2));
			writer.close();
		} 
	}

	@Override
	public List<String> getRanking() throws IOException {
		
		List<String> list = new ArrayList<>();
		Stream<String> stream = Files.lines(Paths.get(TheLastManStandingApp.PATH_SCORE));
		
		stream.forEach(l -> 
			list.add( l.split(" ")[1] + " " + l.split(" ")[0])
		);
		stream.close();
		
		return list;
	}
	
	private boolean isInTopThree(Stream<String> stream, Integer score) {	
		return stream.map(l -> l.split(" "))
				.map(s -> Integer.valueOf(s[0]))
				.filter(n -> n>score).count()!=3;
	}
}
