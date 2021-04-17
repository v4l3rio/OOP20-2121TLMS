package factories;

import model.Firearm;

public class FirearmFactoryImpl implements FirearmFactory{

	public Firearm createBeretta92() {
		return new Firearm(3, 5, 500, "assets/textures/beretta92Shot.png", "assets/textures/beretta92Gun.png") {
			@Override
			public void shoot() {
				super.setNAmmo(super.getNAmmo() - 1);
			}
		};
	}

	public Firearm createMagmaGun() {
		return new Firearm(7, 1, 1000, "assets/textures/magmaGunShot.png", "assets/textures/magmaGunTexture.png") {
			@Override
			public void shoot() {
				super.setNAmmo(super.getNAmmo() - 1);
			}
		};
	}

	public Firearm createMachineGun() {
		return new Firearm(5, 1, 1000, "assets/textures/machineGunShot.png", "assets/textures/machineGunTexture.png") {
			@Override
			public void shoot() {
				//do nothing, feature: unlimited ammo
			}
		};
	}
}
