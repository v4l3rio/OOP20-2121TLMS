package controller;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import model.score.Score;
import model.score.ScoreModel;
import model.score.ScoreModelImpl;

/**
 * Implementation of {@link ScoreController} 
 */
public class ScoreControllerImpl implements ScoreController {
	
    private static final String SEPARATOR = File.separator;
	private static final String FOLDER_NAME = System.getProperty("user.home") + SEPARATOR + "TLMS";
    public static final String FILE_NAME_RANKING = FOLDER_NAME + SEPARATOR + "ranking.json";
    public static final String FILE_NAME_USER = FOLDER_NAME + SEPARATOR + "userName.json";
	
	private final ScoreModel model= new ScoreModelImpl();
	private List<String> list = new ArrayList<>();

	@Override
	public void firstGame() throws IOException {
		try {
			this.list = Files.lines(Paths.get(FILE_NAME_RANKING)).collect(Collectors.toList());
			final BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME_USER));
		    @SuppressWarnings("unused")
		    final String userName = reader.readLine(); //unused but useful to know if it's the firt game ever
		    reader.close();
		} catch (IOException e) {
			createFolder(FOLDER_NAME);
			createRankingFile(FILE_NAME_RANKING);
			createUserNameFile(FILE_NAME_USER);
		}	
	}

	private void createFolder(final String folderName) throws IOException {
		Files.createDirectories(Paths.get(folderName));
	}

	private void createUserNameFile(final String fileNameUser) throws IOException {
		final FileWriter fw = new FileWriter(fileNameUser, true);
		final BufferedWriter bw = new BufferedWriter(fw);
	    bw.write("STEVEN");
	    bw.close();
	    fw.close();
	}

	private void createRankingFile(final String fileName) throws IOException {
		final FileWriter fw = new FileWriter(fileName, true);
		final BufferedWriter bw = new BufferedWriter(fw);
	    bw.write("35 CHRISTOPHER\n"
	    		+ "20 QUENTIN\n"
	    		+ "10 STANLEY");
	    bw.close();
	    fw.close();
	}

	@Override
	public void updateScore(final Score<String, Integer> score) throws IOException {
		
		list = Files.lines(Paths.get(FILE_NAME_RANKING)).collect(Collectors.toList());
		
	    if(model.isInTopThree(list.stream(), score.getScore())){
	    	final BufferedWriter writer = new BufferedWriter(new FileWriter(new File(FILE_NAME_RANKING)));
			list = model.updateRanking(list, score);
			writer.write(list.get(0) + "\n");
			writer.append(list.get(1) + "\n");
			writer.append(list.get(2));				
			writer.close();
	    } 	
	}
	
    @Override
	public List<String> getRanking() throws IOException {
		
    	final List<String> list = new ArrayList<>();
    	final Stream<String> stream = Files.lines(Paths.get(FILE_NAME_RANKING));
		
		stream.forEach(l -> 
			list.add( l.split(" ")[1] + " " + l.split(" ")[0])
		);
		stream.close();
		
		return list;
	}
}
