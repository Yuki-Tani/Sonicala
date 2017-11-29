package sonicala.model.song;

import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Lyrics {
	private Path lyricsFile;
	
	public Lyrics(URL fileLocation) {
		try {
			lyricsFile = Paths.get(fileLocation.toURI());
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
	}
}
