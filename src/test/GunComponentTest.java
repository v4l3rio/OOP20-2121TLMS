package test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import org.junit.jupiter.api.Test;

import components.GunComponent;
import factories.TexturedGunFactoryImpl;
import model.TexturedGun;

public class GunComponentTest {

	private final TexturedGun beretta92 = new TexturedGunFactoryImpl().createBeretta92();
	private final TexturedGun machineGun = new TexturedGunFactoryImpl().createMachineGun();
	private final TexturedGun magmaGun = new TexturedGunFactoryImpl().createMagmaGun();
	private final GunComponent gunComponent = new GunComponent(beretta92);
	
	@Test
	public void testComponent() {
		assertEquals(beretta92, gunComponent.getCurrentGun());
		assertEquals(gunComponent.getDefaultGun(), gunComponent.getCurrentGun());
		
		gunComponent.setCurrentGun(magmaGun);
		assertNotEquals(gunComponent.getDefaultGun(), gunComponent.getCurrentGun());
		assertEquals(magmaGun, gunComponent.getCurrentGun());
		
		gunComponent.setCurrentGun(machineGun);
		assertNotEquals(gunComponent.getDefaultGun(), gunComponent.getCurrentGun());
		assertEquals(machineGun, gunComponent.getCurrentGun());
	}
	
	
}
