package models;

import java.util.UUID;

public interface Enemy {
	int getSpeed();
	int getLife();
	UUID getUUID();
}