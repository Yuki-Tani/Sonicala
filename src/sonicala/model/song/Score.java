package sonicala.model.song;

import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;

import sonicala.model.parts.BeatRingSpace;
import sonicala.model.parts.NoteSpace;

public class Score {
	private Path scoreFile;
	private ScoreFileReader reader;
	
	public Score(URL fileLocation) {
		try {
			scoreFile = Paths.get(fileLocation.toURI());
			reader = new ScoreFileReader(scoreFile);
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
	}
	
	public NoteSpace getNoteLine() {
		return reader.readNotes();
	}
	
	public BeatRingSpace getBeatRingLine() {
		return reader.readBeatRings();
	}
}
