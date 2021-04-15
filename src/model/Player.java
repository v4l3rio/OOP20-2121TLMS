package model;

public interface Player {

	int getSpeed();

	void setSpeed(int speed);

	int getHealt();

	void setHealt(int healt);

	int getNJumps();

	void setMaxJumps(int maxJumps);

	void resetNJumps();

	void decreaseJumps();

	int getJumpHeight();
	
	double getDimension();

}