package sonicala.model.song;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import sonicala.app.Constants;
import sonicala.app.ErrorConstants;
import sonicala.model.data.MusicTime;
import sonicala.model.data.Pitch;
import sonicala.model.data.PitchType;
import sonicala.model.parts.BeatRing;
import sonicala.model.parts.BeatRingSpace;
import sonicala.model.parts.Note;
import sonicala.model.parts.NoteSpace;

public class ScoreFileReader {
	
	private static final int POSE = -1;
	private Path scoreFile;
	
	public ScoreFileReader(Path scoreFile) {
		this.scoreFile = scoreFile;
	}
	
	public NoteSpace readNotes() {
		NoteSpace dataLine = new NoteSpace();
		try {
			BufferedReader reader = Files.newBufferedReader(scoreFile);
			int tempo = 0;
			int beat = 0;
			double delay = 0;
			
			//stock
			double prevStart = -1;
			double prevNoteNum = -1;
			
			String line = reader.readLine();
			while(line!=null) {
				if(line.equals("")) {
					line = reader.readLine();
					continue;
				}
				try {
					String[] elements = line.split("\\s+");
					String head = elements[0].trim().toLowerCase();
//					System.out.println(elements[0]+" "+elements[1]);
//					System.out.println(head);
					// settings
					if(head.equals(Constants.SCORE_FILE_TEMPO_MARKER)) {
						tempo = Integer.parseInt(elements[1]);
					}else if(head.equals(Constants.SCORE_FILE_BEAT_MARKER)) {
						beat = Integer.parseInt(elements[1]);
					}else if(head.equals(Constants.SCORE_FILE_DELAY_MARKER)) {
						delay = Double.parseDouble(elements[1]);
					}else if(head.equals(Constants.SCORE_FILE_END_MARKER)){
					}else {
					// notes;
						double newStart = toSeconds(head,tempo,beat,delay);
						if(prevNoteNum != POSE) {
							dataLine.add(new Note(
									new MusicTime(prevStart),
									new MusicTime(newStart),
									new Pitch(prevNoteNum,PitchType.NOTE_NUMBER)) {
							});
							System.out.println((int)prevStart+"~"+(int)newStart+" "+prevNoteNum);
						}
						if(elements.length == 1) { //pose
							prevNoteNum = POSE;
						}else if(elements.length == 2) {
							prevNoteNum = toNoteNum(elements[1].trim().toLowerCase());
						}
						
						prevStart = newStart;
					}
				} catch (ScoreException e) {
					System.out.println(ErrorConstants.SCO_SYNTAX + "[ScoreFileReader]");
					System.out.println("around :: "+line);
					System.out.println(e.error);
				} catch (Exception e) {
					System.out.println(ErrorConstants.SCO_SYNTAX + "[ScoreFileReader]");
					System.out.println("around :: "+line);
				}
				line = reader.readLine();
			}
		} catch (IOException e) {
			System.out.println("information file reader error[SongInformationFIleReader]");
			e.printStackTrace();
		} 
		return dataLine;
		
		/*
		//test
		NoteSpace dataLine = new NoteSpace();
		dataLine.add(new Note(new MusicTime(6.0), new MusicTime(10.0), new Pitch(7,PitchType.NOTE_NUMBER)));
		dataLine.add(new Note(new MusicTime(11.0), new MusicTime(12.0), new Pitch(4,PitchType.NOTE_NUMBER)));
		dataLine.add(new Note(new MusicTime(12.0), new MusicTime(13.0), new Pitch(5,PitchType.NOTE_NUMBER)));
		dataLine.add(new Note(new MusicTime(13.0), new MusicTime(15.0), new Pitch(7,PitchType.NOTE_NUMBER)));
		dataLine.add(new Note(new MusicTime(15.0), new MusicTime(15.5), new Pitch(0,PitchType.NOTE_NUMBER)));
		dataLine.add(new Note(new MusicTime(15.5), new MusicTime(16.0), new Pitch(2,PitchType.NOTE_NUMBER)));
		dataLine.add(new Note(new MusicTime(16.0), new MusicTime(16.5), new Pitch(4,PitchType.NOTE_NUMBER)));
		dataLine.add(new Note(new MusicTime(16.5), new MusicTime(17.0), new Pitch(5,PitchType.NOTE_NUMBER)));
		dataLine.add(new Note(new MusicTime(17.0), new MusicTime(17.5), new Pitch(7,PitchType.NOTE_NUMBER)));
		dataLine.add(new Note(new MusicTime(17.5), new MusicTime(18.0), new Pitch(9,PitchType.NOTE_NUMBER)));
		dataLine.add(new Note(new MusicTime(18.0), new MusicTime(18.5), new Pitch(11,PitchType.NOTE_NUMBER)));
		return dataLine;
		*/
	}
	public BeatRingSpace readBeatRings() {
		
		BeatRingSpace beatLine = new BeatRingSpace();
		try {
			BufferedReader reader = Files.newBufferedReader(scoreFile);
			int tempo = 0;
			int beat = 0;
			double delay = 0;
			int end = 0;
			
			String line = reader.readLine();
			while(line!=null) {
				try {
					String[] elements = line.split("\\s+");
					String head = elements[0].trim().toLowerCase();
//					System.out.println(elements[0]+" "+elements[1]);
//					System.out.println(head);
					// settings
					if(head.equals(Constants.SCORE_FILE_TEMPO_MARKER)) {
						tempo = Integer.parseInt(elements[1]);
					}else if(head.equals(Constants.SCORE_FILE_BEAT_MARKER)) {
						beat = Integer.parseInt(elements[1]);
					}else if(head.equals(Constants.SCORE_FILE_DELAY_MARKER)) {
						delay = Double.parseDouble(elements[1]);
					}else if(head.equals(Constants.SCORE_FILE_END_MARKER)) {
						end = Integer.parseInt(elements[1]);
					}
				} catch(NumberFormatException e) {
					System.out.println(ErrorConstants.SCO_SYNTAX + "[ScoreFileReader]");
					System.out.println("around :: "+line);
					System.out.println(ErrorConstants.SCO_02);
				}
				line = reader.readLine();
			}
			
			if(tempo == 0 || beat == 0 || end == 0) 
				throw new ScoreException(ErrorConstants.SCO_01);
			
			for(int i=0;i<end*beat;i++) {
				double seconds = 60.0/tempo*i + delay;
				beatLine.add(new BeatRing(new MusicTime(seconds),i%beat==0));
//				System.out.println("beat "+(int)seconds);
			}
			
		} catch(IOException e) {
			System.out.println("score file reader error[ScoreFIleReader]");
		} catch(ScoreException e) {
			System.out.println(ErrorConstants.LYR_SYNTAX + "[ScoreFileReader]");
			System.out.println(e.error);
		}
		return beatLine;
		/*
		//test
		BeatRingSpace beatLine = new BeatRingSpace();
		for(int i=0;i<240;i++) {
			beatLine.add(new BeatRing(new MusicTime(1.0*i),i%4==0));
		}
		return beatLine;
		*/
	}
	
