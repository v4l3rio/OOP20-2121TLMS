package components;

import com.almasb.fxgl.entity.component.Component;
import model.Firearm;

public class FirearmComponent extends Component {

	private boolean changed = false;
	private Firearm defaultFirearm;
	private Firearm currentFirearm;
	
	/**
	 * 
	 * @return whether the firearm has changed more than once, used for handling timers
	 * @see FirearmCollisionFactoryImpl
	 */
	public boolean hasChanged() {
		return changed;
	}

	/**
	 * 
	 * @param hasChanged keeps track of multiple changes, used for handling timers
	 * @see FirearmCollisionFactoryImpl
	 */
	public void setChanged(boolean hasChanged) {
		this.changed = hasChanged;
	}

	/**
	 * 
	 * @param firearm the starting firearm of the Entity to which the component is attached
	 */
	public FirearmComponent(Firearm firearm) {
		this.currentFirearm = firearm;
		this.defaultFirearm = firearm;
	}
	
	/**
	 * 
	 * @return the current firearm, used to handle firearm interactions (shots, damage, etc.)
	 */
	public Firearm getCurrentFirearm() {
		return currentFirearm;
	}

	/**
	 * 
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
		return this.currentFirearm.getFirearmName() == defaultFirearm.getFirearmName();
	}
}
