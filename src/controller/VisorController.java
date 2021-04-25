package controller;

import com.almasb.fxgl.ui.UIController;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.StringProperty;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;

/**
 * The VisorController implements the FXGL UIController abstract class.
 * The only abstract method init() is empty.
 * <p> lifeBar and points fields will be bound with lifeBar and points components in fxml file 
 * placed in assets/ui.
 */
public class VisorController implements UIController {
	
	private static final  String FXMLVISORVIEW = "visorView.fxml";

	/**This field has to be mutable.**/
	@FXML
	private ProgressBar lifeBar;

	/**This field has to be mutable.**/
	@FXML
	private Label points;
	
	/**
	 * Initialize a ProgressBar and a Label JavaFX components.
	 */
	public VisorController() {
		lifeBar = new ProgressBar();
		points = new Label();
	}
	
	/**
	 * @return
	 *     the path of the fxml file
	 */
	public String getFxmlVisor() {
		return FXMLVISORVIEW;
	}
	
	/**
	 * @return
	 *     the {@link DoubleProperty} progress of the lifeBar
	 */
	public DoubleProperty getLifeProgressProperty() {
		return this.lifeBar.progressProperty();
	}

	/**
	 * @return
	 * 	the {@link StringProperty} text of the points label
	 */
	public StringProperty getPointsProperty() {
		return this.points.textProperty();
	}

	@Override
	public void init() {
	}
}
