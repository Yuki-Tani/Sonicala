package sonicala.view.element;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;

public class ShineRingOnPole {
	
	private Paint paint;
	private Paint shinePaint;
	
	public ShineRingOnPole() {
		paint = Color.BLUE;
		shinePaint = Color.WHITE;
	}
	
	public void setPaint(Paint paint) {
		this.paint = paint;
	}
	public void setShinePaint(Paint shinePaint) {
		this.shinePaint = shinePaint;
	}
	
	public void moreTransparent(double rate) {
		try {
			paint = ((Color)paint).deriveColor(0,1,1,rate);
			shinePaint = ((Color)shinePaint).deriveColor(0,1,1,rate);
		}catch(ClassCastException e) {
			e.printStackTrace();
		}
	}
	
	public void paint(double r,double theta,double radius, double width,double brightWidth, PolarCanvas canvas) {
		GraphicsContext gc = canvas.getGraphicsContext2D();
		gc.setStroke(paint);
		gc.setLineWidth(width);
		gc.strokeOval(
			canvas.getX(r, theta) - radius,
			canvas.getY(r, theta) - radius,
			radius*2,
			radius*2
		);
		if(brightWidth == 0) return;
		gc.setStroke(shinePaint);
		gc.setLineWidth(brightWidth);
		gc.strokeOval(
			canvas.getX(r, theta) - radius,
			canvas.getY(r, theta) - radius,
			radius*2,
			radius*2
		);
	}
}
