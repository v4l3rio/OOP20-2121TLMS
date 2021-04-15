package components;

import com.almasb.fxgl.entity.component.Component;

/**
 * 
 * @version 1.1
 * This component gives an entity the ability to do damage
 */
public class DamagingComponent extends Component {
	
	int damageOfComponent = 0;
	
	/**
	 * 
	 * @param damageOfComponent, damage value of this specific entity
	 */
	public DamagingComponent(int damageOfComponent) {
		this.damageOfComponent = damageOfComponent;
	}
	
	public int getDamage(){
		return this.damageOfComponent;
	}

}
