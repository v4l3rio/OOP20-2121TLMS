package test;



import static org.junit.Assert.assertEquals;

import static org.junit.Assert.assertNotNull;

import model.TLMSType;
import model.Zombie;
import model.ZombieFemaleDecorator;
import model.Moveable.TypeOfMovement;
import model.ZombieMaleDecorator;

public class ZombieTest {
	
	private static final int DEFAULT_LIFE = 10;
	private static final int DEFAULT_SPEED = 120;
	private static final int DEFAULT_DAMAGE = 2;
	private static final TypeOfMovement DEFAULT_TYPE_OF_MOVEMENT = TypeOfMovement.RANDOM;
	
	
	private static final String MALE_IDLE_PATH = "assets/textures/zombie/zombie0/zombie_idle.png";
	private static final String MALE_WALK_PATH = "assets/textures/zombie/zombie0/zombie_walk.png";
	private static final String MALE_ATTACK_PATH = "assets/textures/zombie/zombie0/zombie_attack.png";
	private static final String MALE_DEAD_PATH = "assets/textures/zombie/zombie0/zombie_dead.png";
	
	private static final String FEMALE_IDLE_PATH = "assets/textures/zombie/zombie1/zombie_idle.png";
	private static final String FEMALE_WALK_PATH = "assets/textures/zombie/zombie1/zombie_walk.png";
	private static final String FEMALE_ATTACK_PATH = "assets/textures/zombie/zombie1/zombie_attack.png";
	private static final String FEMALE_DEAD_PATH = "assets/textures/zombie/zombie1/zombie_dead.png";
	
	


	@org.junit.Before
	public void initFactory() {

	}

	@org.junit.Test
	public void testZombieCreation() {
		final Zombie zombie = new Zombie(DEFAULT_LIFE, DEFAULT_SPEED, DEFAULT_DAMAGE, DEFAULT_TYPE_OF_MOVEMENT);
		
		assertNotNull(zombie.getUUID());
		assertEquals(DEFAULT_LIFE, zombie.getLife());
		assertEquals(DEFAULT_SPEED, zombie.getSpeed());
		assertEquals(DEFAULT_DAMAGE, zombie.getDamage());
	
	}
	
	@org.junit.Test
	public void testZombieDecoration() {
		final ZombieMaleDecorator maleZombie = new ZombieMaleDecorator(new Zombie(DEFAULT_LIFE, DEFAULT_SPEED, DEFAULT_DAMAGE, DEFAULT_TYPE_OF_MOVEMENT));
		final ZombieFemaleDecorator femaleZombie = new ZombieFemaleDecorator(new Zombie(DEFAULT_LIFE, DEFAULT_SPEED, DEFAULT_DAMAGE, DEFAULT_TYPE_OF_MOVEMENT));
		
		assertEquals(DEFAULT_LIFE, maleZombie.getLife());
		assertEquals(DEFAULT_SPEED, maleZombie.getSpeed());
		assertEquals(DEFAULT_DAMAGE, maleZombie.getDamage());
		
		assertEquals(DEFAULT_LIFE, femaleZombie.getLife());
		assertEquals(DEFAULT_SPEED, femaleZombie.getSpeed());
		assertEquals(DEFAULT_DAMAGE, femaleZombie.getDamage());
		
		assertEquals(MALE_IDLE_PATH, maleZombie.getTexture().getTextureMap().get(TLMSType.IDLE));
		assertEquals(MALE_WALK_PATH, maleZombie.getTexture().getTextureMap().get(TLMSType.WALK));
		assertEquals(MALE_ATTACK_PATH, maleZombie.getTexture().getTextureMap().get(TLMSType.ATTACK));
		assertEquals(MALE_DEAD_PATH, maleZombie.getTexture().getTextureMap().get(TLMSType.DEAD));
		
		assertEquals(FEMALE_IDLE_PATH, femaleZombie.getTexture().getTextureMap().get(TLMSType.IDLE));
		assertEquals(FEMALE_WALK_PATH, femaleZombie.getTexture().getTextureMap().get(TLMSType.WALK));
		assertEquals(FEMALE_ATTACK_PATH, femaleZombie.getTexture().getTextureMap().get(TLMSType.ATTACK));
		assertEquals(FEMALE_DEAD_PATH, femaleZombie.getTexture().getTextureMap().get(TLMSType.DEAD));
		
		assertEquals(DEFAULT_TYPE_OF_MOVEMENT.label, maleZombie.getMovementStrategy());
		assertEquals(DEFAULT_TYPE_OF_MOVEMENT.label, femaleZombie.getMovementStrategy());
	}

		

	
}