package sonicala.view.element;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;

public class ShineFanOnPole {
	
	Paint framePaint;
	Paint innerPaint;
	
	public ShineFanOnPole() {
		framePaint = Color.BLUE;
		innerPaint = new Color(0.2,0.2,0.6,0.8);
	}
	
	public void setFramePaint(Paint paint) {
		this.framePaint = paint;
	}
	
	public void setinnerPaint(Paint paint) {
		this.innerPaint = paint;
	}
	
	public void paint(
			double beginR,
			double endR,
			double rightTheta,
			double leftTheta,
			double width,
			double shineRate,
			PolarCanvas canvas) 
	{
		GraphicsContext gc = canvas.getGraphicsContext2D();
		double centerTheta = (leftTheta+rightTheta)/2;
		double centerRcof = 1.0 / Math.cos(Math.toRadians((leftTheta - rightTheta)/2));
		gc.beginPath();
		gc.moveTo(canvas.getX(beginR, leftTheta),canvas.getY(beginR, leftTheta));
		gc.arcTo(canvas.getX(beginR*centerRcof,centerTheta),canvas.getY(beginR*centerRcof,centerTheta), 
				canvas.getX(beginR,rightTheta), canvas.getY(beginR,rightTheta), beginR);
		gc.lineTo(canvas.getX(endR, rightTheta), canvas.getY(endR, rightTheta));
		gc.arcTo(canvas.getX(endR*centerRcof,centerTheta),canvas.getY(endR*centerRcof,centerTheta), 
				canvas.getX(endR,leftTheta), canvas.getY(endR,leftTheta), endR);
		gc.closePath();
		//gc.setLineWidth(width);
		//gc.setStroke(framePaint);
		//gc.stroke();
		//gc.setStroke(Color.WHITE);
		//gc.setLineWidth(width * shineRate);
		//gc.stroke();
		gc.setFill(innerPaint);
		gc.fill();
	}
}
