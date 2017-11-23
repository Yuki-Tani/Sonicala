package sonicala.view.test;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import sonicala.view.element.PolarCanvas;
import sonicala.view.element.RadiatedCircle;

public class PaintElementTest extends Application{
	
	private Stage stage;
	private StackPane pane;

	@Override
	public void start(Stage stage) throws Exception {
		this.stage = stage;
		pane = new StackPane();
		pane.setStyle("-fx-background-color: black;");
		pane.getChildren().add(new Canvas());
		int thread = 2;
		int frequency = 100;
		ScheduledExecutorService service = Executors.newScheduledThreadPool(thread + 10);
		for(int i=0; i<thread; i++) {
			service.scheduleAtFixedRate(this::paint, frequency*i+1000, frequency*thread, TimeUnit.MILLISECONDS);
		}
		Scene scene = new Scene(pane,955,600) ;
		stage.setScene(scene);
		stage.show();
	}
	
	private Double x = new Double(0);
	private RadiatedCircle circle = new RadiatedCircle();
	
	public void paint() {
		System.out.println("start caluc");
		synchronized(x) {
			x += 5;
		}
		PolarCanvas canvas = new PolarCanvas(stage.getWidth(),stage.getHeight());
		
		for(double theta=0; theta < 360; theta += 9) {
			for(int i=1;i<=100;i++) {
				Color col = new Color(i/100.0,0,1.0-i/100.0,0.5);
				circle.setPaint(col);
				circle.paint(Math.max(i*10+x-1000,0), theta+i*5, 5, canvas);
			}
		}
		
		Platform.runLater(()->pane.getChildren().set(0,canvas));
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
