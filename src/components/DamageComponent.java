package components;

import static com.almasb.fxgl.dsl.FXGL.*;

import java.util.ArrayList;



import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.entity.component.Component;

import controller.TLMSType;

//questo component è mal funzionante e da eliminare in seguito, la gestione delle collisioni va fatta diversa

public class DamageComponent extends Component {
	ArrayList<Entity> zombies;

	@Override
	public void onUpdate(double tpf) {
		zombies = getGameWorld().getEntities();
		for(int i = 0; i < zombies.size(); i++) {
			if(zombies.get(i).isType(TLMSType.ZOMBIE)) {
				if (entity.isColliding(zombies.get(i))) {
					zombies.get(i).setVisible(false);
					zombies.get(i).removeFromWorld();
					zombies.remove(i);
				}
			}
		}

	}

}
