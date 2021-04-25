package model;

import java.util.HashMap;
import java.util.Map;

/**
 * This class allows the creation of texture maps
 */
public class Texture {

	/**
	 * Map that associates an enum to a string
	 */
	private final Map<TLMSType, String> textureMap;
	
	public Texture() {
		this.textureMap = new HashMap<>();
	}
	
	
	/**
	 * 
	 * @param type - Enum in TLMSType, defines the type of texture 
	 * {@link model.TLMSType#IDLE IDLE} 
	 * {@link model.TLMSType#ATTACK ATTACK} 
	 * {@link model.TLMSType#WALK WALK} 
	 * {@link model.TLMSType#RUN RUN}
	 * {@link model.TLMSType#SHOT SHOT}
	 * 
	 * @param path - Path to texture file
	 */
	public void addTexture(final TLMSType type, final String path) {
		this.textureMap.put(type, path);
	}
	
	/**
	 * 
	 * @return texture map created
	 */
	public Map<TLMSType, String> getTextureMap(){
		return this.textureMap;		
	}
}
