package sonicala.app;
	
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.stage.Screen;
import javafx.stage.Stage;
import sonicala.controller.StartPageController;


public class Sonicala extends Application {
	
	private static Stage appStage;
	
	@Override
	public void start(Stage stage) {
		appStage = stage;
		stage.showingProperty().addListener(
				(obsv,oldValue,newValue) -> {if(oldValue && !newValue) close();});
		StartPageController.showPage();
		stage.show();
	}
	
	public static void close() {
		Platform.exit();
		System.exit(0);
	}
	
	public static void setPage(Scene scene) {
		appStage.setScene(scene);
	}
	
	public static void setFullScreel(boolean value) {
		appStage.setFullScreen(value);
	}
	
	public static double getHeight() {
		return appStage.getHeight();
	}
	
	public static double getWidth() {
		return appStage.getWidth();
	}

	public static void main(String[] args) {
		launch(args);
	}
}
