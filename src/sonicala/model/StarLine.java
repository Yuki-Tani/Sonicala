package sonicala.model;

import java.util.ArrayList;

import sonicala.app.Constants;

@SuppressWarnings("serial")
public class StarLine extends ArrayList<Star>{
	
	public StarLine(Spectrum spectrum, MusicTime time) {
		this(spectrum, time, Constants.STAR_QUANTITY_ON_STAR_LINE);
	}
	
	public StarLine(Spectrum spectrum, MusicTime time, int quantity) {
		// spectrumからquantityだけ抽出し、starに変換
		spectrum.sampling(quantity).forEachPoint(
				(frequency,power) -> {
					add(new Star(time,frequency,power));
				}
		);
	}
}
