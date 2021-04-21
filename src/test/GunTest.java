package test;


import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import model.Gun;

public class GunTest {
	
	private final Gun gun = new Gun(3, 5, 600) {
		@Override
		public void shoot() {
			this.setNAmmo(this.getNAmmo()-1);
		}
	};
	
	@Test
	public void testShootingGun() {
		for(int i = 1; i < 5; i++) {
			gun.shoot();
			assertEquals(5-i, gun.getNAmmo());
		}
		gun.reload();
		assertEquals(5, gun.getMaxAmmo());
	}
}
