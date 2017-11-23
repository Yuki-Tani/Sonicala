package sonicala.controller;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import sonicala.app.Constants;
import sonicala.app.Sonicala;

public class StartPageController {
	
	public static final String pageURL = "../view/StartPage.fxml";
	
	public static void showPage() {
		StartPageController controller = new StartPageController();
		try {
			Pane pane = FXMLLoader.load(controller.getClass().getResource(pageURL));
			Scene scene = new Scene(pane,Constants.WINDOW_WIDTH,Constants.WINDOW_HEIGHT);
			Sonicala.setPage(scene);
			
		} catch (IOException e) {
			System.out.println("!!! This page is NOT found [StartPageController]");
			e.printStackTrace();
		}
	}
	
	@FXML
	protected void startAction(ActionEvent event) {
		SelectPageController.showPage();
	}
}
