package sonicala.controller;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ResourceBundle;

import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.layout.Pane;
import sonicala.app.Constants;
import sonicala.app.Sonicala;
import sonicala.model.song.Song;

public class SelectPageController implements Initializable{
	public static final String pageURL = "../view/SelectPage.fxml";
	
	@FXML ListView<String> songList;
	
	public static void showPage() {
		SelectPageController controller = new SelectPageController();
		try {
			Pane pane = FXMLLoader.load(controller.getClass().getResource(pageURL));
			Scene scene = new Scene(pane,Constants.WINDOW_WIDTH,Constants.WINDOW_HEIGHT);
			Sonicala.setPage(scene);
		} catch (IOException e) {
			System.out.println("!!! This page is NOT found [SelectPageController]");
			e.printStackTrace();
		}
	}
	
	protected void selectAction(ObservableValue<? extends String> value, String prev,String now) {
		// song オブジェクト受け渡し
		/*test*/ 
		URL url = getClass().getClassLoader().getResource("songs/information/名前のない怪物.inf");
		Path file;
		try {
			file = Paths.get(url.toURI());
			Song song = new Song(file);
			MainPageController.showPage(song);
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		songList.getItems().add("test1");
		songList.getItems().add("test2");
		songList.getItems().add("test3");
		songList.getSelectionModel().selectedItemProperty().addListener(this::selectAction);
	}
}
