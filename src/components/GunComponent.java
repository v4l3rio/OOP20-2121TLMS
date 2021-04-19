package components;

import com.almasb.fxgl.entity.component.Component;
import model.Gun;
/**
 * Implements a component to attach the entity to its gun.
 */
public class GunComponent extends Component {
	//tracking multiple changes in the component's gun
	private boolean isChanged = false;
	//default gun, native one
	private Gun defaultGun;
	//current gun, might differ from default one
	private Gun currentGun;
	
	/**
	 * 
	 * @param gun the starting firearm of the Component to be attached to an Entity
	 */
	public GunComponent(Gun gun) {
		this.currentGun = gun;
		this.defaultGun = gun;
	}
	
	/**
	 * 
	 * @return whether the gun has changed more than once, used for handling timers
	 * @see GunCollisionFactoryImpl
	 */
	public boolean isChanged() {
		return isChanged;
	}

	/**
	 * Sets isChanged field
	 * @param isChanged keeps track of multiple changes, used for handling timers
	 * @see GunCollisionFactoryImpl
	 */
	public void setChanged(boolean isChanged) {
		this.isChanged = isChanged;
	}
	
	/**
	 * 
	 * @return the current gun, used to handle gun interactions (shots, damage, etc.)
	 */
	public Gun getCurrentGun() {
		return currentGun;
	}

	/**
	 * Changes current gun
	 * @param currentGun
	 */
	public void setCurrentGun(Gun currentGun) {
		this.currentGun = currentGun;
	}
	
	/**
	 * 
	 * @return whether the gun is the default
	 */
	public boolean isDefault() {
		return this.currentGun.isSameTypeAs(this.defaultGun);
	}
	
	/**
	 * 
	 * @return default firearm, to be clear it is the first gun assigned
	 */
	public Gun getDefaultGun() {
		return this.defaultGun;
	}
}
