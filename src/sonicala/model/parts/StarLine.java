package sonicala.model.parts;

import java.util.ArrayList;

import sonicala.app.Constants;
import sonicala.model.data.MusicTime;
import sonicala.model.data.Spectrum;

@SuppressWarnings("serial")
public class StarLine extends ArrayList<Star>{
	
	public StarLine(Spectrum spectrum, MusicTime time) {
		this(spectrum, time, 
				Constants.STAR_QUANTITY_ON_STAR_LINE,
				Constants.STAR_SPACE_START_FREQUENCY,
				Constants.STAR_SPACE_END_FREQUENCY);
	}
	
	public StarLine(Spectrum spectrum, MusicTime time, int quantity) {
		// spectrumからquantityだけ抽出し、starに変換
		spectrum.sampling(quantity).forEachPoint(
				(frequency,power) -> {
					add(new Star(time,frequency,power));
				}
		);
	}
	
	public StarLine(Spectrum spectrum, MusicTime time, int quantity, double minFrequency, double maxFrequency) {
		// spectrumからquantityだけ抽出し、starに変換
		spectrum.sampling(quantity,minFrequency,maxFrequency).forEachPoint(
				(frequency,power) -> {
					add(new Star(time,frequency,power));
				}
		);
	}
}
