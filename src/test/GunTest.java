package test;


import main.model.TLMSType;
import main.model.gun.TexturedGun;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import org.junit.jupiter.api.Test;

import main.components.GunComponent;
import main.factories.TexturedGunFactory;
import main.factories.TexturedGunFactoryImpl;
import main.model.gun.AbstractGun;
import main.model.gun.Gun;

public class GunTest {
	
	private final TexturedGunFactory texturedGunFactory = new TexturedGunFactoryImpl();
	private final TexturedGun beretta92 = texturedGunFactory.getTexturedGun(TLMSType.BERETTA92);
	private final TexturedGun machineGun = texturedGunFactory.getTexturedGun(TLMSType.MACHINEGUN);
	private final TexturedGun magmaGun = texturedGunFactory.getTexturedGun(TLMSType.MAGMAGUN);
	
	@Test
	public void abstractGunTest() {
		final Gun gun = new AbstractGun(3, 5, 600) {
			@Override
			public void shoot() {
				this.setNAmmo(this.getNAmmo() - 1);
			}
		};
	
		for (int i = 1; i < gun.getMaxAmmo(); i++) {
			gun.shoot();
			assertEquals(gun.getMaxAmmo() - i, gun.getNAmmo());
		}

		gun.reload();
		assertEquals(5, gun.getMaxAmmo());
	
		assertEquals(3, gun.getDamage());
		assertEquals(5, gun.getMaxAmmo());
		assertEquals(600, gun.getShotspeed());
	
	}
	
	@Test
	public void gunTypesTest() {

		assertEquals(10, beretta92.getMaxAmmo());
		assertEquals(7, magmaGun.getNAmmo());
		for (int i = 0; i < 80; i++) {
			machineGun.shoot();
			assertTrue(machineGun.getNAmmo() > 0);
		}

		try {
			for (int i = 1; i <= beretta92.getMaxAmmo(); i++) {
				beretta92.shoot();
				assertEquals(beretta92.getMaxAmmo() - i, beretta92.getNAmmo());
			}
		} catch (IllegalStateException exception) {
			exception.printStackTrace();
		}

		try {
			for (int i = 1; i <= magmaGun.getMaxAmmo(); i++) {
				magmaGun.shoot();
				assertEquals(magmaGun.getMaxAmmo() - i, magmaGun.getNAmmo());
			}
		} catch (IllegalStateException exception) {
			exception.printStackTrace();
		}

		assertFalse(beretta92.isSameTypeAs(machineGun));
		assertFalse(beretta92.isSameTypeAs(magmaGun));
		assertFalse(magmaGun.isSameTypeAs(machineGun));
	}
	
	@Test
	public void componentTest() {
		final GunComponent gunComponent = new GunComponent(beretta92);
	
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
