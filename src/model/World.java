package model;


import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class World {
	Map<UUID, Enemy> enemies;
	
	public World() {
		this.enemies = new HashMap<>();
	}
	
	public void addEnemy(Enemy enemy) {
		enemies.put(enemy.getUUID(), enemy);
	}
	
	public void removeEnemy(UUID uuid) {
		enemies.remove(uuid);
	}

	public Map<UUID, Enemy> getEnemies() {
		return enemies;
	}
}
