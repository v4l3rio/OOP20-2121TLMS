package model.score;

import java.util.Set;

/**
 * This interface provides methods to set and get maps of the game.
 *
 */
public interface Maps {
	
	/**
	 * @return a set of maps
	 */
	Set<String> getMaps();
	
	/**
	 * 
	 * @param map
	 *            the map to remove
	 */
	void removeMap(String map);
	
	/**
	 * @param map
	 *            the map to add to the set
	 */
	void addMap(String map);

}
