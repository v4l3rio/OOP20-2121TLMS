package view;

import com.almasb.fxgl.ui.UIController;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.StringProperty;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;

public class DisplayController implements UIController {

	@FXML
	private ProgressBar lifeBar;

	@FXML
	private Label points;
	
	public DisplayController() {
		lifeBar = new ProgressBar();
		points = new Label();
	}

	
	public DoubleProperty getLifeProgressProperty() {
		return this.lifeBar.progressProperty();
	}

	public StringProperty getPointsProperty() {
		return this.points.textProperty();
	}

	@Override
	public void init() {
	}
}
