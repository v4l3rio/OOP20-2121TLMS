package components;

import com.almasb.fxgl.entity.component.Component;

import model.Firearm;

public class FirearmComponent extends Component {

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
}
