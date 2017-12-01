package sonicala.view;

import java.util.ArrayList;
import java.util.stream.Stream;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Mixer;

import javafx.scene.Node;
import javafx.scene.control.Accordion;
import javafx.scene.layout.BorderPane;
import sonicala.controller.SelectPageController;
import sonicala.model.song.Song;

public class SelectPage extends BorderPane{
	
	ArrayList<Song> songs;
	SelectPageController controller;
	
	public SelectPage(SelectPageController controller, ArrayList<Song> songs) {
		setStyle("-fx-background-color: black;");
		this.songs = songs;
		this.controller = controller;
		setCenter(selectAccordion());
		setBottom(selectMixer());
	}
	
	private Accordion selectAccordion() {
		Accordion accordion = new Accordion();
		SongPane[] panes = 
				songs.stream().map(song -> 
				new SongPane(song,controller)).toArray(SongPane[]::new);
		accordion.getPanes().addAll(panes);
		return accordion;
	}
	
	private Node selectMixer() {
		Mixer.Info[] info = AudioSystem.getMixerInfo();
		for(int i=0; i<info.length; i++) {
			System.out.println("Mixer No."+i
					+"\n   Name: "+info[i].getName()
					+"\n   Vendor: "+info[i].getVendor()
					+"\n   Version: "+info[i].getVersion()
					+"\n   Info: "+info[i].getDescription());
		}
		return null;
	}
}
