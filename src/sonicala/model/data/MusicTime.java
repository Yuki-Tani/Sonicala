package sonicala.model.data;

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
	
	public boolean isBefore(MusicTime base) {
		return getRelativeSeconds(base) < 0;
	}
	
	public boolean isAfter(MusicTime base) {
		return getRelativeSeconds(base) > 0;
	}
	
	public MusicTime getPast(double seconds) {
		return new MusicTime(this.seconds - seconds);
	}
	
	public MusicTime getFuture(double seconds) {
		return new MusicTime(this.seconds + seconds);
	}
	
/*	
	public int getMeasureNumber() {
		
	}

	public int getBeatNumber() {
		
	}
*/
}
