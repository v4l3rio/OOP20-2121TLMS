package test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import org.junit.jupiter.api.Test;
import model.TLMSType;
import components.GunComponent;
import factories.TexturedGunFactoryImpl;
import model.TexturedGun;

public class GunComponentTest {
	
	private final TexturedGun beretta92 = new TexturedGunFactoryImpl()
			.getTexturedGun(TLMSType.BERETTA92);
	private final TexturedGun machineGun = new TexturedGunFactoryImpl()
			.getTexturedGun(TLMSType.MACHINEGUN);
	private final TexturedGun magmaGun = new TexturedGunFactoryImpl()
			.getTexturedGun(TLMSType.MAGMAGUN);
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
