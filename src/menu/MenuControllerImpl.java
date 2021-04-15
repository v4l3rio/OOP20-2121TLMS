package menu;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class MenuControllerImpl implements MenuController {

	private MenuView menuView;
	private MenuModel menuModel;
    private String pathScore = "src/assets/score/score.json";
	
	public MenuControllerImpl() {
		this.menuModel = new MenuModelImpl();
	}
	public MenuControllerImpl(final String[] args) throws IOException {
		this.menuView = new MenuViewImpl(this, args);
	}

	@Override
	public void setView(MenuView view) {
		this.menuView = view;
	}

	@Override
	public void setModel(MenuModel model) {
		this.menuModel = model;
	}

	@Override
	public void updateScore(String pathScore, Score<String, Integer> score) throws IOException {
		menuModel.writeOnFile(pathScore, score);
	}
	@Override
	public List<String> getRanking() throws IOException {
		List<String> list = new ArrayList<>();
		Stream<String> stream = Files.lines(Paths.get(pathScore));
		stream.forEach(l -> list.add( l.split(" ")[1] + " " + l.split(" ")[0]));
		stream.close();
		return list;
		
	}
	
}
