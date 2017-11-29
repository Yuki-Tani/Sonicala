package sonicala.model.data;

public class Loudness extends SoundElement{
	private double power;
	
	public Loudness(double power) {
		this.power = power;
	}
	
	public double getPower() {
		return power;
	}
	
	public boolean isLessThan(Loudness base) {
		return power < base.getPower();
	}
	
	public boolean isLessThan(double power) {
		return this.power < power;
	}
}
