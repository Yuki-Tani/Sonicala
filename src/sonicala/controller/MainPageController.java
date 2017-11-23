package sonicala.controller;

import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.input.KeyEvent;
import sonicala.app.Constants;
import sonicala.app.MainPageManager;
import sonicala.app.Sonicala;
import sonicala.model.Song;
import sonicala.view.MainPage;

public class MainPageController {
	
	private MainPageManager manager;
	private MainPage page;
	
	public static void showPage(Song song) {
		MainPageController newController = new MainPageController();
		newController.page = new MainPage(newController);
		Scene scene = new Scene(newController.page,Constants.WINDOW_WIDTH,Constants.WINDOW_HEIGHT);
		Sonicala.setPage(scene);
		newController.prepare(song);
	}
	
	public void prepare(Song song) {
		manager = new MainPageManager(song,this);
	}
	
	public void startAction() {
		if(manager==null) return;
		manager.start();
	}
	
	public void poseAction() {
		if(manager==null) return;
		manager.pose();
	}
	
	public void endAction() {
		if(manager==null) return;
		//各スレッドの停止
		//採点を依頼
		//次の画面へ遷移
	}
	
	public void setBackCanvas(Canvas canvas) {
		Platform.runLater(()->page.setBackCanvas(canvas));
	}
	
	public void setForwardCanvas(Canvas canvas) {
		Platform.runLater(()->page.setForwardCanvas(canvas));
	}
	
	public void keyPressAction(KeyEvent event) {
		
	}
}
