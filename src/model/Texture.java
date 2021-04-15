package model;

import java.util.HashMap;
import java.util.Map;

/**
 * 
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
