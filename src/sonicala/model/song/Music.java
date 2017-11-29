package sonicala.model.song;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.UnsupportedAudioFileException;

public class Music {
	private Path musicFile;
	private AudioFormat format;
	
	public Music(URL fileLocation) {
		try {
			musicFile = Paths.get(fileLocation.toURI());
			format = AudioSystem.getAudioFileFormat(fileLocation).getFormat();
		} catch (UnsupportedAudioFileException e) {
			System.out.println("!! this audio file is unsupported[Music]");
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println("!! this audio file is NOT found[Music]");
			e.printStackTrace();
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
	}
	
	public File getFile() {
		return musicFile.toFile();
	}
		
	public int getSampleRate() {
		return (int)format.getSampleRate();
	}
	
}
