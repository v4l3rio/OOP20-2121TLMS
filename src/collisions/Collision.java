package collisions;


public interface Collision<T, U> {

    /**
     * {@inheritDoc}
     */
    void onCollision(T e1, U e2);
}
