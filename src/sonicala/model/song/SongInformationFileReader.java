package sonicala.model.song;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import sonicala.app.Constants;
import sonicala.app.ErrorConstants;

public class SongInformationFileReader {
	private Path infoFile;
	
	public SongInformationFileReader(Path informaitonFile) {
		this.infoFile = informaitonFile;
	}
	
	public SongInformationDetails read() {
		SongInformationDetails details = new SongInformationDetails();
		try {
			BufferedReader reader = Files.newBufferedReader(infoFile);
			String line = reader.readLine();
			while(line!=null) {
				try {
					String[] elements = line.split(Constants.INFORMATION_FILE_SEPARATOR);
					if(elements.length > 2) throw new SongInformationException(ErrorConstants.INF_01);
					if(elements.length < 2) throw new SongInformationException(ErrorConstants.INF_02);
					details.put(elements[0], elements[1]);
				} catch (SongInformationException e) {
					System.out.println(ErrorConstants.INF_SYNTAX + "[SongInformationFileReader]");
					System.out.println("around :: "+line);
					System.out.println(e.error);
				}
				line = reader.readLine();
			}
		} catch (IOException e) {
			System.out.println("information file reader error[SongInformationFIleReader]");
			e.printStackTrace();
		} 
		return details;
	}
	
	@SuppressWarnings("serial")
	private class SongInformationException extends Exception{
		public String error;
		public SongInformationException(String errorCode) {
			error = errorCode;
		}
	}
}
