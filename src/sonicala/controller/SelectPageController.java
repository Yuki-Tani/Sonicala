package sonicala.controller;

import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

import javafx.scene.Scene;
import javafx.scene.control.ListView;
import sonicala.app.Constants;
import sonicala.app.Sonicala;
import sonicala.model.song.Song;
import sonicala.view.SelectPage;

public class SelectPageController /*implements Initializable*/{
//	public static final String pageURL = "../view/SelectPage.fxml";
	
	//@FXML 
	ListView<String> songList;
	SelectPage page;
	
	public static void showPage() {
		SelectPageController controller = new SelectPageController();
		
		URL url = controller.getClass().getClassLoader().getResource("songs/information/");
		ArrayList<Song> songs = new ArrayList<Song>();
		try {
			Path file = Paths.get(url.toURI());
			Files.list(file).forEach((path) -> {
				if(path.toString().endsWith(Constants.RESOURCE_INFORMATION_FILE_EXTENSION))
					songs.add(new Song(path));
			}
			);
			controller.page = new SelectPage(controller,songs);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		Scene scene = new Scene(controller.page,Constants.WINDOW_WIDTH,Constants.WINDOW_HEIGHT);
		Sonicala.setPage(scene);
		/*
		try {
			Pane pane = FXMLLoader.load(controller.getClass().getResource(pageURL));
			Scene scene = new Scene(pane,Constants.WINDOW_WIDTH,Constants.WINDOW_HEIGHT);
			Sonicala.setPage(scene);
		} catch (IOException e) {
			System.out.println("!!! This page is NOT found [SelectPageController]");
			e.printStackTrace();
		}
		*/
	}
	
	public void selectAction(Song song) {
		// song オブジェクト受け渡し
		/*test 
		URL url = getClass().getClassLoader().getResource("songs/information/名前のない怪物.inf");
		Path file;
		try {
			file = Paths.get(url.toURI());
			Song song = new Song(file);
			MainPageController.showPage(song);
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
		*/
		MainPageController.showPage(song);
	}
/*
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		songList.getItems().add("test1");
		songList.getItems().add("test2");
		songList.getItems().add("test3");
		songList.getSelectionModel().selectedItemProperty().addListener(this::selectAction);
	}
*/
}
