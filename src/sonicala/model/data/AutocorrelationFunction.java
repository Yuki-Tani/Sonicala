package sonicala.model.data;

public class AutocorrelationFunction {
	
	private double[] frame;
	private int calcWidth;
	
	public AutocorrelationFunction(double[] frame) {this(frame,Integer.MAX_VALUE);}
	public AutocorrelationFunction(double[] frame, int calcWidth) {
		this.frame = frame;
		this.calcWidth = calcWidth;
	}
	
	/**
	 * 自己相関関数の値の取得
	 * @param shift 関数のx値(配列シフト量)
	 * @param width 整合を確かめる幅
	 * @return
	 */
	public double getValue(int shift) {
		int width = Math.min(calcWidth, frame.length-shift);
		double sum = 0;
		for(int j=0;j<width;j++) {
			sum += frame[j]*frame[j+shift];
		}
		return sum;
	}
	
	/**
	 * グラフの0以上にある部分のうち、n番目のかたまりの最大値を求める
	 * @param n_th 対象とするグラフ部分
	 * @param margin 0以上/以下とみなすマージン値
	 * @return 対象部分内で最大値をとる配列シフト量
	 */
	public int getMountainTopPosition(int n_th, double margin) {
		int phase = 2;
		int maxSlide = 0;
		double max = 0;
		for(int i=0;i<frame.length;i++) {
			double value = getValue(i);
			if((phase&1)==0) { //山
				if(phase/2==n_th) {//対象の山
					if(value > max) {
						maxSlide = i;
						max = value;
					}
					if(value < -margin) break;
				}else {
					if(value < -margin) phase ++;
				}
			}else{ //谷
				if(value > margin) phase ++;
			}
		}
		return maxSlide;
	}
}
