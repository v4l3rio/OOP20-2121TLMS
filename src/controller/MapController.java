package controller;

import java.io.IOException;

/**
 * 
 * The MapController interface provides methods to read/write map chosen by user
 *
 */
public interface MapController {

	/**
	 * Create file map 
	 * @throws IOException 
	 *             if an I/O error occurs
	 */
	void firstGame() throws IOException;
	 
	/**
	  * @return
	  * 	the map wrote on file
	  * @throws IOException
	  *             if an I/O error occurs
	  */
	String readMap() throws IOException;
	
	/**
	  * @return
	  * 	the tmx map wrote on file
	  * @throws IOException
	  *             if an I/O error occurs
	  */
	String readMapTMX() throws IOException;
	
	/**
	 * @param map
	 *           the map to write on file
	 * @throws IOException
	 *             if an I/O error occurs
	 */
	void writeMap(final String map) throws IOException;
}
