package factories;

import static com.almasb.fxgl.dsl.FXGL.entityBuilder;
import static model.TLMSType.DAMAGE_OBJECT;

import com.almasb.fxgl.animation.Interpolators;
import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.dsl.components.ExpireCleanComponent;
import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.entity.EntityFactory;
import com.almasb.fxgl.entity.SpawnData;
import com.almasb.fxgl.entity.Spawns;
import com.almasb.fxgl.entity.components.CollidableComponent;
import com.almasb.fxgl.physics.BoundingShape;
import com.almasb.fxgl.physics.HitBox;
import com.almasb.fxgl.physics.PhysicsComponent;

import components.DamagingComponent;
import javafx.geometry.Point2D;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

//implementare l'entita' cassa, ostacolo, muro
public class WorldFactory implements EntityFactory {
	
	@Spawns("platform")
	public Entity newPlatform(SpawnData data) {
		return FXGL.entityBuilder(data)
		   // .type(PLATFORM)
            .bbox(new HitBox(BoundingShape.box(data.<Integer>get("width"), data.<Integer>get("height"))))
            .with(new PhysicsComponent())
            .build();
	}
	
	@Spawns("wall")
	public Entity newWall(SpawnData data) {
		return FXGL.entityBuilder(data)
		   // .type(PLATFORM)
            .bbox(new HitBox(BoundingShape.box(data.<Integer>get("width"), data.<Integer>get("height"))))
            .with(new PhysicsComponent())
            .build();
	}


	//da rivedere, l'ha fatto vale. Danno in damagingcomponent()
 	@Spawns("damageObject")
    public Entity newDamageObject(SpawnData data) {
	 return entityBuilder(data)
                .type(DAMAGE_OBJECT)
                .bbox(new HitBox(BoundingShape.box(data.<Integer>get("width"), data.<Integer>get("height"))))
                .with(new DamagingComponent(0))
                .with(new CollidableComponent(true))
                .with(new PhysicsComponent())
                .build();
    }
 	
 	 @Spawns("text")
     public Entity centralText(SpawnData data) {
         String text = data.get("text");
         var e = entityBuilder(data)
                 .view(FXGL.getUIFactoryService().newText(text, 70))
                 .with(new ExpireCleanComponent(Duration.seconds(2.0)))
                 .build();

         FXGL.animationBuilder()
                 .duration(Duration.seconds(2.0))
                 .interpolator(Interpolators.EXPONENTIAL.EASE_OUT())
                 .translate(e)
                 .from(new Point2D(data.getX(), data.getY()))
                 .to(new Point2D(data.getX(), data.getY() - 30))
                 .buildAndPlay();

         return e;
     }
 	 
 	 @Spawns("zombiePoints")
     public Entity newScoreText(SpawnData data) {
         String text = data.get("zombiePoints");

         var e = entityBuilder(data)
                 .view(FXGL.getUIFactoryService().newText(text, 30))
                 .with(new ExpireCleanComponent(Duration.seconds(1.0)).animateOpacity())
                 .build();

         FXGL.animationBuilder()
                 .duration(Duration.seconds(2.0))
                 .interpolator(Interpolators.EXPONENTIAL.EASE_OUT())
                 .translate(e)
                 .from(new Point2D(data.getX(), data.getY()))
                 .to(new Point2D(data.getX(), data.getY() - 30))
                 .buildAndPlay();

         return e;
     }
}
