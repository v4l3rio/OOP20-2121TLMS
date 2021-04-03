package components;

import com.almasb.fxgl.entity.component.Component;


public class DamagingComponent extends Component {
	
	int damageOfComponent = 0;
	
	public DamagingComponent(int damageOfComponent) {
		this.damageOfComponent = damageOfComponent;
	}
	
	public int getDamage(){
		return this.damageOfComponent;
	}

}
