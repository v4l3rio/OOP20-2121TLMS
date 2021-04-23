package settings;

/**
 * SystemSettingsImpl implements {@link SystemSettings} interface
 */
public final class SystemSettingsImpl implements SystemSettings {
	
	private static final int TILE_SIZE = 32;
	private static final int NUM_TILES_WIDTH = 62;
	private static final int NUM_TILES_HEIGHT = 35;
	private static final int WIDTH = NUM_TILES_WIDTH * TILE_SIZE; 
    private static final int HEIGHT = NUM_TILES_HEIGHT * TILE_SIZE; 
    private static final String TITLE = "2021: The last man standing";
    private static final String VERSION = "2.0";

	@Override
	public int getWidth() {
		return WIDTH;
	}

	@Override
	public int getHeight() {
		return HEIGHT;

	}
	

	@Override
	public String getTitle() {
		return TITLE;
	}

	@Override
	public String getVersion() {
		return VERSION;
	}
}
