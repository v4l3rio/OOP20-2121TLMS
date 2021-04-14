package model;

import java.util.HashMap;
import java.util.Map;


/**
 * 
 * @author Valerio Di Zio, Luca Cantagallo
 * @version 1.1
 */
public class Texture {

	Map<TLMSType, String> textureMap;
	
	public Texture() {
		this.textureMap = new HashMap<>();
	}
	
	public void addTexture(TLMSType type, String path) {
		this.textureMap.put(type, path);
	}
	
	public Map<TLMSType, String> getTextureMap(){
		return this.textureMap;
		
	}
}
