package sonicala.controller;

import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import sonicala.app.Constants;
import sonicala.app.Sonicala;
import sonicala.view.StartPage;

public class StartPageController {
	
//	public static final String pageURL = "../view/StartPage.fxml";
	private StartPage page;
	
	public static void showPage() {

		StartPageController controller = new StartPageController();
		controller.page = new StartPage(controller);
		Scene scene = new Scene(controller.page,Constants.WINDOW_WIDTH,Constants.WINDOW_HEIGHT);
		scene.setOnKeyPressed(controller::keyPressedAction);
		scene.setOnMouseClicked(controller::mouseClickedAction);
		Sonicala.setPage(scene);
		
		/*
		try {
			Pane pane = FXMLLoader.load(controller.getClass().getResource(pageURL));
			Scene scene = new Scene(pane,Constants.WINDOW_WIDTH,Constants.WINDOW_HEIGHT);
			Sonicala.setPage(scene);
			
		} catch (IOException e) {
			System.out.println("!!! This page is NOT found [StartPageController]");
			e.printStackTrace();
		}
		*/
	}
	
	public void keyPressedAction(KeyEvent e) {
		System.out.println("key pressed in start page");
		startAction();
	}
	
	public void mouseClickedAction(MouseEvent e) {
		System.out.println("mouse clicked in start page");
		startAction();
	}
	
//	@FXML
	protected void startAction() {
		SelectPageController.showPage();
	}
}
