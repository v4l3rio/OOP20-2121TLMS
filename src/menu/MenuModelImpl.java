package menu;

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

public class MenuModelImpl implements MenuModel {
	
	private File jsonFile;
	
	@Override
	public void writeOnFile(String pathScore, Score<String, Integer> score) throws IOException  {
		
		String strName = score.getName();
		String strScore = String.valueOf(score.getScore());
		Stream<String> stream = Files.lines(Paths.get(pathScore));
		List<String> list = new ArrayList<>(stream.collect(Collectors.toList()));
		stream.close();
		this.jsonFile = new File(pathScore);
		if(isTopThree(list.stream(), score.getScore())) {
			BufferedWriter writer = new BufferedWriter(new FileWriter(jsonFile));
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
	
	private boolean isTopThree(Stream<String> stream, Integer score) {	
		return stream.map(l -> l.split(" "))
				.map(s -> Integer.valueOf(s[0]))
				.filter(n -> n>score).count()!=3;
	}
}
