package sonicala.model;

public abstract class FastFourierTransformer {
	
	/**
	 * 短時間フーリエ変換を行う
	 * @param frame 2のn乗に正規化されている必要がある
	 * @return　スペクトル(DB)
	 */
	public abstract double[] translate(double[] frame_2_Powered_N, int sampleRate);
}
