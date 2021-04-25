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
import java.util.Arrays;
import java.util.List;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import model.score.Maps;
import model.score.MapsImpl;
import model.score.Pair;
import model.score.Score;
import model.score.ScoreModel;
import model.score.ScoreModelImpl;

/**
 * Implementation of {@link ScoreController}. 
 */
public final class ScoreControllerImpl implements ScoreController {
	
    private static final String SEPARATOR = File.separator;
	private static final String FOLDER_NAME = System.getProperty("user.home") + SEPARATOR + "TLMS";
    private static final String FILE_NAME_RANKING = FOLDER_NAME + SEPARATOR + "ranking.json";
    private final MapController mapController = new MapControllerImpl();
	private final ScoreModel scoreModel = new ScoreModelImpl();
	private final UserNameController userNameController = new UserNameControllerImpl();
	private List<String> list = new ArrayList<>();

	@Override
	public void firstGame() throws IOException {
		try {
			this.list = Files.lines(Paths.get(FILE_NAME_RANKING)).collect(Collectors.toList());
			BufferedReader reader = new BufferedReader(new FileReader(UserNameControllerImpl.FILE_NAME_USER));
		    @SuppressWarnings("unused")
		    final String userName = reader.readLine(); //unused but useful to know if it's the firt game ever
		    reader.close();
		} catch (IOException e) {
			createFolder(FOLDER_NAME);
			createRankingFile(FILE_NAME_RANKING);
		}	
	}
	
	/**
	 * Method to create game folder to save game data.
	 * @param folderName
	 *            name of the folder
	 * @throws IOException
	 *             if an I/O error occurs
	 */
	private void createFolder(final String folderName) throws IOException {
		Files.createDirectories(Paths.get(folderName));
	}

	/**
	 * Method to create ranking file.
	 * @param fileName
	 *            path of the file
	 * @throws IOException
	 *             if an I/O error occurs
	 */
	private void createRankingFile(final String fileName) throws IOException {
		final FileWriter fw = new FileWriter(fileName, true);
		final BufferedWriter bw = new BufferedWriter(fw);
	    bw.write("Cemetery:35 CHRISTOPHER,20 QUENTIN,10 STANLEY\n"
	    		+ "Pyramid:55 SPIKE,30 ALFRED,12 WOODY\n"
	    		+ "Canyon:90 DAVID,40 FEDERICO,7 FRANCIS");
	    bw.close();
	    fw.close();
	}

	
	@Override
	public void updateScore(final Score<String, Integer> score) throws IOException {

		final String map = mapController.readMap();
		/* Selection of the correct line of the file to update*/
		final String line = Files.lines(Paths.get(FILE_NAME_RANKING))
							    .filter(l -> l.contains(map))
								.collect(Collectors.joining());
		/* The previous ranking without the updated line is saved*/
		final List<String> previousRanking = Files.lines(Paths.get(FILE_NAME_RANKING))
			    .filter(l -> !l.contains(map))
				.collect(Collectors.toList());
		/*The line is split by ":" */
		final List<String> rankingList = Arrays.stream(line.split(":")).collect(Collectors.toList());
		/*Selection of the second half of the string. The one with the ranking*/
		final String rankingString = rankingList.get(1);
		final Supplier<Stream<String>> supplier = () -> Arrays.stream(rankingString.split(","));

	    if (scoreModel.isInTopThree(supplier.get(), score.getScore())) {
	    	/*Update the file with the new line and the other ranking lines previously saved*/
	    	final BufferedWriter writer = new BufferedWriter(new FileWriter(new File(FILE_NAME_RANKING)));
			list = supplier.get().collect(Collectors.toList());
			list = scoreModel.updateRanking(list, score);
			writer.write(map + ":" 
						+ list.get(0) + ","
						+ list.get(1) + ","
						+ list.get(2) + "\n");
			writer.append(previousRanking.get(0) + "\n");
			writer.append(previousRanking.get(1) + "\n");
			writer.close();
	    } 
	}
	
    @Override
	public List<Pair<String, List<String>>> getRanking() throws IOException {

    	final List<String> rankingList = new ArrayList<>();
    	final Supplier<Stream<String>> rankingStreamSupplier = () -> {
			try {
				return Files.lines(Paths.get(FILE_NAME_RANKING));
			} catch (IOException e) {
				e.printStackTrace();
			}
			return null;
		};
    	final Maps maps = new MapsImpl();
    	/* Addition to the String List of the rankings, one for each map*/
    	maps.getMaps().forEach(map -> 
	    	rankingList.add(rankingStreamSupplier
	    		.get()
			    .filter(l -> l.contains(map))
				.collect(Collectors.joining())
				)
    	); 
    	return List.copyOf(scoreModel.rankingListRefactor(rankingList));
	}
}
