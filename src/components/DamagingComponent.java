package components;

import com.almasb.fxgl.entity.component.Component;

/**
 * This component gives an entity the ability to do damage
 */
public class DamagingComponent extends Component {
	
	private int damageOfComponent = 0;
	
	/**
	 * @param damageOfComponent, damage value of this specific entity
	 */
	public DamagingComponent(int damageOfComponent) {
		this.damageOfComponent = damageOfComponent;
	}
	
	/**
	 * @return damage of this entity
	 */
	public int getDamage(){
		return this.damageOfComponent;
	}

}
