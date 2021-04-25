package settings;

/**
*  Pojo interface of system settings.
 */
public interface SystemSettings {
	
	/**
	 * @return 
	 *     the width of the window
	 */
	int getWidth();
	
	/**
	 * @return
	 *      the height of the window
	 */
	int getHeight();
	
	/**
	 * @return
	 *     the game title
	 */
	String getTitle();
	
	/**
	 * @return
	 *     the game version
	 */
	String getVersion();
	
}
