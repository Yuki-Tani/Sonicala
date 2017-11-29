package sonicala.model.parts;

import sonicala.model.data.MusicTime;

public class BeatRing {
	MusicTime time;
	boolean accented;
	
	public BeatRing(MusicTime time, boolean accented) {
		this.time = time;
		this.accented = accented;
	}
	
	public boolean shoudAppear(MusicTime inTime) {
		return inTime.isAfter(time);
	}
	
	public boolean shoudDisappear(MusicTime outTime) {
		return outTime.isAfter(time);
	}
	
	public double getX(MusicTime inTime) {
		return time.getRelativeSeconds(inTime)*1000;
	}
	
	public double getY() {
		return (accented)? 2.0 : 1.0;
	}
}
