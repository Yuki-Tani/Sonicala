package sonicala.model;

public class MusicSound extends Sound{
	private MusicTime position;

	public MusicSound(double[] frame, int sampleRate, MusicTime position) {
		super(frame, sampleRate);
		this.position = position;
	}
	
	public MusicTime getPosition() {
		return position;
	}
	
}
