package model;

import javafx.scene.image.Image;

public interface Firearm{
	
	public int getNAmmo();
	
	public void decAmmo();
	
    public int getShotDamage();
    
    public void recharge();
    
	public Image getShotTexture();
	
	public Image getWeaponTexture();
	
	public String getFirearmName();
}
