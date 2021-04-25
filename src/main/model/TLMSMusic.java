package main.model;

import static com.almasb.fxgl.dsl.FXGL.getSettings;
import com.almasb.fxgl.dsl.FXGL;

import com.almasb.fxgl.audio.Music;

/**
 * 
 *	this class is used to manage background music
 */
public class TLMSMusic {
	
	private double volume;
	private Music gameMusic;
	private String backgroundSong;
	
	/**
	 * 
	 * @param songName
	 * @param volume
	 */
	public TLMSMusic(final String songName, final double volume){	
		this.volume = volume;
		this.backgroundSong = songName;		
		this.gameMusic = FXGL.getAssetLoader().loadMusic(this.backgroundSong);
		getSettings().setGlobalMusicVolume(volume);		
	}
	
	/**
	 * 
	 * @return gameMusic
	 */
	public Music getMusic() {
		return this.gameMusic;
	}
	
	public double getVolume() {
		return this.volume;
	}
	
	public void setVolume(final double volume) {
		this.volume = volume;
		getSettings().setGlobalMusicVolume(volume);	
	}
	
	public void noVolume() {
		this.setVolume(0);
	}
	
	public void changeSong(final String song) {
		this.backgroundSong = song;
		this.gameMusic = FXGL.getAssetLoader().loadMusic(this.backgroundSong);		
	}

}
