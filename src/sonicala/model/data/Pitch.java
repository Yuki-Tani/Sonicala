package sonicala.model.data;

public class Pitch extends SoundElement{
	
	private double frequency;
	private double noteNumber;
	
	public Pitch() {this(440.0,PitchType.FREQUENCY);}
	public Pitch(double frequency_or_noteNumber, PitchType type) {
		if(type == PitchType.FREQUENCY) {
			this.frequency = frequency_or_noteNumber;
			this.noteNumber = Double.NaN;
		} else if(type == PitchType.NOTE_NUMBER){
			this.noteNumber = frequency_or_noteNumber;
			this.frequency = Double.NaN;
		}
	}
	
	public void println() {
		System.out.println(frequency);
	}
	
	public double getFrequency() {
		if(!Double.isNaN(frequency)) return frequency;
		return frequency = 440 * Math.pow(2, (noteNumber - 69) / 12);
	}
	
	public double getNoteNumber() {
		if(!Double.isNaN(noteNumber)) return noteNumber;
		return noteNumber = 12*Math.log(frequency/440.0)/Math.log(2.0) + 69;
	}
}
