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
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.RadialGradient;
import javafx.scene.paint.Stop;
import javafx.stage.Stage;
import sonicala.view.element.CircleOnPole;
import sonicala.view.element.PolarCanvas;
import sonicala.view.element.RectangeOnPole;
import sonicala.view.element.ShineLine;
import sonicala.view.element.ShineRingOnPole;
import sonicala.view.element.TrailingCircleOnPole;

public class PaintElementTest extends Application{
	
	private Stage stage;
	private StackPane pane;

	@Override
	public void start(Stage stage) throws Exception {
		this.stage = stage;
		pane = new StackPane();
		pane.setStyle("-fx-background-color: black;");
		pane.getChildren().add(new Canvas());
		int thread = 1;
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
	private CircleOnPole circle = new CircleOnPole();
	private ShineRingOnPole ring = new ShineRingOnPole();
	private RectangeOnPole rect = new RectangeOnPole();
	private ShineLine line = new ShineLine();
	private TrailingCircleOnPole trail = new TrailingCircleOnPole();
	
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
		
		ring.paint(0,  0,  200, 10, 3, canvas);
		rect.paint(50, 5, 200, 20, 270, canvas);
		line.paint(50, 5, 200, 20, 0.8, 90, canvas);
		
		Stop centerStop = new Stop(0.1,new Color(0,0,1,1));
		Stop endStop = new Stop(0.9,new Color(0,0,1,0));
		/*RadialGradient gradient = new RadialGradient(
				0, 0,
				0, 0, 200, 
				false, CycleMethod.NO_CYCLE, 
				centerStop, endStop);
		circle.setPaint(gradient);
		circle.paint(0, 0, 200, canvas);
		*/
		
		 RadialGradient gradient = new RadialGradient(
				 0 , 0 , 500 , 500 , 500 , false , CycleMethod.NO_CYCLE , centerStop, endStop);
		 //canvas.getGraphicsContext2D().setFill(lg);
		 //canvas.getGraphicsContext2D().fillOval(300, 300, 100, 100);
		 //circle.setPaint(gradient);
		 //circle.setPaint(Color.WHITE);
		 //circle.paint(50, 90, 50, canvas);
		 
		 trail.paint(
				 200, 90, 20, 5, 2, 
				 100, -90, 10, 3, 1,
				 50, canvas);
		 
		Platform.runLater(()->pane.getChildren().set(0,canvas));
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
