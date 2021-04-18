package factories;

import static com.almasb.fxgl.dsl.FXGL.entityBuilder;
import static model.TLMSType.PLATFORM;
import static model.TLMSType.WALL;

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


import javafx.geometry.Point2D;
import javafx.util.Duration;

public class WorldFactory implements EntityFactory {
	
	private final double durationPointsText = 1.0;
	private final double durationReloadText = 2.0;
	private final int sizePointsText = 30;
	private final int sizeReloadText = 70;
	private final int widthShiftText = 30;
	
	@Spawns("platform")
	public Entity newPlatform(SpawnData data) {
		return FXGL.entityBuilder(data)
		    .type(PLATFORM)
            .bbox(new HitBox(BoundingShape.box(data.<Integer>get("width"), data.<Integer>get("height"))))
            .with(new PhysicsComponent())
            .build();
	}
	
	@Spawns("wall")
	public Entity newWall(SpawnData data) {
		return FXGL.entityBuilder(data)
		    .type(WALL)
            .bbox(new HitBox(BoundingShape.box(data.<Integer>get("width"), data.<Integer>get("height"))))
            .with(new PhysicsComponent())
            .with(new CollidableComponent(true))
            .build();
	}
 	
 	 @Spawns("text")
     public Entity centralText(SpawnData data) {
         String text = data.get("text");
         
         var e = entityBuilder(data)
                 .view(FXGL.getUIFactoryService().newText(text, sizeReloadText))
                 .with(new ExpireCleanComponent(Duration.seconds(durationReloadText)))
                 .build();

         FXGL.animationBuilder()
                 .interpolator(Interpolators.EXPONENTIAL.EASE_OUT())
                 .translate(e)
                 .from(new Point2D(data.getX(), data.getY()))
                 .to(new Point2D(data.getX(), data.getY() - widthShiftText))
                 .buildAndPlay();

         return e;
     }
 	 
 	 @Spawns("zombiePoints")
     public Entity newScoreText(SpawnData data) {
         String text = data.get("zombiePoints");

         var e = entityBuilder(data)
                 .view(FXGL.getUIFactoryService().newText(text, sizePointsText))
                 .with(new ExpireCleanComponent(Duration.seconds(durationPointsText)).animateOpacity())
                 .build();

         FXGL.animationBuilder()
                 .interpolator(Interpolators.EXPONENTIAL.EASE_OUT())
                 .translate(e)
                 .from(new Point2D(data.getX(), data.getY()))
                 .to(new Point2D(data.getX(), data.getY() - widthShiftText))
                 .buildAndPlay();
         return e;
     }
}