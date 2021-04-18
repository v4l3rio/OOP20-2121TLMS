package components;

import com.almasb.fxgl.entity.component.Component;
import model.Firearm;
/**
 * Implements a component to attach the entity to its firearm.
 */
public class FirearmComponent extends Component {
	//tracking multiple changes in the component's firearm
	private boolean isChanged = false;
	//default firearm, native one
	private Firearm defaultFirearm;
	//current firearm, might differ from default one
	private Firearm currentFirearm;
	
	/**
	 * 
	 * @param firearm the starting firearm of the Component to be attached to an Entity
	 */
	public FirearmComponent(Firearm firearm) {
		this.currentFirearm = firearm;
		this.defaultFirearm = firearm;
	}
	
	/**
	 * 
	 * @return whether the firearm has changed more than once, used for handling timers
	 * @see FirearmCollisionFactoryImpl
	 */
	public boolean isChanged() {
		return isChanged;
	}

	/**
	 * Sets isChanged field
	 * @param isChanged keeps track of multiple changes, used for handling timers
	 * @see FirearmCollisionFactoryImpl
	 */
	public void setChanged(boolean isChanged) {
		this.isChanged = isChanged;
	}
	
	/**
	 * 
	 * @return the current firearm, used to handle firearm interactions (shots, damage, etc.)
	 */
	public Firearm getCurrentFirearm() {
		return currentFirearm;
	}

	/**
	 * Changes current firearm
	 * @param currentFirearm
	 */
	public void setCurrentFirearm(Firearm currentFirearm) {
		this.currentFirearm = currentFirearm;
	}
	
	/**
	 * 
	 * @return whether the firearm is the default
	 */
	public boolean isDefault() {
		return this.currentFirearm.isSameTypeAs(this.defaultFirearm);
	}
	
	/**
	 * 
	 * @return default firearm, to be clear it is the first firearm assigned
	 */
	public Firearm getDefaultFirearm() {
		return this.defaultFirearm;
	}
}
