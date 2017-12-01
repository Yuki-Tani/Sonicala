package sonicala.view;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import sonicala.app.Constants;
import sonicala.controller.SelectPageController;
import sonicala.model.song.Song;
import sonicala.model.song.SongInformationDetails;

public class SongPane extends TitledPane{
	
	private double 
		TITLE_SIZE = 50,
		SUB_TITLE_SIZE = 20,
		VOCAL_SIZE= 20,
		TIME_LENGTH_SIZE = 15,
		DESCRIPTION_SIZE = 15,
		EMPTY_SIZE = 10;
	
	private Song song;
	private SongInformationDetails details;
	
	public SongPane(Song song, SelectPageController controller) {
		
		this.song = song;
		details = song.getInformation().getDeatails();
		VBox box = new VBox();
		
		String songName = details.get(Constants.INFORMATION_FILE_INDEX_SONG_NAME);
		Label title = new Label();
		title.setText(songName);
		title.setFont(Font.font(TITLE_SIZE));
		
		Label sub = new Label();
		sub.setText(details.get(Constants.INFORMATION_FILE_INDEX_SUB_NAME));
		sub.setFont(Font.font(SUB_TITLE_SIZE));
		
		Label vocal = new Label();
		vocal.setText(details.get(Constants.INFORMATION_FILE_INDEX_VOCAL));
		vocal.setFont(Font.font(VOCAL_SIZE));
		
		Label timeLength = new Label();
		timeLength.setText(details.get(Constants.INFORMATION_FILE_TIME_INDEX_LENGTH));
		timeLength.setFont(Font.font(TIME_LENGTH_SIZE));
		
		Label description = new Label();
		description.setText(details.get(Constants.INFORMATION_FILE_INDEX_DESCRIPTION));
		description.setFont(Font.font(DESCRIPTION_SIZE));
		description.setWrapText(true);
		
		Label empty1 = new Label();
		empty1.setText("     ");
		description.setFont(Font.font(EMPTY_SIZE));
		
		Label empty2 = new Label();
		empty1.setText("     ");
		description.setFont(Font.font(EMPTY_SIZE));
		
		Label empty3 = new Label();
		empty1.setText("     ");
		description.setFont(Font.font(EMPTY_SIZE));
		
		Button botton = new Button("START");
		botton.setOnAction((e)->controller.selectAction(song));
		
		/////////////////////////////////////////////////////////////
		
		box.getChildren().add(botton);
		box.getChildren().add(title);
		box.getChildren().add(sub);
		box.getChildren().add(empty1);
		box.getChildren().add(vocal);
		box.getChildren().add(empty2);
		box.getChildren().add(timeLength);
		box.getChildren().add(empty3);
		box.getChildren().add(description);	
		
		System.out.println(title.getText());
		System.out.println(sub.getText());
		System.out.println(vocal.getText());
		System.out.println(description.getText());
		System.out.println(title.getText());
		System.out.println(box.getChildren().size());
		
		setText(songName);
		setContent(box);
	}
}
