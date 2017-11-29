package sonicala.model.parts;

import sonicala.model.data.Loudness;
import sonicala.model.data.Pitch;

public class PitchRing {
	
	private Pitch pitch;
	private Loudness loudness;
	private PitchRing prevRing;
	
	public static PitchRing makeInitialPitchRing() {
		return new PitchRing(new Pitch(), new Loudness(0), new PitchRing());
	}
	
	private PitchRing() {
		pitch = new Pitch();
		loudness = new Loudness(0);
	}
	
	public PitchRing(Pitch pitch, Loudness loud, PitchRing prevRing) {
		this.loudness = loud;
		this.pitch = pitch;
		this.prevRing = prevRing;
	}
	
	/**
	 * 
	 * @return pitch ring Y (noteNumber)
	 */
	public double getY() {
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
		return prevRing.getY();
	}
	
	public double getPrevR() {
		return prevRing.getR();
	}
	
}
