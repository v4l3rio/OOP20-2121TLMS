package components;

import com.almasb.fxgl.entity.component.Component;

/**
 * This component gives an entity the ability to do damage
 */
public class DamagingComponent extends Component {
	
	private final int damageOfComponent;
	
	/**
	 * @param damageOfComponent, damage value of this specific entity
	 */
	public DamagingComponent(final int damageOfComponent) {
		this.damageOfComponent = damageOfComponent;
	}
	
	/**
	 * @return damage of this entity
	 */
	public int getDamage(){
		return this.damageOfComponent;
	}

}
