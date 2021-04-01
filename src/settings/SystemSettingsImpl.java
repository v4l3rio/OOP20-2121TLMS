package settings;

public class SettingSystemImpl implements SettingsSystem {
	
	private final int WIDTH = 62 * 32; //(int) Toolkit.getDefaultToolkit().getScreenSize().getWidth();
    private final int HEIGHT = 35 * 32; //(int) Toolkit.getDefaultToolkit().getScreenSize().getHeight();
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
