package test;

import model.Player;
import model.PlayerColor;
import model.PlayerImpl;
import model.PlayerTextures;
import model.TLMSType;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

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
		assertEquals(player.getColor(), player.getColor());
		assertEquals(player.getDimension(), 1);
		assertEquals(player.getMaxHeath(), 10);
		assertEquals(player.getSpeed(), 450);
		player.decreaseJumps();
		assertEquals(player.getNJumps(), 0);
	}

}
