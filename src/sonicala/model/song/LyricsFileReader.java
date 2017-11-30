package sonicala.model.song;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import sonicala.app.Constants;
import sonicala.app.ErrorConstants;
import sonicala.model.data.MusicTime;
import sonicala.model.parts.Sentence;
import sonicala.model.parts.SentenceSpace;

public class LyricsFileReader {
	
	private Path lyricsFile;
	
	public LyricsFileReader(Path lyricsFile) {
		this.lyricsFile = lyricsFile;
	}
	
	public SentenceSpace read() {
		SentenceSpace dataLine = new SentenceSpace();
		try {
			BufferedReader reader = Files.newBufferedReader(lyricsFile);
			int tempo = 0;
			int beat = 0;
			int delay = 0;
			// stock
			double prevStartTime = -1;
			String prevLyric = null;
			String line = reader.readLine();
			while(line!=null) {
				try {
					String[] elements = line.split("\\s+");
					String head = elements[0].trim().toLowerCase();
//					System.out.println(elements[0]+" "+elements[1]);
//					System.out.println(head);
					// settings
					if(head.equals(Constants.LYRICS_FILE_TEMPO_MARKER)) {
						tempo = Integer.parseInt(elements[1]);
					}else if(head.equals(Constants.LYRICS_FILE_BEAT_MARKER)) {
						beat = Integer.parseInt(elements[1]);
					}else if(head.equals(Constants.LYRICS_FILE_DELAY_MARKER)) {
						delay = Integer.parseInt(elements[1]);
					}else {
					// lyrics
						String[] timing = head.split(Constants.LYRICS_FILE_TIMING_SEPARATOR);
						if(timing.length == 0) continue;
						double t;
						if(timing.length == 1) { // direct timing
							t = Double.parseDouble(timing[0]);
						}else {
							if(tempo == 0 || beat == 0) 
								throw new LyricsException(ErrorConstants.LYR_01);
							double noteNum = 0;
							double coefficient = beat;
							for(int i=0;i<timing.length;i++) {
								noteNum += Integer.parseInt(timing[i])*coefficient;
								coefficient = coefficient / beat;
							}
							t = noteNum * 60.0 / tempo;
						}
						
						t = t - Constants.LYRICS_FUTURE_IN_TIME + delay;
						// add prev lyrics
						if(prevLyric != null) {
							dataLine.add(new Sentence(
									new MusicTime(prevStartTime),
									new MusicTime(t),
									prevLyric));
							System.out.println((int)prevStartTime+"~"+(int)t+" "+prevLyric);
						}
						prevLyric = "";
						for(int i=1;i<elements.length;i++) {
							prevLyric = prevLyric + ((i==1)? "":" ") + elements[i];
						}
						prevStartTime = t;
					}
				} catch (LyricsException e) {
					System.out.println(ErrorConstants.LYR_SYNTAX + "[LyricsFileReader]");
					System.out.println("around :: "+line);
					System.out.println(e.error);
				} catch (Exception e) {
					System.out.println(ErrorConstants.LYR_SYNTAX + "[LyricsFileReader]");
					System.out.println("around :: "+line);
					System.out.println(ErrorConstants.LYR_02);
				}
				line = reader.readLine();
			}
		} catch (IOException e) {
			System.out.println("lyrics file reader error[LyricsFIleReader]");
			e.printStackTrace();
		} 
		
		return dataLine;
	}
	
	@SuppressWarnings("serial")
	private class LyricsException extends Exception{
		public String error;
		public LyricsException(String errorCode) {
			error = errorCode;
		}
	}
}
