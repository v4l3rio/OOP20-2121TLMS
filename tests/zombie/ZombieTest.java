package zombie;



import static org.junit.Assert.*;
import org.junit.jupiter.api.extension.ExtendWith;

import static com.almasb.fxgl.dsl.FXGL.*;



import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.entity.SpawnData;

import collisions.PlayerZombieCollision;
import model.TLMSType;
import model.Zombie;
import model.ZombieFemaleDecorator;
import model.ZombieMaleDecorator;

public class ZombieTest {
	
	private static final int DEFAULT_LIFE = 10;
	private static final int DEFAULT_SPEED = 120;
	private static final int DEFAULT_DAMAGE = 2;
	
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
		Zombie zombie = new Zombie(DEFAULT_LIFE, DEFAULT_SPEED, DEFAULT_DAMAGE);
		
		assertNotNull(zombie.getUUID());
		assertEquals(DEFAULT_LIFE, zombie.getLife());
		assertEquals(DEFAULT_SPEED, zombie.getSpeed());
		assertEquals(DEFAULT_DAMAGE, zombie.getDamage());
	
	}
	
	@org.junit.Test
	public void testZombieDecoration() {
		ZombieMaleDecorator maleZombie = new ZombieMaleDecorator(new Zombie(DEFAULT_LIFE, DEFAULT_SPEED, DEFAULT_DAMAGE));
		ZombieFemaleDecorator femaleZombie = new ZombieFemaleDecorator(new Zombie(DEFAULT_LIFE, DEFAULT_SPEED, DEFAULT_DAMAGE));
		
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
	}
	
	@org.junit.Test
	public void testZombieSpawn() {
		SpawnData zombieStupidStats = new SpawnData(10, 10);
		SpawnData zombieFollowStats = new SpawnData(10, 10);
		
		ZombieMaleDecorator maleZombie = new ZombieMaleDecorator(new Zombie(DEFAULT_LIFE, DEFAULT_SPEED, DEFAULT_DAMAGE));
		zombieStupidStats.put("zombie", maleZombie);
		ZombieFemaleDecorator femaleZombie = new ZombieFemaleDecorator(new Zombie(DEFAULT_LIFE, DEFAULT_SPEED, DEFAULT_DAMAGE));
		zombieStupidStats.put("zombie", femaleZombie);
	
		
//		spawn("stupidZombie");
//		spawn("followZombie");
		
//		assertFalse(getGameWorld().getEntitiesByType(TLMSType.ZOMBIE).isEmpty());
//		
//		assertEquals(2,getGameWorld().getEntitiesByType(TLMSType.ZOMBIE).size());
		
		
	}
	

	@org.junit.Test
	public void testMaleAndFemale() {
//		final IteratorOfLists<Integer> sp = this.factory.iterative(10);
//		assertEquals(sp.nextList(),List.of());
//		assertEquals(sp.nextList(),List.of(10));
//		sp.reset();
//		assertEquals(sp.nextList(),List.of());
//		assertEquals(sp.nextList(),List.of(10));
//		assertEquals(sp.nextList(),List.of(10,10));		
	}
	
	@org.junit.Test
	public void testCollisions() {
		Entity zombie = new Entity();
		Entity player = new Entity();
		PlayerZombieCollision coll = new PlayerZombieCollision(TLMSType.PLAYER, TLMSType.ZOMBIE);
		coll.onCollisionBegin(player, zombie);
	}

	
}