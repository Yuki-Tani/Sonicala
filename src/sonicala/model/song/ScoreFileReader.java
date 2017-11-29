package sonicala.model.song;

import java.nio.file.Path;

import sonicala.model.data.MusicTime;
import sonicala.model.data.Pitch;
import sonicala.model.data.PitchType;
import sonicala.model.parts.BeatRing;
import sonicala.model.parts.BeatRingSpace;
import sonicala.model.parts.Note;
import sonicala.model.parts.NoteSpace;

public class ScoreFileReader {
	
	private Path scoreFile;
	
	public ScoreFileReader(Path scoreFile) {
		this.scoreFile = scoreFile;
	}
	
 // TODO make score file reader
	public NoteSpace readNotes() {
		
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
	}
// TODO make beat reader	
	public BeatRingSpace readBeatRings() {
		
		//test
		BeatRingSpace beatLine = new BeatRingSpace();
		for(int i=0;i<240;i++) {
			beatLine.add(new BeatRing(new MusicTime(1.0*i),i%4==0));
		}
		return beatLine;
	}
}
