package controller;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Implementation of {@link MapController} .
 */
public final class MapControllerImpl implements MapController {

	private static final String SEPARATOR = File.separator;
	private static final String FOLDER_NAME = System.getProperty("user.home") + SEPARATOR + "TLMS";
	private static final String FILE_NAME_MAP = FOLDER_NAME + SEPARATOR + "map.json";
	
	@Override
	public void firstGame() throws IOException {
		try {
			final BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME_MAP));
		    @SuppressWarnings("unused")
		    final String userName = reader.readLine(); //unused but useful to know if it's the firt game ever
		    reader.close();
		} catch (IOException e) {
			createMapFile(FILE_NAME_MAP);
		}	
	}
	 /**
	  * Method to write map on file on the first start of game.
	  * @param fileName
	  *            the path of the file
	  * @throws IOException
	  *             if an I/O error occurs
	  */
	private void createMapFile(final String fileName) throws IOException {
		final FileWriter fw = new FileWriter(fileName, true);
		final BufferedWriter bw = new BufferedWriter(fw);
	    bw.write("Cemetery");
	    bw.close();
	    fw.close();
	}

	@Override
	public String readMap() throws IOException {
		try (BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME_MAP))) {
			final String map = reader.readLine();
			reader.close();
			return map;
		}
	}
	
	@Override
	public String readMapTMX() throws IOException {
		return readMap() + ".tmx";
	}
	
	@Override
	public void writeMap(final String map) throws IOException {
		try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME_MAP))) {
			writer.write(map);
		}
	}

}
