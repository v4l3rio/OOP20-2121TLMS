package collisions;


public interface Collision<T, U> {

    void onCollision(T entity1, U entity2);
    
}
