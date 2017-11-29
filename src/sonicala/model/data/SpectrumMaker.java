package sonicala.model.data;

import java.util.Arrays;

import jp.ac.kyoto_u.kuis.le4music.Le4MusicUtils;

public class SpectrumMaker extends SoundElementMaker{
	public SpectrumMaker() {
		
	}
	
	public Spectrum make(double[] frame, int sampleRate) {
		double[] src = to2PoweredN(frame);
		// 高速フーリエ変換
		FastFourierTransformer transformer = new Le4FFT();
		double[] specDB = transformer.translate(src, sampleRate);
		double unit = 1.0 * sampleRate / src.length;
		double maxFrequency = sampleRate / 2.0;
//		System.out.println(">"+frame.length+">"+src.length+">"+specDB.length+" >"+unit+" >"+maxFrequency);
		return new Spectrum(specDB,unit,maxFrequency);
	}
	
	private double[] to2PoweredN(double[] src) {
		final int fftSize = 1 << Le4MusicUtils.nextPow2(src.length);
		return Arrays.stream(Arrays.copyOf(src, fftSize)).toArray();
	}
}
