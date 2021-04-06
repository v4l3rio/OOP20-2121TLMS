package models;

import java.util.UUID;

public interface Enemy {
	int getSpeed();
	int getLife();
	UUID getUUID();
	void setDamage(int dmg);
	int getDamage();
}