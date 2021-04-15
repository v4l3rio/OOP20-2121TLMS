package controller;

import com.almasb.fxgl.ui.UIController;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.StringProperty;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;

public class VisorController implements UIController {
	
	private String fxmlVisorView = "visorView.fxml";

	@FXML
	private ProgressBar lifeBar;

	@FXML
	private Label points;
	
	public VisorController() {
		lifeBar = new ProgressBar();
		points = new Label();
	}
	
	public String getFxmlVisor() {
		return this.fxmlVisorView;
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
