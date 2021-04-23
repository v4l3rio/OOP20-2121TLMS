package test;


import model.TLMSType;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import factories.TexturedGunFactoryImpl;
import model.AbstractGun;
import model.Gun;

public class GunTest {
	
	private final Gun gun = new AbstractGun(3, 5, 600) {
		@Override
		public void shoot() {
			this.setNAmmo(this.getNAmmo() - 1);
		}
	};
	
	@Test
	public void testShootingGun() {
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
	public void testGunTypes() {
		final Gun beretta92 = new TexturedGunFactoryImpl().getTexturedGun(TLMSType.BERETTA92);
		final Gun machineGun = new TexturedGunFactoryImpl().getTexturedGun(TLMSType.MACHINEGUN);
		final Gun magmaGun = new TexturedGunFactoryImpl().getTexturedGun(TLMSType.MAGMAGUN);

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
	
}
