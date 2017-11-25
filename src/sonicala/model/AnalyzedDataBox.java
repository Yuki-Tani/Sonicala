package sonicala.model;

import java.util.function.Consumer;

import sonicala.app.Constants;

public class AnalyzedDataBox extends DataBox{
	
	private MusicTime time;
	private StarSpace space;
	private ScaleRing scaleRing;
	private Shaft[] shafts;
	private PitchRing pitchRing;
	
	public AnalyzedDataBox() {
		space = new StarSpace();
		time = new MusicTime(0);
		scaleRing = new ScaleRing(false,1.0);
		shafts = new Shaft[Constants.SHAFT_QUANTITY];
		for(int i=0; i<shafts.length; i++) {
			shafts[i] = new Shaft();
		}
		pitchRing = PitchRing.makeInitialPitchRing();
	}
	
	public void updateStarSpace(StarLine newStarLine) {
		space.add(newStarLine);
	}
	
	public void updateMusicTime(MusicTime time) {
		this.time = time;
	}
	
	public void updateScaleRing(boolean beat, double brightness) {
		scaleRing = new ScaleRing(beat,brightness);
	}
	
	public void updatePitchRing(Pitch newPitch, Loudness newLoudness) {
		pitchRing = new PitchRing(newPitch, newLoudness, pitchRing);
	}
	
	public void operateStars(Consumer<? super Star> operation){
		space.forEach(line -> line.forEach(operation));
	}
	
	public MusicTime getCurrentTime() {
		return time;
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
