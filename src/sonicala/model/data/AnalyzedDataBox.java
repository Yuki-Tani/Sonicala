package sonicala.model.data;

import java.util.function.Consumer;

import sonicala.app.Constants;
import sonicala.model.parts.BeatRing;
import sonicala.model.parts.BeatRingSpace;
import sonicala.model.parts.Note;
import sonicala.model.parts.NoteSpace;
import sonicala.model.parts.PitchRing;
import sonicala.model.parts.ScaleRing;
import sonicala.model.parts.Sentence;
import sonicala.model.parts.SentenceSpace;
import sonicala.model.parts.Shaft;
import sonicala.model.parts.Star;
import sonicala.model.parts.StarLine;
import sonicala.model.parts.StarSpace;

public class AnalyzedDataBox extends DataBox{
	
	private MusicTime currentTime;
	private MusicTime inTime;
	private MusicTime outTime;
	
	private StarSpace starSpace;
	private NoteSpace noteSpace;
	private BeatRingSpace beatRingSpace;
	
	private ScaleRing scaleRing;
	private Shaft[] shafts;
	private PitchRing pitchRing;
	
	private SentenceSpace sentenceSpace;
	
	
	public AnalyzedDataBox() {
		starSpace = new StarSpace();
		noteSpace = new NoteSpace();
		beatRingSpace = new BeatRingSpace();
		currentTime = new MusicTime(0);
		scaleRing = new ScaleRing(false,1.0);
		shafts = new Shaft[Constants.SHAFT_QUANTITY];
		for(int i=0; i<shafts.length; i++) {
			shafts[i] = new Shaft();
		}
		pitchRing = PitchRing.makeInitialPitchRing();
		sentenceSpace = new SentenceSpace();

		inTime = currentTime.getFuture(Constants.NOTE_FUTURE_IN_TIME / 1000.0);
		outTime = currentTime.getPast(Constants.NOTE_PAST_OUT_TIME / 1000.0);
	}
	
	public void updateMusicTime(MusicTime currentTime) {
		this.currentTime = currentTime;
		inTime = currentTime.getFuture(Constants.NOTE_FUTURE_IN_TIME / 1000.0);
		outTime = currentTime.getPast(Constants.NOTE_PAST_OUT_TIME / 1000.0);
	}
	
	public void updateStarSpace(StarLine newStarLine) {
		starSpace.add(newStarLine);
	}
	
	public void updateNoteSpace(NoteSpace src) {
//System.out.println("update note");
		noteSpace.removeAllOldNote(outTime);
		src.sendAllNewNote(inTime, noteSpace);
	}
	
	public void updateBeatRingSpace(BeatRingSpace src) {
		beatRingSpace.removeAllOldBeatRing(outTime);
		src.sendAllNewBeatRing(inTime, beatRingSpace);
	}

	public void updateSentenceSpace(SentenceSpace src) {
		sentenceSpace.removeAllOldNote(currentTime);
		src.sendAllNewNote(currentTime, sentenceSpace);
	}
	
	public void updateScaleRing(boolean beat, double brightness) {
		scaleRing = new ScaleRing(beat,brightness);
	}
	
	public void updatePitchRing(Pitch newPitch, Loudness newLoudness) {
		pitchRing = new PitchRing(newPitch, newLoudness, pitchRing);
	}
	
	//////////////////////
	public void operateStars(Consumer<? super Star> operation){
		starSpace.forEach(line -> line.forEach(operation));
	}

	public void operateNotes(Consumer<? super Note> operation) {
		noteSpace.forEach(operation);
	}
	
	public void operateBeatRing(Consumer<? super BeatRing> operation) {
		beatRingSpace.forEach(operation);
	}
	
	public void operateSentenceSpace(Consumer<? super Sentence> operation) {
		sentenceSpace.forEach(operation);
	}

	public MusicTime getCurrentTime() {
		return currentTime;
	}
	
	public MusicTime getInTime() {
		return inTime;
	}
	
	public MusicTime getOutTime() {
		return outTime;
	}
	
	public ScaleRing getScaleRing() {
		return scaleRing;
	}
	
	public Shaft[] getShafts() {
		return shafts;
	}
	
	public PitchRing getPitchRing() {
		return pitchRing;
	}
}
