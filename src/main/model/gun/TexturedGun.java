package main.model.gun;

import java.util.Map;

import main.model.TLMSType;
import main.model.Texture;

//adds texture to gun logics
public abstract class TexturedGun extends AbstractGun {
	
	private final Texture texture = new Texture();
	//calls super constructor and adds all textures to textures
	public TexturedGun(final int shotDMG, final int maxAmmo, final double shotSpeed, final Map<TLMSType, String> texturePaths) {
		super(shotDMG, maxAmmo, shotSpeed);
		texturePaths.entrySet().forEach(e -> {
			texture.addTexture(e.getKey(), e.getValue());
		});
	}
	/**
	 * 
	 * @return the texture map straight from texture's method
	*/
	public Texture getTexture() {
		return this.texture;
	}
}
