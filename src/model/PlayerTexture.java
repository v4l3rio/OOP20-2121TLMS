package model;

import com.almasb.fxgl.dsl.FXGL;

import javafx.scene.image.Image;

public class PlayerTexture {

    private Image imageIdle, imageRun, imageJump, imageDeath, imageDemage;

	public PlayerTexture() {
		this.imageIdle = FXGL.image("Gunner_Blue_Idle.png");
		this.imageRun = FXGL.image("Gunner_Blue_Run.png");
		this.imageJump = FXGL.image("Gunner_Blue_Jump.png");
		this.imageDeath = FXGL.image("Gunner_Blue_Death.png");
	}
	
	public Image getImageIdle() {
		return this.imageIdle;
	}

	public Image getImageRun() {
		return this.imageRun;
	}

	public Image getImageJump() {
		return this.imageJump;
	}

	public Image getImageDeath() {
		return this.imageDeath;
	}
    
    
    
    

}
