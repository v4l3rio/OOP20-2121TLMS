package settings;

public class AudioSettingsImpl implements AudioSettings {
	
	private final String musicGame = "thriller.wav";
	private final double volume = 10; 

	@Override
	public String getMusicGame() {
		return musicGame;
	}

	@Override
	public double getMusicVolume() {
		return volume;
	}

}
