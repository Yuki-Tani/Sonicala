package sonicala.model;

public class Pitch extends SoundElement{
	double frequency;
	
	public Pitch(double frequency) {
		this.frequency = frequency;
	}
	
	public void println() {
		System.out.println(frequency);
	}
	
	public double getFrequency() {
		return frequency;
	}
}
