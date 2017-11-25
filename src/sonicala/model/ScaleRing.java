package sonicala.model;

public class ScaleRing {
	private double brightness;
	private boolean beat;
	
	public ScaleRing(boolean beat, double brightness) {
		this.beat = beat;
		this.brightness = brightness;
	}
	
	public void setBeat(boolean beat) {
		this.beat = beat;
	}
}
