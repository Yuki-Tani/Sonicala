package sonicala.model.song;

import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;

import sonicala.model.parts.SentenceSpace;

public class Lyrics {
	private Path lyricsFile;
	private LyricsFileReader reader;
	
	public Lyrics(URL fileLocation) {
		try {
			lyricsFile = Paths.get(fileLocation.toURI());
			reader = new LyricsFileReader(lyricsFile);
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
	}
	
	public SentenceSpace getSentenceSpace() {
		return reader.read();
	}
}
