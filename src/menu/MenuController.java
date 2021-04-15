package menu;

import java.io.File;
import java.io.IOException;
import java.util.List;

public interface MenuController {

	void setView(MenuView view);

	void setModel(MenuModel model);

	void updateScore(String path, Score<String, Integer> score) throws IOException;

	List<String> getRanking() throws IOException;
}
