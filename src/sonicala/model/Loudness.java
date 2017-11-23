package sonicala.model;

public class Loudness extends SoundElement{
	private double power;
	
	public Loudness(double power) {
		this.power = power;
	}
	
	public double getPower() {
		return power;
	}
}
