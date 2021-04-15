package menu;

public interface Score<U, V> {
	
	U getName();
	
	void setName(U name);
	
	V getScore();

	void setScore(V score);
	
}
