package components;

import static com.almasb.fxgl.dsl.FXGL.*;

import java.util.ArrayList;



import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.entity.component.Component;

import controller.TLMSType;



public class DamagingComponent extends Component {
	
	int damageOfComponent = 0;
	
	public DamagingComponent(int damageOfComponent) {
		this.damageOfComponent = damageOfComponent;
	}
	
	public int getDamage(){
		return this.damageOfComponent;
	}

}
