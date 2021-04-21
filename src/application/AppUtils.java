package application;
/**
 * Utility class used for fields, which are in the app decision domain
 */
public final class AppUtils {
	//starting from player x center, gun length to spawn shots at the right x
	public static final double GUN_LENGTH = 30;
	//variables fixing shot spawning axis: it's formula would've been of huge complexity,
	//not worth it the calculus based on images: we want shot textured to follow requirements
	public static final double SHOT_Y_AXIS_FIX = 60;
	public static final double SHOT_X_AXIS_FIX = 48;
	//gun prop spawn delay
	public static final double GUN_SPAWN_DELAY = 6;

	private AppUtils() {};
}
