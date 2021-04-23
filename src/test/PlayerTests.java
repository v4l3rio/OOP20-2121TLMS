package test;

import com.almasb.fxgl.entity.Entity;

import components.ComponentUtils;
import model.Player;
import model.PlayerColor;
import model.PlayerImpl;
import model.PlayerTextures;
import model.TLMSType;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class PlayerTests {	
	
	@Test
	public void coloredTextures(){
		PlayerTextures playerTexture = new PlayerTextures(PlayerColor.RED);
		assertEquals(playerTexture.getTexture().getTextureMap().get(TLMSType.IDLE), "Gunner_Red_Idle.png");
		assertNotEquals(playerTexture.getTexture().getTextureMap().get(TLMSType.IDLE), "Gunner_Blue_Idle.png");
		
		playerTexture = new PlayerTextures(PlayerColor.BLUE);
		assertEquals(playerTexture.getTexture().getTextureMap().get(TLMSType.IDLE), "Gunner_Blue_Idle.png");
	}
	
	@Test
	public void initialPlayerParameters() {
		final Player player = new PlayerImpl();
		assertEquals(player.getHealt(), player.getMaxHeath());
		assertEquals(player.getNJumps(), 1);
		player.decreaseJumps();
		assertEquals(player.getNJumps(), 0);
	}

}
