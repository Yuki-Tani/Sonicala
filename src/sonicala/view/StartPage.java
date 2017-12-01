package sonicala.view;

import java.io.InputStream;

import javafx.animation.FadeTransition;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.util.Duration;
import sonicala.app.Sonicala;
import sonicala.controller.StartPageController;

public class StartPage extends StackPane{
	
	private static final double 
		START_LABEL_Y_RATE = 0.8,
		START_LABEL_FLASH_CYCLE = 3;
	
	
	public StartPage(StartPageController controller) {
		setStyle("-fx-background-color: black;");
		InputStream title = getClass().getClassLoader()
				.getResourceAsStream("system/img/sonicala_title.png");
		ImageView image = new ImageView(new Image(title));
		
		getChildren().add(image);
		getChildren().add(getComputedPane());
	}
	
	private Pane getComputedPane() {
		System.out.println(Sonicala.getHeight());
		Pane pane = new Pane();
		Text start = new Text(
				0,
				Sonicala.getHeight()*START_LABEL_Y_RATE,
				"START"
				);
		start.setFont(Font.font(20));
		start.setFill(Color.LIGHTSLATEGRAY);
		start.setWrappingWidth(Sonicala.getWidth());
		start.setTextAlignment(TextAlignment.CENTER);
		pane.getChildren().add(start);
		
		FadeTransition startColorTransition = new FadeTransition();
		startColorTransition.setNode(start);
		startColorTransition.setDuration(Duration.seconds(START_LABEL_FLASH_CYCLE/2.0));
		startColorTransition.setFromValue(0.2);
		startColorTransition.setToValue(1.0);
		startColorTransition.setCycleCount(FadeTransition.INDEFINITE);
		startColorTransition.setAutoReverse(true);
		startColorTransition.play();
		return pane;
	}
}
