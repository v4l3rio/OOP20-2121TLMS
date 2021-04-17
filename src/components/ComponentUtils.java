package components;

import com.almasb.fxgl.dsl.components.HealthIntComponent;

/**
 * 
 * @version 2.0
 * Utility that defines the types of components used
 */

public final class ComponentUtils {
	
	public static final Class<HealthIntComponent> HEALTH_COMPONENT = HealthIntComponent.class;

	public static final Class<DamagingComponent> DAMAGING_COMPONENT = DamagingComponent.class;
	
	public static final Class<PlayerComponent> PLAYER_COMPONENT = PlayerComponent.class;
	
	public static final Class<ZombieTextureComponent> TEXTURE_COMPONENT = ZombieTextureComponent.class;
	
	public static final Class<FirearmComponent> FIREARM_COMPONENT = FirearmComponent.class;
	
	private ComponentUtils() {}
}
