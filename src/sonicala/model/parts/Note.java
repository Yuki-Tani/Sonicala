package sonicala.model.parts;

import sonicala.model.data.MusicTime;
import sonicala.model.data.Pitch;

public class Note {
	private MusicTime startTime;
	private MusicTime endTime;
	private Pitch pitch;
	
	public Note(MusicTime startTime, MusicTime endTime, Pitch pitch) {
		this.pitch = pitch;
		this.startTime = startTime;
		this.endTime = endTime;
	}
	
	public boolean shoudAppear(MusicTime inTime) {
		return inTime.isAfter(startTime);
	}
	
	public boolean shoudDisappear(MusicTime outTime) {
		return outTime.isAfter(endTime);
	}
	
	public double getStartX(MusicTime inTime, MusicTime outTime) {
		if(startTime.isBefore(outTime)) 
			return outTime.getRelativeSeconds(inTime) * 1000;
		return startTime.getRelativeSeconds(inTime)*1000;
	}
	
	public double getEndX(MusicTime inTime, MusicTime outTime) {
		if(endTime.isAfter(inTime))
			return 0;
		return endTime.getRelativeSeconds(inTime)*1000;
	}
	
	public double getY() {
		return pitch.getNoteNumber();
	}
}
