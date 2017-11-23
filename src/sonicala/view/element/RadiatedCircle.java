package sonicala.view.element;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Paint;

public class RadiatedCircle {
	
	private Paint paint;
	
	public void setPaint(Paint paint) {
		this.paint = paint;
	}
	
	public void paint(double r,double theta, double radius, PolarCanvas canvas) {
		GraphicsContext gc = canvas.getGraphicsContext2D();
		gc.setFill(paint);
		gc.setStroke(paint);
		gc.fillOval(
			canvas.getX(r, theta)-radius,
			canvas.getY(r, theta)-radius,
			radius*2,
			radius*2
		);
		/*
		gc.strokeLine(
			canvas.getX(r, theta),
			canvas.getY(r, theta),
			canvas.getX(r-radius*5, theta),
			canvas.getY(r-radius*5, theta)
		);
		*/
		
	}
}
