package sonicala.model;

public class PitchRing {
	
	private Pitch pitch;
	private Loudness loudness;
	private PitchRing prevRing;
	
	public static PitchRing makeInitialPitchRing() {
		return new PitchRing(new Pitch(440.0), new Loudness(0), new PitchRing());
	}
	
	private PitchRing() {
		pitch = new Pitch(440.0);
		loudness = new Loudness(0);
	}
	
	public PitchRing(Pitch pitch, Loudness loud, PitchRing prevRing) {
		this.loudness = loud;
		this.pitch = pitch;
		this.prevRing = prevRing;
	}
	
	/**
	 * 
	 * @return pitch ring X (noteNumber)
	 */
	public double getX() {
		return pitch.getNoteNumber();
	}
	
	/**
	 * 
	 * @return pitch ring R (power)
	 */
	public double getR() {
		return loudness.getPower();
	}
	
	public double getPrevX() {
		return prevRing.getX();
	}
	
	public double getPrevR() {
		return prevRing.getR();
	}
	
}
