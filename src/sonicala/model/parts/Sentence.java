package sonicala.model.parts;

import sonicala.model.data.MusicTime;

public class Sentence {
	private MusicTime startTime;
	private MusicTime endTime;
	private String sentence;
	
	public Sentence(MusicTime startTime, MusicTime endTime, String sentence) {
		this.sentence = sentence;
		this.startTime = startTime;
		this.endTime = endTime;
	}
	
	public boolean shoudAppear(MusicTime currentTime) {
		return currentTime.isAfter(startTime);
	}
	
	public boolean shoudDisappear(MusicTime currentTime) {
		return currentTime.isAfter(endTime);
	}
	
	public String getString() {
		return sentence;
	}
}
