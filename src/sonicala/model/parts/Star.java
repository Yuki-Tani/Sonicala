package sonicala.model.parts;

import sonicala.model.data.MusicTime;

public class Star {
	private MusicTime absoluteTime;
	private double frequency;
	private double power;
	
	public Star(MusicTime absoluteTime, double frequency, double power) {
		this.absoluteTime = absoluteTime;
		this.frequency = frequency;
		this.power = power;
	}
	
	@Override
	public String toString() {
		return "t:"+(int)(absoluteTime.getAbsoluteSeconds()*100)/100.0
				+" f: "+(int)(frequency*100)/100.0
				+" p: "+(int)(power*100)/100.0;
			
	}
	
	public double getX(MusicTime currentTime) {
		return absoluteTime.getRelativeSeconds(currentTime)*1000;
	}
	
	public double getY() {
		return frequency;
	}
	
	public double getZ() {
		return power;
	}
	
}
