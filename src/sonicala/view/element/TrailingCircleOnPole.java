package sonicala.view.element;

import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;

public class TrailingCircleOnPole {
	
	private Color color;
	private Color shineColor;
	private ShineRingOnPole mainRing;
	private ShineRingOnPole afterImage;
	private double startOpaque;
	private double endOpaque;
	
	public TrailingCircleOnPole() {
		mainRing = new ShineRingOnPole();
		afterImage = new ShineRingOnPole();
		color = Color.BLUE;
		shineColor = Color.WHITE;
		startOpaque = 0.4;
		endOpaque = 0.2;
	}
	
	public void setMainPaint(Paint paint) {
		mainRing.setPaint(paint);
	}
	
	public void setAfterImageColor(Color color) {
		this.color = color;
	}
	
	public void setAfterImageShineColor(Color color) {
		this.shineColor = color;
	}
	
	public void setAfterImageOpaque(double start, double end) {
		startOpaque = start;
		endOpaque = end;
	}
	
	public void paint(
		double r, double theta, double radius, double width, double shineWidth,
		double prevR, double prevTheta, double prevRadius, double prevWidth, double prevShineWidth,
		int afterImageQuantity, PolarCanvas canvas) 
	{
		afterImage.setPaint(color.deriveColor(0,1,1,endOpaque));
		afterImage.setShinePaint(shineColor.deriveColor(0,1,1,endOpaque));
		double rate = (afterImageQuantity >= 2)? 
				Math.pow(startOpaque/endOpaque, 1.0/(afterImageQuantity-1))
				: 1;
		for(int i=0; i<afterImageQuantity; i++) {
			afterImage.paint(
				prevR + (r - prevR) / afterImageQuantity * i,
				prevTheta + (theta - prevTheta) / afterImageQuantity * i, 
				prevRadius + (radius - prevRadius) / afterImageQuantity * i, 
				prevWidth + (width - prevWidth) / afterImageQuantity * i, 
				prevShineWidth + (shineWidth - prevShineWidth) / afterImageQuantity * i, 
			    canvas);
			afterImage.moreTransparent(rate);
		}
		mainRing.paint(r, theta ,radius, width, shineWidth, canvas);
	}
}