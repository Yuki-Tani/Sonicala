package sonicala.model;

public abstract class SoundElementMaker {
	public abstract SoundElement make(double[] frame, int sampleRate);
}
