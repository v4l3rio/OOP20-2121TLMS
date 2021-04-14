package components;

import com.almasb.fxgl.entity.component.Component;

import model.Firearm;

public class FirearmComponent extends Component {

	private boolean changed = false;
	
	public boolean hasChanged() {
		return changed;
	}

	public void setChanged(boolean hasChanged) {
		this.changed = hasChanged;
	}

	private Firearm currentFirearm;

	public FirearmComponent(Firearm firearm) {
		this.currentFirearm = firearm;
	}
	
	public Firearm getCurrentFirearm() {
		return currentFirearm;
	}

	public void setCurrentFirearm(Firearm currentFirearm) {
		this.currentFirearm = currentFirearm;
	}
	
	public boolean isDefault() {
		return this.currentFirearm.getName() == "Beretta92";
	}
}