	private double toSeconds(String timing, int tempo, int beat, double delay) throws ScoreException{
		String[] pos = timing.split(Constants.SCORE_FILE_TIMING_SEPARATOR);
		double t;
		if(pos.length == 1) { // direct timing
			t = Double.parseDouble(pos[0]);
		}else {
			if(tempo == 0 || beat == 0) 
				throw new ScoreException(ErrorConstants.SCO_01);
			double noteQuantity = 0;
			double coefficient = beat;
			for(int i=0;i<pos.length;i++) {
				noteQuantity += Integer.parseInt(pos[i])*coefficient;
				coefficient = coefficient / beat;
			}
			t = noteQuantity * 60.0 / tempo;
		}
		t = t + delay;
		return t;
	}
	
	private double toNoteNum(String noteName) throws ScoreException{
		switch(noteName) {
		case "p"	: return POSE;
		case "c"	: return 0;
		case "cis"	: 
		case "des"	: return 1;
		case "d" 	: return 2;
		case "dis" 	:
		case "es" 	: return 3;
		case "e" 	: return 4;
		case "eis"	: 
		case "f"	: return 5;
		case "fis"	: 
		case "ges"	: return 6;
		case "g"	: return 7;
		case "gis"	: 
		case "as"	: return 8;
		case "a"	: return 9;
		case "ais"	:
		case "b"	: return 10;
		case "h"	:
		case "ces"	: return 11;
		default 	: throw new ScoreException(ErrorConstants.SCO_03);
		}
	}
	
	@SuppressWarnings("serial")
	private class ScoreException extends Exception{
		public String error;
		public ScoreException(String errorCode) {
			error = errorCode;
		}
	}
}
