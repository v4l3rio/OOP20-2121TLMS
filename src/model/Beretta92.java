package model;

import java.util.UUID;

public class Beretta92 extends Firearm{
	
	private final UUID uuid;
	
	public Beretta92() {
		this.uuid = UUID.randomUUID();
		shotDamage = 3;
	}
	
	public UUID getUUID() {
		return uuid;
	}
}
