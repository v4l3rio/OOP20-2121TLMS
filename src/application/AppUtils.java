package application;
/**
 * Utility class used for fields, which are in the app decision domain.
 */
public final class AppUtils {
	
	/**
	 * starting from player x center, gun length to spawn shots at the right x.
	 */
	public static final double GUN_LENGHT = 45;
	/**
	 * gun prop spawn delay.
	 */
	public static final double GUN_SPAWN_DELAY = 6;
	/**
	 * not to put over-complicate guns, and exceed variables.
	 */
	public static final double RELOAD_TIME = 1.5;
	/**
	 * reload text x axis position, to have it centered.
	 */
	public static final int RELOAD_TEXT_X = 840;
	/**
	 * reload text y axis position, to have it centered.
	 */
	public static final int RELOAD_TEXT_Y = 150;
	/**
	 * gun spawning y axis.
	 */
	public static final int GUN_SPAWN_Y = -100;
	
	/**
	 * Max value of an x coordinate.
	 */
	public static final int MAXXCOORDINATES = 2000;
	
	/**
	 * Middle value of an x coordinate.
	 */
	public static final int MIDDLEXCOORDINATES = MAXXCOORDINATES / 2;
	
	/**
	 * A zero could be useful in a lot of cases.
	 */
	public static final int ZERO = 0;
	
	/**
	 * initial volume of the music in tenthes.
	 */
	public static final double INITIALBGMUSICVOLUME = 0.1;
	
	private AppUtils() { };
}
