package menu;

import java.io.IOException;

public interface MenuModel {

	void writeOnFile(String path,  Score<String, Integer> score) throws IOException;

}
