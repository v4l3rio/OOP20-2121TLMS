package model;

import static com.almasb.fxgl.dsl.FXGL.getSettings;
import static com.almasb.fxgl.dsl.FXGL.*;

import com.almasb.fxgl.audio.Music;

public class TLMSMusic {
	
	private Music gameMusic;
	private String backgroundSong;
	
	public TLMSMusic(final String songName, final double volume){
		
		this.backgroundSong = songName;		
		this.gameMusic = getAssetLoader().loadMusic(this.backgroundSong);
		getSettings().setGlobalMusicVolume(volume);		
	}
	
	public Music getMusic() {
		return this.gameMusic;
	}
	
	

}
