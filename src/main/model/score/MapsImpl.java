package main.model.score;

import java.util.HashSet;
import java.util.Set;

/**
 * 
 * Implementation of {@link Maps}.
 * This class provides the set of all the maps present in the game.
 *
 */
public final class MapsImpl implements Maps {
	
	private final Set<String> maps = new HashSet<>(Set.of("Cemetery", "Canyon", "Pyramid"));

	@Override
	public Set<String> getMaps() {
		return Set.copyOf(maps);
	}

	@Override
	public void removeMap(final String map) {
		maps.remove(map);
	}

	@Override
	public void addMap(final String map) {
		maps.add(map);
	}

}
