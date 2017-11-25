package sonicala.model;

public class Pitch extends SoundElement{
	private double frequency;
	
	public Pitch(double frequency) {
		this.frequency = frequency;
	}
	
	public void println() {
		System.out.println(frequency);
	}
	
	public double getFrequency() {
		return frequency;
	}
	
	public double getNoteNumber() {
		return 12*Math.log(frequency/440.0)/Math.log(2.0) + 69;
	}
}
