package sonicala.model;

import sonicala.app.Constants;

public class PitchMaker extends SoundElementMaker{
	
	public PitchMaker() {
		
	}
	
	public Pitch make(double[] frame, int sampleRate) {
		AutocorrelationFunction acf = 
				new AutocorrelationFunction(frame,Constants.AUTOCORRELATION_WIDTH);
		int length  = acf.getMountainTopPosition(2, Constants.AUTOCORRELATION_MOUNTAIN_MARGIN);
		return new Pitch(1.0 * sampleRate / length);
	}
}
