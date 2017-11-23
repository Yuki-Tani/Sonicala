package sonicala.view;

import javafx.scene.canvas.Canvas;
import javafx.scene.layout.StackPane;
import sonicala.app.Constants;
import sonicala.controller.MainPageController;

public class MainPage extends StackPane{
	
	private final int BACK = 0, FORWARD = 1;
	
	public MainPage(MainPageController controller) {
		setStyle("-fx-background-color: black;");
		setOnKeyPressed(controller::keyPressAction);
		
		Canvas back = new Canvas(Constants.WINDOW_WIDTH,Constants.WINDOW_HEIGHT);
		Canvas forward = new Canvas(Constants.WINDOW_WIDTH,Constants.WINDOW_HEIGHT);
		getChildren().add(back);
		getChildren().add(forward);
	}
	
	public void setBackCanvas(Canvas canvas) {
		getChildren().set(BACK, canvas);
	}
	
	public void setForwardCanvas(Canvas canvas) {
		getChildren().set(FORWARD, canvas);
	}
}
