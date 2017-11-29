package sonicala.model.data;

import sonicala.app.Constants;

public class LoudnessMaker extends SoundElementMaker{
	public LoudnessMaker() {
		
	}
	
	public Loudness make(double[] frame, int sampleRate) {
		return new Loudness(toDB(rootMeanSquare(frame)));
	}
	
	private double rootMeanSquare(double[] src) {
		double sum = 0;
		for(int i=0; i<src.length; i++) {
			sum += Math.pow(src[i],2);
		}
		return Math.sqrt(sum / src.length);
	}
	
	private double toDB(double src) {
		return 20.0 * Math.log10(src/Constants.FFT_DB_BASIS);
	}
}
