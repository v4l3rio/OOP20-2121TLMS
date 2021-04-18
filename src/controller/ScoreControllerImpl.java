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
import model.score.ScoreModel;
import model.score.ScoreModelImpl;

/**
 * Implementation of {@link ScoreController} 
 */
public class ScoreControllerImpl implements ScoreController {
	
	public static final String PATH_SCORE = "src/assets/score/score.json";
    public static final String PATH_USER = "src/assets/score/userName.json";
	
	private final ScoreModel model= new ScoreModelImpl();

	@Override
	public void updateScore(Score<String, Integer> score) throws IOException {
		
		List<String> list = Files.lines(Paths.get(PATH_SCORE)).collect(Collectors.toList());
		
		if(model.isInTopThree(list.stream(), score.getScore())) {
			BufferedWriter writer = new BufferedWriter(new FileWriter(new File(PATH_SCORE)));
			list = model.updateRanking(list, score);
			writer.write(list.get(0) + "\n");
			writer.append(list.get(1) + "\n");
			writer.append(list.get(2));
			writer.close();
		} 
	}

	@Override
	public List<String> getRanking() throws IOException {
		
		List<String> list = new ArrayList<>();
		Stream<String> stream = Files.lines(Paths.get(PATH_SCORE));
		
		stream.forEach(l -> 
			list.add( l.split(" ")[1] + " " + l.split(" ")[0])
		);
		stream.close();
		
		return list;
	}
}
