package main.components;

import com.almasb.fxgl.dsl.components.HealthIntComponent;

/**
 * Utility that defines the types of components used
 */

public final class ComponentUtils {
	
	public static final Class<HealthIntComponent> HEALTH_COMPONENT = HealthIntComponent.class;

	public static final Class<DamagingComponent> DAMAGING_COMPONENT = DamagingComponent.class;
	
	public static final Class<PlayerComponent> PLAYER_COMPONENT = PlayerComponent.class;
	
	public static final Class<TextureComponent> PLAYERTEXTURE_COMPONENT = TextureComponent.class;
	
	public static final Class<ZombieTextureComponent> TEXTURE_COMPONENT = ZombieTextureComponent.class;
	
	public static final Class<GunComponent> GUN_COMPONENT = GunComponent.class;
	
	public static final Class<RandomMovementComponent> RANDOM_MOVEMENT_COMPONENT = RandomMovementComponent.class;
	
	public static final Class<FollowPlayerComponent> FOLLOW_MOVEMENT_COMPONENT = FollowPlayerComponent.class;
	
	public static final Class<FirePowerComponent> FIREPOWER_COMPONENT = FirePowerComponent.class;
	
	private ComponentUtils() {}
}
