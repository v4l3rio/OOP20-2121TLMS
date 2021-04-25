package factories;

import static com.almasb.fxgl.dsl.FXGL.entityBuilder;
import static com.almasb.fxgl.dsl.FXGL.animationBuilder;
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

/**
 * 
 * Factory for static (not dynamic) entities.
 * This class must implements the FXGL EntityFactory
 * to provide "Spawns" method to the FGXL GameApplication
 *
 */
public class WorldFactory implements EntityFactory {
	

	private static final double DURATION_POINT_TEXT = 1.0;
	private static final double DURATION_RELOAD_TEXT = 2.0; //3.0
	private static final int SIZE_POINT_TEXT = 30;
	private static final int SIZE_RELOAD_TEXT = 70; //50
	private static final int WIDTH_SHIFT_TEXT = 30;

	
	/**
	 * @param data
	 * @return
	 *     a platform entity
	 */
	@Spawns("platform")
	public Entity newPlatform(final SpawnData data) {
		return entityBuilder(data)
		    .type(PLATFORM)
            .bbox(new HitBox(BoundingShape.box(data.<Integer>get("width"), data.<Integer>get("height"))))
            .with(new PhysicsComponent())
            .build();
	}
	
	/**
	 * @param data
	 * @return
	 *     a wall entity
	 */
	@Spawns("wall")
	public Entity newWall(final SpawnData data) {
		return entityBuilder(data)
		    .type(WALL)
            .bbox(new HitBox(BoundingShape.box(data.<Integer>get("width"), data.<Integer>get("height"))))
            .with(new PhysicsComponent())
            .with(new CollidableComponent(true))
            .build();
	}

	/**
	 * @param data
	 * @return
	 *     a generic text entity on north-center of the window
	 */
 	 @Spawns("text")
     public Entity centralText(final SpawnData data) {
 		final String text = data.get("text");

 		final var e = entityBuilder(data)
                 .view(FXGL.getUIFactoryService().newText(text, SIZE_RELOAD_TEXT))
                 .with(new ExpireCleanComponent(Duration.seconds(DURATION_RELOAD_TEXT)))
                 .build();

        animationBuilder()
                 .interpolator(Interpolators.EXPONENTIAL.EASE_OUT())
                 .translate(e)
                 .from(new Point2D(data.getX(), data.getY()))
                 .to(new Point2D(data.getX(), data.getY() - WIDTH_SHIFT_TEXT))
                 .buildAndPlay();

         return e;
     }

 	 /** 
 	  * @param data
 	  * @return
 	  *     the points text of the kill of a zombie on the zombie's head
 	  */
 	 @Spawns("zombiePoints")
     public Entity newScoreText(final SpawnData data) {
 		final String text = data.get("zombiePoints");

 		final var e = entityBuilder(data)
                 .view(FXGL.getUIFactoryService().newText(text, SIZE_POINT_TEXT))
                 .with(new ExpireCleanComponent(Duration.seconds(DURATION_POINT_TEXT)).animateOpacity())
                 .build();

        animationBuilder()
                 .interpolator(Interpolators.EXPONENTIAL.EASE_OUT())
                 .translate(e)
                 .from(new Point2D(data.getX(), data.getY()))
                 .to(new Point2D(data.getX(), data.getY() - WIDTH_SHIFT_TEXT))
                 .buildAndPlay();
         return e;
     }
}
