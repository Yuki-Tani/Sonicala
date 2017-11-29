package sonicala.model.player;

import sonicala.model.data.MusicTime;
import sonicala.model.data.Sound;

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
