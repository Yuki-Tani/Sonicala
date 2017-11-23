package sonicala.model;

import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Score {
	private Path scoreFile;
	
	public Score(URL fileLocation) {
		try {
			scoreFile = Paths.get(fileLocation.toURI());
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
	}
}
