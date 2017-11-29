package sonicala.model.test;

import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Paths;

import sonicala.app.Constants;
import sonicala.model.song.Song;
import sonicala.model.song.SongInformation;

public class FileLoadTest {
	public static void main(String[] args) {
		FileLoadTest test = new FileLoadTest();
		URL testURL = test.getClass().getClassLoader().getResource("songs/information/test240.inf");
		System.out.println("input url: "+testURL.toString());
		
		Song song = null;
		try {
			song = new Song(Paths.get(testURL.toURI()));
		} catch (URISyntaxException e) {
			System.out.println("MISS FILE LOAD[FileLoadTest]");
			e.printStackTrace();
		}
		
		System.out.println("info file url: "+song.getInformation().getInformationFileURL());
		System.out.println("music file url: "+song.getInformation().getMusicFileURL());
		System.out.println("score file url: "+song.getInformation().getScoreFileURL());
		System.out.println("lyrics file url: "+song.getInformation().getLyricsFileURL());
		System.out.println();
		
		SongInformation info = song.getInformation();
		System.out.println("[song name]"+info.get(Constants.INFORMATION_FILE_INDEX_SONG_NAME));
		System.out.println("[sub name]"+info.get(Constants.INFORMATION_FILE_INDEX_SUB_NAME));
		System.out.println("[vocal]"+info.get(Constants.INFORMATION_FILE_INDEX_VOCAL));
		System.out.println("[description]"+info.get(Constants.INFORMATION_FILE_INDEX_DESCRIPTION));
	}
}
