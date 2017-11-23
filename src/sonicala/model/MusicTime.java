package sonicala.model;

public class MusicTime {
	private double seconds;
	
	public MusicTime(double seconds) {
		this.seconds = seconds;
	}
	
	public double getRelativeSeconds(MusicTime base) {
		return getAbsoluteSeconds() - base.getAbsoluteSeconds();
	}
	
	public double getAbsoluteSeconds() {
		return seconds;
	}
/*	
	public int getMeasureNumber() {
		
	}

	public int getBeatNumber() {
		
	}
*/
}
