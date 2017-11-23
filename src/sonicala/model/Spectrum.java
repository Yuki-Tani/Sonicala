package sonicala.model;

public class Spectrum extends SoundElement{
	
	private final double[] spectrum;
	private final double unit; //１配列要素の周波数Hz
	private final double maxFrequency;
	
	public Spectrum(double[] spectrum, double unit, double maxFrequency) {
		this.spectrum = spectrum;
		this.unit = unit;
		this.maxFrequency = maxFrequency;
	}
	
	// O(quantity)
	public Spectrum sampling(int quantity) {
		double[] sample = new double[quantity];
		int slide = (spectrum.length-1)/(quantity-1);
		for(int i=0;i<quantity;i++) {
			sample[i] = spectrum[slide*i];
		}
		return new Spectrum(sample,slide*unit,slide*unit*quantity);
	}
	
	public void forEachPoint(PointProcessing processing) {
		for(int i=0; i<spectrum.length; i++) {
			processing.process(i*unit, spectrum[i]);
		}
	}
	
	interface PointProcessing{
		public void process(double frequency, double power);
	}
}
