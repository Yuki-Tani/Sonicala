package sonicala.model.data;

public abstract class SoundElementMaker {
	public abstract SoundElement make(double[] frame, int sampleRate);
}
