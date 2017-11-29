package sonicala.view.element;

import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;

public class ShineRectangleOnPole {
	
	private RectangeOnPole shineRect;
	private RectangeOnPole smokeRect;
	
	public ShineRectangleOnPole() {
		shineRect = new RectangeOnPole();
		smokeRect = new RectangeOnPole();
		shineRect.setPaint(Color.WHITE);
		smokeRect.setPaint(Color.BLUE);
	}
	
	public void setShinePaint(Paint paint) {
		shineRect.setPaint(paint);
	}
	
	public void setSmokePaint(Paint paint) {
		smokeRect.setPaint(paint);
	}
	
	public void paint(
			double beginR,
			double beginW,
			double endR,
			double endW,
			double shineRate,
			double theta,
			PolarCanvas canvas)
	{
		double shineBeginW = beginW * shineRate;
		double shineEndW = endW * shineRate;
		
		smokeRect.paint(beginR, beginW, endR, endW, theta, canvas);
		shineRect.paint(beginR, shineBeginW, endR, shineEndW, theta, canvas);
	}
	
	
}
