package application;
/**
 * Utility class used for fields, which are in the app decision domain.
 */
public final class AppUtils {
	
	/**
	 * starting from player x center, gun length to spawn shots at the right x.
	 */
	public static final double GUN_LENGTH = 30;
	/**
	 * variables fixing shot spawning axis: it's formula would've been of huge complexity,
	 * /not worth it the calculus based on images: we want shot textured to follow requirements.
	 */
	public static final double SHOT_Y_AXIS_FIX = 60;
	/**
	 * variables fixing shot spawning axis: it's formula would've been of huge complexity,
	 * /not worth it the calculus based on images: we want shot textured to follow requirements.
	 */
	public static final double SHOT_X_AXIS_FIX = 48;
	/**
	 * gun prop spawn delay.
	 */
	public static final double GUN_SPAWN_DELAY = 6;
	/**
	 * not to put over-complexify guns, and exceed variables.
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
	
	private AppUtils() { };
}
