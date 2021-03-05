package model;

public interface Weapon {
	public enum Direction{
		RIGHT,
		LEFT
	}
	void shoot(Direction dir) throws IllegalAccessException;
	void setAmmo(int n);
	int getReloadTime();
	int getAmmo();
}
