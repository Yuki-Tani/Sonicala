package sonicala.model.data;

public class Sound {
	private final double[] frame;
	private final int sampleRate;
	private Spectrum spectrum;
	private Pitch pitch;
	private Loudness loudness;
	
	public Sound(double[] frame, int sampleRate) {
		this.frame = frame;
		this.sampleRate = sampleRate;
	}
	
	public Spectrum getSpectrum() {
		if(spectrum == null) {
			spectrum = new SpectrumMaker().make(frame, sampleRate);
		}
		return spectrum;
	}
	
	public Pitch getPitch() {
		if(pitch==null) {
			pitch = new PitchMaker().make(frame, sampleRate);
		}
		return pitch;
	}
	
	public Loudness getLoudness() {
		if(loudness == null) {
			loudness = new LoudnessMaker().make(frame, sampleRate);
		}
		return loudness;
	}
}
