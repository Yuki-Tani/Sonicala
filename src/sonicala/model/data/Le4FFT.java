package sonicala.model.data;

import java.util.Arrays;

import org.apache.commons.math3.complex.Complex;

import jp.ac.kyoto_u.kuis.le4music.Le4MusicUtils;
import sonicala.app.Constants;

public class Le4FFT extends FastFourierTransformer{
	/**
	 * 短時間フーリエ変換を行う
	 * @param frame 2のn乗に正規化されている必要がある
	 * @return　スペクトル(DB)
	 */
	public double[] translate(double[] frame_2_Powered_N, int sampleRate) {
		final Complex[] spectrum = Le4MusicUtils.rfft(frame_2_Powered_N);
		double[] specDB = Arrays.stream(spectrum).mapToDouble(c -> toDB(c.abs())).toArray();
		return specDB;
	}
	
	private double toDB(double src) {
		return 20.0 * Math.log10(src/Constants.FFT_DB_BASIS);
	}
}
