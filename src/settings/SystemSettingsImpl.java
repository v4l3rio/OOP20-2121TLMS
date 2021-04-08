package settings;


//controllare la risoluzione per schermi piu piccoli
public class SystemSettingsImpl implements SystemSettings {
	
	private final int TILE_SIZE = 32;
	private final int NUM_TILES_WIDTH = 62;
	private final int NUM_TILES_HEIGHT = 35;
	private final int WIDTH = NUM_TILES_WIDTH * TILE_SIZE; 
    private final int HEIGHT = NUM_TILES_HEIGHT * TILE_SIZE; 
    private final String title = "2021: The last man standing";
    private final String version = "1.0";

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
		return this.title;
	}

	@Override
	public String getVersion() {
		return this.version;
	}
}
