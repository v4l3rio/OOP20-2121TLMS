package model;

import static com.almasb.fxgl.dsl.FXGL.getSettings;
import static com.almasb.fxgl.dsl.FXGL.*;

import com.almasb.fxgl.audio.Music;

public class TLMSMusic {
	
	private Music gameMusic;
	private String backgroundSong;
	
	public TLMSMusic(double volume){
		this.backgroundSong = "thriller.wav";
		
		this.gameMusic = getAssetLoader().loadMusic(this.backgroundSong);
		getSettings().setGlobalMusicVolume(volume);		
	}
	
	public Music getMusic() {
		return this.gameMusic;
	}
	
	

}
