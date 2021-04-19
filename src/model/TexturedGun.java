package model;

import java.util.HashMap;
import java.util.Map;

//adds texture to gun logics
public abstract class TexturedGun extends Gun {

	Texture texture = new Texture();
	//calls super constructor and adds all textures to textures
	public TexturedGun(int shotDMG, int maxAmmo, double shotSpeed, HashMap<TLMSType, String> texturePaths) {
		super(shotDMG, maxAmmo, shotSpeed);
		texturePaths.entrySet().forEach(e->{
			texture.addTexture(e.getKey(), e.getValue());
		});
	}
	/**
	 * 
	 * @return the texture map straight from texture's method
	*/
	public Map<TLMSType, String> getTextureMap() {
		return this.texture.getTextureMap();
	}
}