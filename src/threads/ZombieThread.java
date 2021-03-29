package threads;
import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.entity.SpawnData;

import application.*;
import components.ZombieComponent;

public class ZombieThread implements Runnable {

	@Override
	public void run() {
		FXGL.getGameWorld().spawn("zombie", 50, 50);
		
	}
	
}
